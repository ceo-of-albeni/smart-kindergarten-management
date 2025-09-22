package kg.megalab.smart_kindergarten_management.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Group extends BaseEntity{

    @Id
    @GeneratedValue
    Long id;
    String name;
    int max_children_count;
    double price;

    @ManyToOne
    @JoinColumn(name = "nanny_id")
    Teacher nanny;

    @ManyToOne
    @JoinColumn(name = "group_category_id")
    GroupCategory group_category;

    @ManyToOne
    @JoinColumn(name = "teacher_id ")
    Teacher teacher;
}
