package kg.megalab.smart_kindergarten_management.service.impl;

import kg.megalab.smart_kindergarten_management.model.Child;
import kg.megalab.smart_kindergarten_management.model.Group;
import kg.megalab.smart_kindergarten_management.model.GroupChildren;
import kg.megalab.smart_kindergarten_management.model.dto.EnrollChildDto;
import kg.megalab.smart_kindergarten_management.model.dto.WithdrawChildDto;
import kg.megalab.smart_kindergarten_management.repository.ChildRepo;
import kg.megalab.smart_kindergarten_management.repository.GroupChildrenRepo;
import kg.megalab.smart_kindergarten_management.repository.GroupRepo;
import kg.megalab.smart_kindergarten_management.service.GroupChildrenService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class GroupChildrenServiceImpl implements GroupChildrenService {

    private final ChildRepo childRepo;
    private final GroupRepo groupRepo;
    private final GroupChildrenRepo groupChildrenRepo;

    public GroupChildrenServiceImpl(ChildRepo childRepo,  GroupRepo groupRepo, GroupChildrenRepo groupChildrenRepo) {
        this.childRepo = childRepo;
        this.groupRepo = groupRepo;
        this.groupChildrenRepo = groupChildrenRepo;
    }

    @Override
    public EnrollChildDto enrollChild(EnrollChildDto enrollChildDto) {

        Group group = groupRepo.findById(enrollChildDto.getGroupId())
                .orElseThrow(() -> new RuntimeException("Группа не найдена!"));


        Long activeChildrenCount = groupChildrenRepo.countByGroupIdAndEndDateIsNull(group.getId());
        if (activeChildrenCount >= group.getMaxChildrenCount()) {
            throw new RuntimeException("Группа заполнена!");
        }

        Child child = new Child();
        child.setFirstName(enrollChildDto.getFirstName());
        child.setLastName(enrollChildDto.getLastName());
        child.setPatronymic(enrollChildDto.getPatronymic());
        child.setDateOfBirth(enrollChildDto.getDateOfBirth());
        child = childRepo.save(child);

        GroupChildren groupChildren = new GroupChildren();
        groupChildren.setChildren(child);
        groupChildren.setGroup(group);
        groupChildren.setStartDate(LocalDate.now());
        groupChildren.setPrice(enrollChildDto.getPrice() != null ? enrollChildDto.getPrice() : group.getPrice());
        groupChildrenRepo.save(groupChildren);

        return enrollChildDto;
    }

    @Override
    public GroupChildren withdrawChild(Long id, WithdrawChildDto withdrawChildDto) {
        GroupChildren groupChildren = groupChildrenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Запись ребенка в группе не найдена!"));

        LocalDate endDate = withdrawChildDto.getEndDate() != null ? withdrawChildDto.getEndDate() : LocalDate.now();
        groupChildren.setEndDate(endDate);

        return groupChildrenRepo.save(groupChildren);
    }
}
