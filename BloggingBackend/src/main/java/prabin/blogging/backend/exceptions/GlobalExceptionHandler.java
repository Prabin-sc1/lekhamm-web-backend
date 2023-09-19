package prabin.blogging.backend.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import prabin.blogging.backend.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {

		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public
	 * ResponseEntity<ApiResponse>
	 * methodArgumentNotValidException(MethodArgumentNotValidException ex){ String
	 * temp = ex.getMessage(); ApiResponse apiResponse = new ApiResponse(temp,
	 * false); return new ResponseEntity<ApiResponse>(apiResponse,
	 * HttpStatus.NOT_FOUND); }
	 */

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((e) -> {
			String field = ((FieldError) e).getField();
			String defaultMessage = e.getDefaultMessage();
			errors.put(field, defaultMessage);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
