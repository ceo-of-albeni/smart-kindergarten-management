package kg.megalab.smart_kindergarten_management.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue
    Long id;
    double amount;
    LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "group_children_id ")
    GroupChildren groupChildren;
}
