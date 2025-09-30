package kg.megalab.smart_kindergarten_management.model;

import jakarta.persistence.*;
import kg.megalab.smart_kindergarten_management.enums.TeacherDegree;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "teachers")
public class Teacher extends BaseEntity{

    @Id
    @GeneratedValue
    Long id;
    @Column(name = "first_name")
    String firstName;
    String lastName;
    String patronymic;
    @Enumerated(EnumType.STRING)
    TeacherDegree teacherDegree;
    boolean active;
}
