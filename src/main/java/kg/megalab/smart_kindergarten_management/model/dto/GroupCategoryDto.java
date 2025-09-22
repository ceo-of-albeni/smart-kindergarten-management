package kg.megalab.smart_kindergarten_management.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class GroupCategoryDto {

    @NotBlank(message = "Название категории обязательно")
    private String name;

    @NotNull(message = "Статус активности обязателен")
    private Boolean active;

    @NotNull(message = "Цена обязательна")
    @Positive(message = "Цена должна быть положительной")
    private Integer price;

}
