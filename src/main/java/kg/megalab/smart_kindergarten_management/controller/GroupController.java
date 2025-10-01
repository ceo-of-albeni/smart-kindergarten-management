package kg.megalab.smart_kindergarten_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.megalab.smart_kindergarten_management.model.dto.GroupDto;
import kg.megalab.smart_kindergarten_management.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@Tag(name = "Group Controller", description = "Контроллер «Групп»")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("")
    @Operation(summary = "Создание Group", description = "В этом методе создается объект Group")
    @ApiResponse(responseCode = "201", description = "Group успешно создан")
    @ApiResponse(responseCode = "409", description = "Group уже существует в таблице")
    public ResponseEntity<GroupDto> createGroup(@RequestBody @Valid GroupDto groupDto){
        groupDto = groupService.createGroup(groupDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(groupDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление Group", description = "В этом методе обновляется объект Group")
    @ApiResponse(responseCode = "200", description = "Group успешно обновлен")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    @ApiResponse(responseCode = "404", description = "Категория не найдена")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long id, @RequestBody @Valid GroupDto groupDto) {
        GroupDto updatedGroupDto = groupService.updateGroup(id, groupDto);
        return ResponseEntity.ok(updatedGroupDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление Group", description = "В этом методе удаляется объект Group")
    @ApiResponse(responseCode = "200", description = "Group успешно удален")
    @ApiResponse(responseCode = "404", description = "Group не найден")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        GroupDto deletedGroupDto = groupService.deleteGroup(id);
        return ResponseEntity.ok(deletedGroupDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение Group по ID", description = "В этом методе получаем объект Group")
    @ApiResponse(responseCode = "200", description = "Group успешно найден")
    @ApiResponse(responseCode = "404", description = "Group не найден")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long id) {
        GroupDto groupDto = groupService.findGroupById(id);
        return ResponseEntity.ok(groupDto);
    }

    @GetMapping("")
    @Operation(summary = "Получение списка Group", description = "В этом методе получаем список Group")
    @ApiResponse(responseCode = "200", description = "Groups успешно найдены")
    @ApiResponse(responseCode = "400", description = "Неверные параметры пагинации")
    public ResponseEntity<?> getAllGroups(@RequestParam int pageNo, @RequestParam int pageSize) {

        if (pageNo < 0 || pageSize <= 0) {
            return ResponseEntity.badRequest()
                    .body("Неверные параметры пагинации: page >= 0, size > 0");
        }

        List<GroupDto> groupDtos = groupService.findGroups(pageNo, pageSize);

        if (groupDtos.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


        return ResponseEntity.ok(groupDtos);
    }
}
