/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.isa.osApi.domain.exception;

import dev.isa.osApi.exceptionhandler.ProblemaException;
import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author digma
 */
public class DomainException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DomainException(String message) {
    super(message);
    }
   
    
 private ResponseEntity<Object> handleExceptionInternal(DomainException ex, ProblemaException problema, HttpHeaders httpHeaders, HttpStatus status, WebRequest request) {
 throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
   
 }
    
  // @ExceptionHandler(DomainException.class)
  /* public ResponseEntity<Object> handleDomainException(DomainException ex, WebRequest request) {
   var status = HttpStatus.BAD_REQUEST;
   problema.setStatus(status.value());
   problema.setTitulo(ex.getMessage());
   problema.setDataHora(LocalDateTime.now());

   return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
*/
  

}
