package kg.megalab.smart_kindergarten_management.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table(name = "group_categories")
public class GroupCategory extends BaseEntity{

    @Id
    @GeneratedValue
    Long id;
    String name;
    boolean active;
    double price;

    @OneToMany(mappedBy = "groupCategory")
    List<Group> groups = new ArrayList<>();
}
