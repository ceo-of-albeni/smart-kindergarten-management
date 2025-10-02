package kg.megalab.smart_kindergarten_management.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotActiveInPrevMonth extends RuntimeException{
    public NotActiveInPrevMonth(Long childId) {
        super("Ребенок с ID " + childId + " не был активен в прошлом месяце.");
    }

}
