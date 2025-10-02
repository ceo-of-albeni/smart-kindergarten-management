package kg.megalab.smart_kindergarten_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.megalab.smart_kindergarten_management.model.dto.PaymentDto;
import kg.megalab.smart_kindergarten_management.model.dto.PreviousMonthDebtDto;
import kg.megalab.smart_kindergarten_management.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@Tag(name = "Payment Controller", description = "Контроллер Оплат")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("")
    @Operation(summary = "Создание Payment", description = "Добавление новой записи о платеже за ребенка")
    @ApiResponse(responseCode = "201", description = "Платеж успешно добавлен")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации DTO")
    @ApiResponse(responseCode = "404", description = "Запись о ребенке в группе не найдена")
    public ResponseEntity<PaymentDto> postPayment(@RequestBody @Valid PaymentDto paymentDto){
        paymentDto = paymentService.postPayment(paymentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentDto);
    }

    @GetMapping("/previous-month/{childId}")
    @Operation(summary = "Получение задолженности за прошлый календарный месяц", description = "Возвращает, сколько должен ребенок за прошлый календарный месяц.")
    @ApiResponse(responseCode = "200", description = "Успешно.")
    @ApiResponse(responseCode = "400", description = "Неверный формат ID.")
    @ApiResponse(responseCode = "404", description = "Ребенок не найден или нет активной группы за прошлый месяц.")
    public ResponseEntity<PreviousMonthDebtDto> getDebtFromPreviousMonth(@PathVariable Long childId) {
        PreviousMonthDebtDto previousMonthDebtDto = paymentService.getDebtFromPreviousMonth(childId);
        return ResponseEntity.ok(previousMonthDebtDto);
    }

}
