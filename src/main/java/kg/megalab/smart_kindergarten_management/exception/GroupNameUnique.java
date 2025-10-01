package kg.megalab.smart_kindergarten_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class GroupNameUnique extends RuntimeException{
}
