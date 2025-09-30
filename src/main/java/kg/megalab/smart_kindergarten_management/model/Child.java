package kg.megalab.smart_kindergarten_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table(name = "childrens")
public class Child {

    @Id
    @GeneratedValue
    Long id;
    String firstName;
    String lastName;
    String patronymic;
    LocalDate dateOfBirth;
}
