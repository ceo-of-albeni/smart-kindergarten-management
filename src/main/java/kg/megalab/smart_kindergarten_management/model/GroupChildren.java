package kg.megalab.smart_kindergarten_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
    LocalDate startDate;
    LocalDate endDate;
    Double price;

    @ManyToOne
    @JoinColumn(name = "children_id")
    Child children;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    Group group;
}
