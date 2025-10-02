package kg.megalab.smart_kindergarten_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.megalab.smart_kindergarten_management.model.GroupChildren;
import kg.megalab.smart_kindergarten_management.model.dto.EnrollChildDto;
import kg.megalab.smart_kindergarten_management.model.dto.WithdrawChildDto;
import kg.megalab.smart_kindergarten_management.service.GroupChildrenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group-children")
@Tag(name = "Group Children Controller", description = "Контроллер «Групп детей»")
public class GroupChildrenController {

    private final GroupChildrenService groupChildrenService;

    public GroupChildrenController(GroupChildrenService groupChildrenService) {
        this.groupChildrenService = groupChildrenService;
    }

    @PostMapping("")
    @Operation(summary = "Зачисление ребенка", description = "Создает ребенка и записывает его в выбранную группу")
    @ApiResponse(responseCode = "201", description = "Ребенок успешно зачислен")
    @ApiResponse(responseCode = "404", description = "Группа не найдена")
    @ApiResponse(responseCode = "409", description = "Группа переполнена")
    public ResponseEntity<EnrollChildDto> enrollChild(@RequestBody @Valid EnrollChildDto enrollChildDto){
        enrollChildDto = groupChildrenService.enrollChild(enrollChildDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollChildDto);
    }

    @PutMapping("/{id}/withdraw")
    @Operation(summary = "Отчисление ребенка", description = "Закрывает запись ребенка в группе, добавляя дату окончания")
    @ApiResponse(responseCode = "200", description = "Ребенок успешно отчислен")
    @ApiResponse(responseCode = "404", description = "Запись ребенка в группе не найдена")
    public ResponseEntity<GroupChildren> withdrawChild(@PathVariable Long id, @RequestBody(required = false) WithdrawChildDto withdrawChildDto) {
        GroupChildren updated = groupChildrenService.withdrawChild(id, withdrawChildDto);
        return ResponseEntity.ok(updated);
    }
}
