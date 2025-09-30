package kg.megalab.smart_kindergarten_management.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    @NotBlank(message = "Название группы обязательно")
    String name;

    @NotNull(message = "Макс. количество детей обязательно")
    @Positive(message = "Количество должно быть положительным")
    Integer maxChildrenCount;

    @NotNull(message = "Цена обязательна")
    @Positive(message = "Цена должна быть положительной")
    Integer price;

    @NotNull(message = "Няня обязательна")
    Long nannyId;

    @NotNull(message = "Категория группы обязательна")
    Long groupCategoryId;

    @NotNull(message = "Учитель обязателен")
    Long teacherId;
}
