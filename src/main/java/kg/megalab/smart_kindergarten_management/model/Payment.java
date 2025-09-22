package kg.megalab.smart_kindergarten_management.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Payment {

    @Id
    @GeneratedValue
    Long id;
    double amount;
    LocalDateTime payment_date;

    @ManyToOne
    @JoinColumn(name = "group_children_id ")
    GroupChildren group_children;
}
