package kg.megalab.smart_kindergarten_management.repository;

import kg.megalab.smart_kindergarten_management.model.GroupChildren;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupChildrenRepo extends JpaRepository<GroupChildren, Long> {

    Long countByGroupIdAndEndDateIsNull(Long groupId);

}
