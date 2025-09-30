package kg.megalab.smart_kindergarten_management.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.megalab.smart_kindergarten_management.model.dto.TeacherDto;
import kg.megalab.smart_kindergarten_management.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
@Tag(name = "TeacherController", description = "Контроллер «Учителя»")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("")
    @Operation(summary = "Создание Teacher", description = "В этом методе создается объект Teacher")
    @ApiResponse(responseCode = "201", description = "Teacher успешно создан")
    @ApiResponse(responseCode = "409", description = "Teacher уже существует в таблице")
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody @Valid TeacherDto teacherDto){
        teacherDto = teacherService.createTeacher(teacherDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление Teacher", description = "В этом методе обновляется объект Teacher")
    @ApiResponse(responseCode = "200", description = "Teacher успешно обновлен")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    @ApiResponse(responseCode = "404", description = "Teacher не найден")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long id, @RequestBody @Valid TeacherDto teacherDto) {
        TeacherDto updatedTeacherDto = teacherService.updateTeacher(id, teacherDto);
        return ResponseEntity.ok(updatedTeacherDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление Teacher", description = "В этом методе удаляется объект Teacher")
    @ApiResponse(responseCode = "200", description = "Teacher успешно удален")
    @ApiResponse(responseCode = "404", description = "Teacher не найден")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        TeacherDto deletedTeacherDto = teacherService.deleteTeacher(id);
        return ResponseEntity.ok(deletedTeacherDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение Teacher по ID", description = "В этом методе получаем объект Teacher")
    @ApiResponse(responseCode = "200", description = "Teacher успешно найден")
    @ApiResponse(responseCode = "404", description = "Teacher не найден")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
        TeacherDto teacherDto = teacherService.findTeacherById(id);
        return ResponseEntity.ok(teacherDto);
    }

    @GetMapping("")
    @Operation(summary = "Получение списка Teacher", description = "В этом методе получаем список Teacher")
    @ApiResponse(responseCode = "200", description = "Teachers успешно найдены")
    @ApiResponse(responseCode = "400", description = "Неверные параметры пагинации")
    public ResponseEntity<?> getAllTeachers(@RequestParam int pageNo, @RequestParam int pageSize) {

        if (pageNo < 0 || pageSize <= 0) {
            return ResponseEntity.badRequest()
                    .body("Неверные параметры пагинации: page >= 0, size > 0");
        }

        List<TeacherDto> teacherDtos = teacherService.findTeachers(pageNo, pageSize);

        if (teacherDtos.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.ok(Map.of(
                "amount", teacherDtos.size(),
                "groups", teacherDtos
        ));
    }
}
