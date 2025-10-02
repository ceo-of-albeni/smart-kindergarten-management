package kg.megalab.smart_kindergarten_management.service.impl;

import kg.megalab.smart_kindergarten_management.exception.GroupChildrenNotFound;
import kg.megalab.smart_kindergarten_management.exception.NotActiveInPrevMonth;
import kg.megalab.smart_kindergarten_management.mapper.PaymentMapper;
import kg.megalab.smart_kindergarten_management.model.Child;
import kg.megalab.smart_kindergarten_management.model.GroupChildren;
import kg.megalab.smart_kindergarten_management.model.Payment;
import kg.megalab.smart_kindergarten_management.model.dto.PaymentDto;
import kg.megalab.smart_kindergarten_management.model.dto.PreviousMonthDebtDto;
import kg.megalab.smart_kindergarten_management.repository.ChildRepo;
import kg.megalab.smart_kindergarten_management.repository.GroupChildrenRepo;
import kg.megalab.smart_kindergarten_management.repository.PaymentRepo;
import kg.megalab.smart_kindergarten_management.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo  paymentRepo;
    private final GroupChildrenRepo groupChildrenRepo;
    private final ChildRepo childRepo;
    private final PaymentMapper paymentMapper = PaymentMapper.INSTANCE;

    public PaymentServiceImpl(PaymentRepo paymentRepo, GroupChildrenRepo groupChildrenRepo, ChildRepo childRepo) {
        this.paymentRepo = paymentRepo;
        this.groupChildrenRepo = groupChildrenRepo;
        this.childRepo = childRepo;
    }

    @Override
    public PaymentDto postPayment(PaymentDto paymentDto) {

        GroupChildren groupChildren = groupChildrenRepo.findById(paymentDto.getGroupChildrenId())
                .orElseThrow(() -> new GroupChildrenNotFound(paymentDto.getGroupChildrenId()));

        paymentDto.setGroupChildrenId(groupChildren.getId());

        Payment payment = paymentMapper.paymentDtoToPayment(paymentDto);
        payment = paymentRepo.save(payment);
        return paymentMapper.paymentToPaymentDto(payment);
    }

    @Override
    public PreviousMonthDebtDto getDebtFromPreviousMonth(Long childId) {

        Child child = childRepo.findById(childId)
                .orElseThrow(() -> new GroupChildrenNotFound(childId));

        GroupChildren groupChildren = groupChildrenRepo.findById(childId)
                .orElseThrow(() -> new GroupChildrenNotFound(childId));

        LocalDate now = LocalDate.now();
        LocalDate firstDayPrevMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayPrevMonth = firstDayPrevMonth.withDayOfMonth(firstDayPrevMonth.lengthOfMonth());

        boolean activeInPrevMonth = groupChildren.getStartDate()
                .isBefore(lastDayPrevMonth.plusDays(1)) &&
                (groupChildren.getEndDate() == null ||
                        groupChildren.getEndDate()
                                .isAfter(firstDayPrevMonth.minusDays(1)));

        if (!activeInPrevMonth) {
            throw new NotActiveInPrevMonth(childId);
        }

        double price = (groupChildren.getPrice() != null) ? groupChildren.getPrice() : groupChildren.getGroup().getPrice();

        List<Payment> payments = paymentRepo.findAllByGroupChildrenIdAndPaymentDateBetween(
                groupChildren.getId(),
                firstDayPrevMonth.atStartOfDay(),
                lastDayPrevMonth.atTime(23, 59, 59)
        );
        double totalPaid = payments.stream().mapToDouble(Payment::getAmount).sum();

        double debt = price - totalPaid;

        if (debt < 0)
            debt = 0;

        PreviousMonthDebtDto previousMonthDebtDto = new PreviousMonthDebtDto();
        previousMonthDebtDto.setChildId(childId);
        previousMonthDebtDto.setAmountDue((int) debt);
        return previousMonthDebtDto;
    }
}
