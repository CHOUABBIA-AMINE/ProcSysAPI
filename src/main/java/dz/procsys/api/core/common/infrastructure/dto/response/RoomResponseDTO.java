/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : RoomResponseDTO
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
 * Response DTO representing a room.
 */
@Schema(
    name = "RoomResponseDTO",
    description = "Represents a room located in a building"
)
@Setter
@Getter
@ToString
public class RoomResponseDTO {

    /**
     * Technical identifier of the room.
     */
    @Schema(
        description = "Technical identifier of the room",
        example = "10"
    )
    private Long id;

    /**
     * Room code unique within the building.
     */
    @Schema(
        description = "Room code unique within the building",
        example = "A99"
    )
    private String code;

    /**
     * Identifier of the building where the room is located.
     */
    @Schema(
        description = "Identifier of the building where the room is located",
        example = "1"
    )
    private Long buildingId;
}