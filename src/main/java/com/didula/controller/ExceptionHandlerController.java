package com.didula.controller;

import com.didula.dto.MessageDTO;
import com.didula.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
  @ExceptionHandler(NoSuchGenderException.class)
  ResponseEntity<MessageDTO> handleNoSuchGenderException() {
    return new ResponseEntity<>(
            new MessageDTO("Such gender doesn't exist in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchCourseException.class)
  ResponseEntity<MessageDTO> handleNoSuchCourseException() {
    return new ResponseEntity<>(
            new MessageDTO("Such course doesn't exist in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchUserException.class)
  ResponseEntity<MessageDTO> handleNoSuchUserException() {
    return new ResponseEntity<>(
            new MessageDTO("Such user doesn't exist in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchSecurityException.class)
  ResponseEntity<MessageDTO> handleNoSuchSecurityException() {
    return new ResponseEntity<>(
            new MessageDTO("Such security doesn't exist in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchCourseInfoException.class)
  ResponseEntity<MessageDTO> handleNoSuchCourseInfoException() {
    return new ResponseEntity<>(
            new MessageDTO("Such course info doesn't exist in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchTestException.class)
  ResponseEntity<MessageDTO> handleNoSuchTestException() {
    return new ResponseEntity<>(
            new MessageDTO("Such test doesn't exist in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchModuleException.class)
  ResponseEntity<MessageDTO> handleNoSuchModuleException() {
    return new ResponseEntity<>(
            new MessageDTO("Such module doesn't exist in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchMessageException.class)
  ResponseEntity<MessageDTO> handleNoSuchMessageException() {
    return new ResponseEntity<>(
            new MessageDTO("Such message doesn't exist in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchAnswerException.class)
  ResponseEntity<MessageDTO> handleNoSuchAnswerException() {
    return new ResponseEntity<>(
            new MessageDTO("Such answer doesn't exist in database"), HttpStatus.NOT_FOUND);
  }
}
