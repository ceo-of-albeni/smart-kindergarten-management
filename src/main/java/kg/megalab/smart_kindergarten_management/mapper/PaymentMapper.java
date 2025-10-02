package kg.megalab.smart_kindergarten_management.mapper;

import kg.megalab.smart_kindergarten_management.model.Payment;
import kg.megalab.smart_kindergarten_management.model.dto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(source = "groupChildrenId", target = "groupChildren.id")
    Payment paymentDtoToPayment(PaymentDto paymentDto);

    @Mapping(source = "groupChildren.id", target = "groupChildrenId")
    PaymentDto paymentToPaymentDto(Payment payment);

    List<PaymentDto> paymentsToPaymentDtos(List<Payment> payments);
}
