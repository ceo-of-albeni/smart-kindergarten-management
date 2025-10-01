package kg.megalab.smart_kindergarten_management.service;

import jakarta.validation.Valid;
import kg.megalab.smart_kindergarten_management.model.dto.GroupDto;

import java.util.List;

public interface GroupService {
    GroupDto createGroup(@Valid GroupDto groupDto);

    GroupDto updateGroup(Long id, @Valid GroupDto groupDto);

    GroupDto deleteGroup(Long id);

    GroupDto findGroupById(Long id);

    List<GroupDto> findGroups(int pageNo, int pageSize);
}
