package kg.megalab.smart_kindergarten_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GroupChildrenNotFound extends RuntimeException {
    public GroupChildrenNotFound(Long id) {
        super("Запись ребенка с id=" + id + " не найдена!");
    }
}