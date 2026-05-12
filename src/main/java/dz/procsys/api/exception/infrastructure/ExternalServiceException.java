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

package dz.procsys.api.exception.infrastructure;

import dz.procsys.api.exception.base.ApiException;
import dz.procsys.api.exception.base.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExternalServiceException extends ApiException {

    private static final long serialVersionUID = 1L;
    private final String serviceName;

    public ExternalServiceException(String serviceName, String message) {
        super(ErrorCode.INF_EXTERNAL_SERVICE_ERROR,
              String.format("External service '%s' failed: %s", serviceName, message),
              HttpStatus.BAD_GATEWAY);
        this.serviceName = serviceName;
    }

    public ExternalServiceException(String serviceName, String message, Throwable cause) {
        super(ErrorCode.INF_EXTERNAL_SERVICE_ERROR,
              String.format("External service '%s' failed: %s", serviceName, message),
              HttpStatus.BAD_GATEWAY, cause);
        this.serviceName = serviceName;
    }
}