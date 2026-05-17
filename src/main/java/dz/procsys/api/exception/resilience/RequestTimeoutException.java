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

package dz.procsys.api.exception.resilience;

import dz.procsys.api.exception.base.ApiException;
import dz.procsys.api.exception.base.ErrorCode;
import org.springframework.http.HttpStatus;

public class RequestTimeoutException extends ApiException {

    private static final long serialVersionUID = 1L;

    public RequestTimeoutException(String operation) {
        super(ErrorCode.RES_REQUEST_TIMEOUT,
              String.format("Operation '%s' timed out. Please try again.", operation),
              HttpStatus.REQUEST_TIMEOUT);
    }

    public RequestTimeoutException(String operation, long timeoutMillis) {
        super(ErrorCode.RES_REQUEST_TIMEOUT,
              String.format("Operation '%s' timed out after %d ms.", operation, timeoutMillis),
              HttpStatus.REQUEST_TIMEOUT);
    }
}