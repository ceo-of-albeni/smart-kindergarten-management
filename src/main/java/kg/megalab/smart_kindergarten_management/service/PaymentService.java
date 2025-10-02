package kg.megalab.smart_kindergarten_management.service;

import jakarta.validation.Valid;
import kg.megalab.smart_kindergarten_management.model.dto.PaymentDto;
import kg.megalab.smart_kindergarten_management.model.dto.PreviousMonthDebtDto;

public interface PaymentService {
    PaymentDto postPayment(@Valid PaymentDto paymentDto);

    PreviousMonthDebtDto getDebtFromPreviousMonth(Long childId);
}
