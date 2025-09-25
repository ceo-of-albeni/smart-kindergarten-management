package kg.megalab.smart_kindergarten_management.service;

import jakarta.validation.Valid;
import kg.megalab.smart_kindergarten_management.model.dto.GroupCategoryDto;

import java.util.List;

public interface GroupCategoryService {
    GroupCategoryDto createGroupCategory(@Valid GroupCategoryDto groupCategoryDto);

    GroupCategoryDto updateGroupCategory(Long id, @Valid GroupCategoryDto groupCategoryDto);

    GroupCategoryDto deleteGroupCategory(Long id);

    GroupCategoryDto findGroupCategoryById(Long id);

    List<GroupCategoryDto> findGroupCategories(int pageNo, int pageSize);
}
