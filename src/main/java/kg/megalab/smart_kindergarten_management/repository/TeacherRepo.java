package kg.megalab.smart_kindergarten_management.repository;

import kg.megalab.smart_kindergarten_management.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
}
