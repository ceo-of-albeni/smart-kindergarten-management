package kg.megalab.smart_kindergarten_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.megalab.smart_kindergarten_management.model.dto.GroupCategoryDto;
import kg.megalab.smart_kindergarten_management.service.GroupCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group-category")
@Tag(name = "GroupCategoryController", description = "Контроллер «Категории групп»")
public class GroupCategoryController {

    private final GroupCategoryService groupCategoryService;

    public GroupCategoryController(GroupCategoryService groupCategoryService) {
        this.groupCategoryService = groupCategoryService;
    }

    @PostMapping("")
    @Operation(summary = "Создание GroupCategory", description = "В этом методе создается объект GroupCategory")
    @ApiResponse(responseCode = "201", description = "GroupCategory успешно создан")
    @ApiResponse(responseCode = "409", description = "GroupCategory уже существует в таблице")
    public ResponseEntity<GroupCategoryDto> createGroupCategory(@RequestBody @Valid GroupCategoryDto groupCategoryDto){
        groupCategoryDto = groupCategoryService.createGroupCategory(groupCategoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(groupCategoryDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление GroupCategory", description = "В этом методе обновляется объект GroupCategory")
    @ApiResponse(responseCode = "200", description = "GroupCategory успешно обновлен")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    @ApiResponse(responseCode = "404", description = "Категория не найдена")
    @ApiResponse(responseCode = "409", description = "GroupCategory с таким именем уже существует в таблице")
    public ResponseEntity<GroupCategoryDto> updateGroupCategory(@PathVariable Long id, @RequestBody @Valid GroupCategoryDto groupCategoryDto) {
        GroupCategoryDto updatedGroupCategoryDto = groupCategoryService.updateGroupCategory(id, groupCategoryDto);
        return ResponseEntity.ok(updatedGroupCategoryDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление GroupCategory", description = "В этом методе удаляется объект GroupCategory")
    @ApiResponse(responseCode = "200", description = "GroupCategory успешно удален")
    @ApiResponse(responseCode = "404", description = "Категория не найдена")
    public ResponseEntity<?> deleteGroupCategory(@PathVariable Long id) {
        GroupCategoryDto deletedGroupCategoryDto = groupCategoryService.deleteGroupCategory(id);
        return ResponseEntity.ok(deletedGroupCategoryDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение GroupCategory по ID", description = "В этом методе получаем объект GroupCategory")
    @ApiResponse(responseCode = "200", description = "GroupCategory успешно найден")
    @ApiResponse(responseCode = "404", description = "Категория не найдена")
    public ResponseEntity<GroupCategoryDto> getGroupCategoryById(@PathVariable Long id) {
        GroupCategoryDto groupCategoryDto = groupCategoryService.findGroupCategoryById(id);
        return ResponseEntity.ok(groupCategoryDto);
    }

    @GetMapping("")
    @Operation(summary = "Получение списка GroupCategory", description = "В этом методе получаем список GroupCategory")
    @ApiResponse(responseCode = "200", description = "GroupCategories успешно найдены")
    @ApiResponse(responseCode = "400", description = "Неверные параметры пагинации")
    public ResponseEntity<?> getAllGroupCategories(@RequestParam int pageNo, @RequestParam int pageSize) {

        if (pageNo < 0 || pageSize <= 0) {
            return ResponseEntity.badRequest()
                    .body("Неверные параметры пагинации: page >= 0, size > 0");
        }

        List<GroupCategoryDto> groupCategoryDtos = groupCategoryService.findGroupCategories(pageNo, pageSize);

        if (groupCategoryDtos.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.ok(Map.of(
                "amount", groupCategoryDtos.size(),
                "groups", groupCategoryDtos
        ));
    }

}
