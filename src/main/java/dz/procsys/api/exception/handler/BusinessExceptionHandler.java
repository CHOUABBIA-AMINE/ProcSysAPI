/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ApiException
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 11-18-2025
 *
 *	@Type		: Class
 *	@Layer		: Exception
 *	@Package	: Exception
 *
 **/

package dz.procsys.api.exception.handler;

import dz.procsys.api.exception.base.ErrorResponse;
import dz.procsys.api.exception.business.BusinessException;
import dz.procsys.api.exception.business.BusinessValidationException;
import dz.procsys.api.exception.business.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, WebRequest req) {
        log.warn("Resource not found: {}", ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(ErrorResponse.of(ex, path(req)));
    }

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<ErrorResponse> handleBusinessValidation(BusinessValidationException ex, WebRequest req) {
        log.warn("Business validation: {}", ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(ErrorResponse.of(ex, path(req)));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex, WebRequest req) {
        log.warn("Business exception [{}]: {}", ex.getCode(), ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(ErrorResponse.of(ex, path(req)));
    }

    private String path(WebRequest req) {
        return req.getDescription(false).replace("uri=", "");
    }
}