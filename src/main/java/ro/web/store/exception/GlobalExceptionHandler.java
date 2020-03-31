package ro.web.store.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleNotFounds(EntityNotFoundException entityNotFoundException){
        return createResponseEntity(HttpStatus.NOT_FOUND,entityNotFoundException);
    }

    private ResponseEntity createResponseEntity(HttpStatus httpStatus, Exception e){
        return new ResponseEntity(new ApiError(httpStatus,e.getMessage()), httpStatus);
    }

    @Data
    @NoArgsConstructor
    public class ApiError {
        private int status;
        private String message;
        private String error;


        public ApiError(HttpStatus httpStatus, String message) {
            if (httpStatus != null) {
                this.status=httpStatus.value();
                this.error=httpStatus.getReasonPhrase();
            }
            this.message=message;
        }
    }
}
