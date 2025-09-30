package kg.megalab.smart_kindergarten_management.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.megalab.smart_kindergarten_management.enums.TeacherDegree;
import lombok.Data;

@Data
public class TeacherDto {

    @NotBlank(message = "Имя обязательно")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    private String lastName;

    private String patronymic;

    @NotNull(message = "Степень/роль обязательна")
    private TeacherDegree teacherDegree;

    @NotNull(message = "Статус обязателен")
    private Boolean active;

}

