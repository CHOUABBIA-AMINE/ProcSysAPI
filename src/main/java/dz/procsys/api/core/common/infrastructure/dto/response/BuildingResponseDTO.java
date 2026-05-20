/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : BuildingResponseDTO
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Class
 *  @Layer      : DTO / Response
 *  @Package    : Common / Infrastructure
 *
 **/

package dz.procsys.api.core.common.infrastructure.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Response DTO representing a building.
 */
@Schema(
    name = "BuildingResponseDTO",
    description = "Represents a building with multilingual designations"
)
@Setter
@Getter
@ToString
public class BuildingResponseDTO {

    /**
     * Technical identifier of the building.
     */
    @Schema(
        description = "Technical identifier of the building",
        example = "1"
    )
    private Long id;

    /**
     * Building designation in Arabic.
     */
    @Schema(
        description = "Building designation in Arabic",
        example = "المبنى أ"
    )
    private String designationAr;

    /**
     * Building designation in English.
     */
    @Schema(
        description = "Building designation in English",
        example = "Building A"
    )
    private String designationEn;

    /**
     * Building designation in French (unique business key).
     */
    @Schema(
        description = "Building designation in French (unique business key)",
        example = "Bloc A"
    )
    private String designationFr;
}