package kg.megalab.smart_kindergarten_management.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class PreviousMonthDebtDto {
    Long childId;
    Integer amountDue; // сумма к оплате за прошлый месяц
}

