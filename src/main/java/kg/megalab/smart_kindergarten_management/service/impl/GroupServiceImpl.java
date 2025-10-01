package kg.megalab.smart_kindergarten_management.service.impl;

import kg.megalab.smart_kindergarten_management.enums.TeacherDegree;
import kg.megalab.smart_kindergarten_management.exception.GroupNameUnique;
import kg.megalab.smart_kindergarten_management.exception.GroupNotFound;
import kg.megalab.smart_kindergarten_management.exception.TeacherNotFound;
import kg.megalab.smart_kindergarten_management.mapper.GroupMapper;
import kg.megalab.smart_kindergarten_management.model.Group;
import kg.megalab.smart_kindergarten_management.model.GroupCategory;
import kg.megalab.smart_kindergarten_management.model.Teacher;
import kg.megalab.smart_kindergarten_management.model.dto.GroupDto;
import kg.megalab.smart_kindergarten_management.model.dto.TeacherDto;
import kg.megalab.smart_kindergarten_management.repository.GroupCategoryRepo;
import kg.megalab.smart_kindergarten_management.repository.GroupRepo;
import kg.megalab.smart_kindergarten_management.repository.TeacherRepo;
import kg.megalab.smart_kindergarten_management.service.GroupService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepo groupRepo;
    private final GroupCategoryRepo groupCategoryRepo;
    private final TeacherRepo teacherRepo;
    private final GroupMapper groupMapper = GroupMapper.INSTANCE;

    public GroupServiceImpl(GroupRepo  groupRepo, GroupCategoryRepo groupCategoryRepo, TeacherRepo teacherRepo) {
        this.groupRepo = groupRepo;
        this.groupCategoryRepo = groupCategoryRepo;
        this.teacherRepo = teacherRepo;
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        if (groupRepo.existsByNameIgnoreCase(groupDto.getName()))
            throw new GroupNameUnique();

//        CATEGORY
        GroupCategory groupCategory = groupCategoryRepo.findById(groupDto.getGroupCategoryId()).
                orElseThrow(() -> new RuntimeException("Категория группы не найдена!"));


        if (!groupCategory.isActive()) {
            throw new RuntimeException("Категория с таким ID не активна!");
        }

//        TEACHER
        Teacher teacher = teacherRepo.findById(groupDto.getTeacherId()).orElseThrow(() ->
                new RuntimeException("Учитель c таким id не найден!"));

        if (!teacher.isActive()) {
            throw new RuntimeException("Учитель с таким ID не активна!");
        }

        if (teacher.getTeacherDegree() != TeacherDegree.TEACHER){
            throw new RuntimeException("Объект с таким ID не являеется учителем!");
        }
//         NANNY
        Teacher nanny = teacherRepo.findById(groupDto.getNannyId()).orElseThrow(() ->
                new RuntimeException("Няня c таким ID не найдена!"));

        if (!nanny.isActive()) {
            throw new RuntimeException("Няня с таким ID не активна!");
        }

        if (nanny.getTeacherDegree() != TeacherDegree.NANNY){
            throw new RuntimeException("Объект с таким ID не являеется няней!");
        }

        Group group = groupMapper.groupDtoToGroup(groupDto);

        group.setGroupCategory(groupCategory);
        group.setTeacher(teacher);
        group.setNanny(nanny);

        group = groupRepo.save(group);
        return groupMapper.groupToGroupDto(group);
    }

    @Override
    public GroupDto updateGroup(Long id, GroupDto groupDto) {
        Group existingGroup = groupRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Группа с таким ID не найдена!"));

        existingGroup.setName(groupDto.getName());
        existingGroup.setMaxChildrenCount(groupDto.getMaxChildrenCount());
        existingGroup.setPrice(groupDto.getPrice());

        if (groupDto.getGroupCategoryId() != null) {
            GroupCategory groupCategory = groupCategoryRepo.findById(groupDto.getGroupCategoryId()).orElseThrow(() ->
                    new RuntimeException("Категория групп с таким ID не найдена!"));

            if (!groupCategory.isActive()) {
                throw new RuntimeException("Категория с таким ID не активна!");
            }

            existingGroup.setGroupCategory(groupCategory);
        };

        if (groupDto.getNannyId() != null) {
            Teacher nanny = teacherRepo.findById(groupDto.getNannyId()).orElseThrow(() ->
                    new RuntimeException("Няня с таким ID не найдена!"));

            if (!nanny.isActive()) {
                throw new RuntimeException("Няня с таким ID не активна!");
            }

            if (nanny.getTeacherDegree() != TeacherDegree.NANNY){
                throw new RuntimeException("Объект с таким ID не являеется няней!");
            }

            existingGroup.setNanny(nanny);
        }

        if (groupDto.getTeacherId() != null) {
            Teacher teacher = teacherRepo.findById(groupDto.getTeacherId()).orElseThrow(() ->
                    new RuntimeException("Учитель с таким ID не найден!"));

            if (!teacher.isActive()) {
                throw new RuntimeException("Учитель с таким ID не активен!");
            }

            if (teacher.getTeacherDegree() != TeacherDegree.TEACHER){
                throw new RuntimeException("Объект с таким ID не являеется учителем!");
            }

            existingGroup.setTeacher(teacher);
        }

        Group savedGroup = groupRepo.save(existingGroup);
        return groupMapper.groupToGroupDto(savedGroup);
    }

    @Override
    public GroupDto deleteGroup(Long id) {
        groupRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Группа с таким ID не найдена"));

        groupRepo.deleteById(id);

        return null;
    }

    @Override
    public GroupDto findGroupById(Long id) {
        Group group = groupRepo.findById(id).orElseThrow(GroupNotFound::new);
        return groupMapper.groupToGroupDto(group);
    }

    @Override
    public List<GroupDto> findGroups(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Group> groups = groupRepo.findAll(pageable).getContent();
        List<GroupDto> groupDtos = groupMapper.groupsToGroupDtos(groups);
        return groupDtos;
    }
}
