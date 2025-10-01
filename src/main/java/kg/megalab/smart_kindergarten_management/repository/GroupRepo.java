package kg.megalab.smart_kindergarten_management.repository;

import kg.megalab.smart_kindergarten_management.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {

    boolean existsByNameIgnoreCase(String name);
}
