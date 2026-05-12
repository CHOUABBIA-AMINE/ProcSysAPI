/**
 *	
 *	@Author		: Amine CHOUABBIA
 *
 *	@Name		: TokenRefreshResponse
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 11-18-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: System / Authentication
 *
 **/

package dz.procsys.api.core.system.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRefreshResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
}
