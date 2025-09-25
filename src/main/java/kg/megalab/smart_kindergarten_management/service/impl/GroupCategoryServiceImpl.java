package kg.megalab.smart_kindergarten_management.service.impl;

import kg.megalab.smart_kindergarten_management.exception.GroupCategoryNameUnique;
import kg.megalab.smart_kindergarten_management.exception.GroupCategoryNotFound;
import kg.megalab.smart_kindergarten_management.mapper.GroupCategoryMapper;
import kg.megalab.smart_kindergarten_management.model.GroupCategory;
import kg.megalab.smart_kindergarten_management.model.dto.GroupCategoryDto;
import kg.megalab.smart_kindergarten_management.repository.GroupCategoryRepo;
import kg.megalab.smart_kindergarten_management.service.GroupCategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupCategoryServiceImpl implements GroupCategoryService {

    private final GroupCategoryRepo  groupCategoryRepo;
    private final GroupCategoryMapper groupCategoryMapper = GroupCategoryMapper.INSTANCE;

    public GroupCategoryServiceImpl(GroupCategoryRepo  groupCategoryRepo) {
        this.groupCategoryRepo = groupCategoryRepo;

    }

    @Override
    public GroupCategoryDto createGroupCategory(GroupCategoryDto groupCategoryDto) {

        if (groupCategoryRepo.existsByNameIgnoreCase(groupCategoryDto.getName()))
            throw new GroupCategoryNameUnique();

        GroupCategory groupCategory = groupCategoryMapper.groupCategoryDtoToGroupCategory(groupCategoryDto);
        groupCategory = groupCategoryRepo.save(groupCategory);
        return groupCategoryMapper.groupCategoryToGroupCategoryDto(groupCategory);
    }

    @Override
    public GroupCategoryDto updateGroupCategory(Long id, GroupCategoryDto groupCategoryDto) {

        GroupCategory existingGroupCategory = groupCategoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Категория с таким ID не найдена"));

        existingGroupCategory.setName(groupCategoryDto.getName());
        existingGroupCategory.setActive(groupCategoryDto.getActive());
        existingGroupCategory.setPrice(groupCategoryDto.getPrice());

        GroupCategory savedGroupCategory = groupCategoryRepo.save(existingGroupCategory);
        return groupCategoryMapper.groupCategoryToGroupCategoryDto(savedGroupCategory);
    }

    @Override
    public GroupCategoryDto deleteGroupCategory(Long id) {

        GroupCategory groupCategory = groupCategoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Категория с таким ID не найдена"));

        groupCategoryRepo.deleteById(id);

        return null;
    }

    @Override
    public GroupCategoryDto findGroupCategoryById(Long id) {
        GroupCategory groupCategory = groupCategoryRepo.findById(id).orElseThrow(GroupCategoryNotFound::new);
        return groupCategoryMapper.groupCategoryToGroupCategoryDto(groupCategory);
    }

    @Override
    public List<GroupCategoryDto> findGroupCategories(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<GroupCategory> groupCategories = groupCategoryRepo.findAll(pageable).getContent();
        List<GroupCategoryDto> groupCategoryDtos = groupCategoryMapper.groupCategoriesToGroupCategoryDtos(groupCategories);
        return groupCategoryDtos;
    }
}