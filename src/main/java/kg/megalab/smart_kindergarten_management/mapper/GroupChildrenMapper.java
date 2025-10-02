package kg.megalab.smart_kindergarten_management.mapper;

import kg.megalab.smart_kindergarten_management.model.GroupChildren;
import kg.megalab.smart_kindergarten_management.model.dto.EnrollChildDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GroupChildrenMapper {

    GroupChildrenMapper INSTANCE = Mappers.getMapper(GroupChildrenMapper.class);

    GroupChildren enrollChildDtoToGroupChildren(EnrollChildDto groupChildrenDto);
    EnrollChildDto groupChildrenToEnrollChildDto(GroupChildren groupChildren);

    List<EnrollChildDto> groupChildrensToEnrollChildDtos(List<GroupChildren> groupCategories);

}
