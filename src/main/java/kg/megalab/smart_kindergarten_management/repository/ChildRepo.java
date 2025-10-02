package kg.megalab.smart_kindergarten_management.repository;

import kg.megalab.smart_kindergarten_management.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ChildRepo extends JpaRepository<Child, Long> {

    Child findByFirstNameAndLastNameAndDateOfBirth(String firstName, String lastName, LocalDate dateOfBirth);

}
