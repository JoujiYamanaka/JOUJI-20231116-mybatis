package mybatis.exceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import mybatis.util.MessageSourceUtil;
import mybatis.util.ResponseEntityUtil;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

	/**
	 * 500エラーに対するハンドラー
	 * 
	 * @param ex Throwable
	 * @return ResponseEntity INTERNAL_SERVER_ERROR
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Throwable.class })
	public ResponseEntity<Map<String, Object>> handleThrowable(Throwable ex) {
		log.warn("handleThrowable", ex);
		return ResponseEntityUtil.setResponseEntity(MessageSourceUtil.getMessage("internalServerError"),
				HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonList(ex.getMessage()));
	}

	/**
	 * ModelAttributeのバリデーションに対するハンドラー
	 * 
	 * @param ex BindException
	 * @return ResponseEntity BAD_REQUEST
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ResponseEntity<Map<String, Object>> handleConstraintViolationException(BindException ex) {
		List<String> errorMessages = new ArrayList<String>();
		// ここでエラーメッセージを取得
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errorMessages.add(error.getDefaultMessage());
		}
		return ResponseEntityUtil.setResponseEntity(errorMessages, HttpStatus.BAD_REQUEST, null);
	}

	/**
	 * クエリーパラメータのバリデーションに対するハンドラー
	 * 
	 * @param ex ConstraintViolationException
	 * @return ResponseEntity BAD_REQUEST
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {
		List<String> errorMessages = new ArrayList<String>();
		// ここでエラーメッセージを取得
		for (ConstraintViolation<?> error : ex.getConstraintViolations()) {
			errorMessages.add(error.getMessage());
		}
		return ResponseEntityUtil.setResponseEntity(errorMessages, HttpStatus.BAD_REQUEST, null);
	}

	/**
	 * RequestBodyのバリデーションに対するハンドラー
	 * 
	 * @param ex MethodArgumentNotValidException
	 * @return ResponseEntity BAD_REQUEST
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		List<String> errorMessages = new ArrayList<String>();
		// ここでエラーメッセージを取得
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errorMessages.add(error.getDefaultMessage());
		}
		return ResponseEntityUtil.setResponseEntity(errorMessages, HttpStatus.BAD_REQUEST, null);
	}
}
