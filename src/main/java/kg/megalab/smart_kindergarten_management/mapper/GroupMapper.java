package kg.megalab.smart_kindergarten_management.mapper;


import kg.megalab.smart_kindergarten_management.model.Group;
import kg.megalab.smart_kindergarten_management.model.dto.GroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(source = "nannyId", target = "nanny.id")
    @Mapping(source = "teacherId", target = "teacher.id")
    @Mapping(source = "groupCategoryId", target = "groupCategory.id")
    Group groupDtoToGroup(GroupDto groupDto);

    @Mapping(source = "nanny.id", target = "nannyId")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "groupCategory.id", target = "groupCategoryId")
    GroupDto groupToGroupDto(Group group);

    List<GroupDto> groupsToGroupDtos(List<Group> groups);
}
