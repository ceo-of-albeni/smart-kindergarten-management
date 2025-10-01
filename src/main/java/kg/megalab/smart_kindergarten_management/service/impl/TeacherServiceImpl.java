package kg.megalab.smart_kindergarten_management.service.impl;

import kg.megalab.smart_kindergarten_management.exception.TeacherNotFound;
import kg.megalab.smart_kindergarten_management.mapper.TeacherMapper;
import kg.megalab.smart_kindergarten_management.model.Teacher;
import kg.megalab.smart_kindergarten_management.model.dto.TeacherDto;
import kg.megalab.smart_kindergarten_management.repository.TeacherRepo;
import kg.megalab.smart_kindergarten_management.service.TeacherService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;
    private final TeacherMapper teacherMapper = TeacherMapper.INSTANCE;

    public TeacherServiceImpl(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = teacherMapper.teacherDtoToTeacher(teacherDto);
        teacher = teacherRepo.save(teacher);
        return teacherMapper.teacherToTeacherDto(teacher);
    }

    @Override
    public TeacherDto updateTeacher(Long id, TeacherDto teacherDto) {
        Teacher existingTeacher = teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Учитель с таким ID не найден"));

        existingTeacher.setFirstName(teacherDto.getFirstName());
        existingTeacher.setLastName(teacherDto.getLastName());
        existingTeacher.setPatronymic(teacherDto.getPatronymic());
        existingTeacher.setTeacherDegree(teacherDto.getTeacherDegree());
        existingTeacher.setActive(teacherDto.getActive());

        Teacher savedTeacher = teacherRepo.save(existingTeacher);
        return teacherMapper.teacherToTeacherDto(savedTeacher);
    }

    @Override
    public TeacherDto deleteTeacher(Long id) {
        teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Категория с таким ID не найдена"));

        teacherRepo.deleteById(id);

        return null;
    }

    @Override
    public TeacherDto findTeacherById(Long id) {
        Teacher teacher = teacherRepo.findById(id).orElseThrow(TeacherNotFound::new);
        return teacherMapper.teacherToTeacherDto(teacher);
    }

    @Override
    public List<TeacherDto> findTeachers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Teacher> teachers = teacherRepo.findAll(pageable).getContent();
        List<TeacherDto> teacherDtos = teacherMapper.teachersToTeacherDtos(teachers);
        return teacherDtos;
    }
}
