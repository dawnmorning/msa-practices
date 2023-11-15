package com.poscodx.emaillist.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.poscodx.emaillist.dto.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<JsonResult>  handlerException(Exception e) {
		// 로깅(Logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		log.error(errors.toString());
		// 0. 요청 구분
		// json 요청: accept에 application/json (o)
		// html 요청: accept에 application/json (x)

		// 응답

		JsonResult jsonResult = (e instanceof NoHandlerFoundException) ? JsonResult.fail("Unknown request")
				: JsonResult.fail(errors.toString());
		return ResponseEntity.status(HttpStatus.OK).body(jsonResult);

	}
}