package kg.megalab.smart_kindergarten_management.model.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    @NotNull(message = "ID записи ребенка в группе обязателен")
    Long groupChildrenId;

    @NotNull(message = "Сумма платежа обязательна")
    @Positive(message = "Сумма должна быть положительной")
    Integer amount;

    @NotNull(message = "Дата платежа обязательна")
    LocalDate paymentDate;
}

