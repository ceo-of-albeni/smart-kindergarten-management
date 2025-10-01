package kg.megalab.smart_kindergarten_management.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
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
