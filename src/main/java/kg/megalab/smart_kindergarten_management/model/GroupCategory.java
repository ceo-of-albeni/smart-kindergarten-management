package kg.megalab.smart_kindergarten_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class GroupCategory extends BaseEntity{

    @Id
    @GeneratedValue
    Long id;
    String name;
    boolean active;
    double price;
}
