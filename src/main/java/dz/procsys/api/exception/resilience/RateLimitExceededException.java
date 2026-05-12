/**
 *	
 *	@Author		: Amine CHOUABBIA
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
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RateLimitExceededException extends ApiException {

    private static final long serialVersionUID = 1L;
    private final int retryAfterSeconds;

    public RateLimitExceededException(int retryAfterSeconds) {
        super(ErrorCode.RES_RATE_LIMIT_EXCEEDED,
              String.format("Rate limit exceeded. Retry after %d seconds.", retryAfterSeconds),
              HttpStatus.TOO_MANY_REQUESTS);
        this.retryAfterSeconds = retryAfterSeconds;
    }

    public RateLimitExceededException(String message, int retryAfterSeconds) {
        super(ErrorCode.RES_RATE_LIMIT_EXCEEDED, message, HttpStatus.TOO_MANY_REQUESTS);
        this.retryAfterSeconds = retryAfterSeconds;
    }
}