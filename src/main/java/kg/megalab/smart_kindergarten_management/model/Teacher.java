package kg.megalab.smart_kindergarten_management.model;

import jakarta.persistence.*;
import kg.megalab.smart_kindergarten_management.enums.TeacherDegree;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Teacher extends BaseEntity{

    @Id
    @GeneratedValue
    Long id;
    String first_name;
    String last_name;
    String patronymic;
    @Enumerated(EnumType.STRING)
    TeacherDegree teacher_degree;
    boolean active;
}
