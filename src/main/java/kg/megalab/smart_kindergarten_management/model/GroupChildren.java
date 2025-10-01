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
@Table(name = "group_childrens")
public class GroupChildren {

    @Id
    @GeneratedValue
    Long id;
    LocalDateTime startDate;
    LocalDateTime endDate;
    double price;

    @ManyToOne
    @JoinColumn(name = "children_id")
    Child children;

    @ManyToOne
    @JoinColumn(name = "group_id")
    Group group;
}
