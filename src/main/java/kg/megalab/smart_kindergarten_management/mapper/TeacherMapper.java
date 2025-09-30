package kg.megalab.smart_kindergarten_management.mapper;

import kg.megalab.smart_kindergarten_management.model.Teacher;
import kg.megalab.smart_kindergarten_management.model.dto.TeacherDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    Teacher teacherDtoToTeacher(TeacherDto teacherDto);
    TeacherDto teacherToTeacherDto(Teacher teacher);

    List<TeacherDto> teachersToTeacherDtos(List<Teacher> teachers);
}
