package kg.megalab.smart_kindergarten_management.mapper;

import kg.megalab.smart_kindergarten_management.model.GroupCategory;
import kg.megalab.smart_kindergarten_management.model.dto.GroupCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GroupCategoryMapper {

    GroupCategoryMapper INSTANCE = Mappers.getMapper(GroupCategoryMapper.class);

    GroupCategory groupCategoryDtoToGroupCategory(GroupCategoryDto groupCategoryDto);
    GroupCategoryDto groupCategoryToGroupCategoryDto(GroupCategory groupCategory);

    List<GroupCategoryDto> groupCategoriesToGroupCategoryDtos(List<GroupCategory> groupCategories);
}
