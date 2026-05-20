/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ShelfResponseDTO
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
 * Response DTO representing a shelf.
 */
@Schema(
    name = "ShelfResponseDTO",
    description = "Represents a storage shelf located in a room"
)
@Setter
@Getter
@ToString
public class ShelfResponseDTO {

    /**
     * Technical identifier of the shelf.
     */
    @Schema(
        description = "Technical identifier of the shelf",
        example = "5"
    )
    private Long id;

    /**
     * Shelf code unique within the room.
     */
    @Schema(
        description = "Shelf code unique within the room",
        example = "S01"
    )
    private String code;

    /**
     * Identifier of the room where the shelf is located.
     */
    @Schema(
        description = "Identifier of the room where the shelf is located",
        example = "10"
    )
    private Long roomId;
}