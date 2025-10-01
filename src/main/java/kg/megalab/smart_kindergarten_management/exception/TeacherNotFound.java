package kg.megalab.smart_kindergarten_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TeacherNotFound extends RuntimeException {
}
