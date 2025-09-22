package kg.megalab.smart_kindergarten_management.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table(name = "group_childrens")
public class GroupChildren {

    @Id
    @GeneratedValue
    Long id;
    LocalDateTime start_date;
    LocalDateTime end_date;
    double price;

    @ManyToOne
    @JoinColumn(name = "children_id")
    Child children;

    @ManyToOne
    @JoinColumn(name = "group_id")
    Group group;
}
