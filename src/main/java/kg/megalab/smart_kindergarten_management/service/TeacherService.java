package kg.megalab.smart_kindergarten_management.service;

import jakarta.validation.Valid;
import kg.megalab.smart_kindergarten_management.model.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    TeacherDto createTeacher(@Valid TeacherDto teacherDto);

    TeacherDto updateTeacher(Long id, @Valid TeacherDto teacherDto);

    TeacherDto deleteTeacher(Long id);

    TeacherDto findTeacherById(Long id);

    List<TeacherDto> findTeachers(int pageNo, int pageSize);
}
