package kg.megalab.smart_kindergarten_management.service;

import jakarta.validation.Valid;
import kg.megalab.smart_kindergarten_management.model.GroupChildren;
import kg.megalab.smart_kindergarten_management.model.dto.EnrollChildDto;
import kg.megalab.smart_kindergarten_management.model.dto.WithdrawChildDto;

public interface GroupChildrenService {
    EnrollChildDto enrollChild(@Valid EnrollChildDto enrollChildDto);

    GroupChildren withdrawChild(Long id, WithdrawChildDto withdrawChildDto);
}
