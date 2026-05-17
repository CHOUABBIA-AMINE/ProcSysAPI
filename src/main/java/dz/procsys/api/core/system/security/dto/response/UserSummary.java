package dz.procsys.api.core.system.security.dto.response;

public record UserSummary(
        Long id,
        String username,
        String email,
        Boolean enabled
) {}
