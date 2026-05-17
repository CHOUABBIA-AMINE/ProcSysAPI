/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: GenericDTO
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 05-17-2026
 *
 *	@Type		: Abstract Class
 *	@Layer		: Template
 *	@Package	: Platform / Kernel
 *
 **/

package dz.procsys.api.platform.kernel.dto;

/**
 * Base Data Transfer Object type carrying the technical identifier of a resource.
 * Domain-specific DTOs should extend this type and add additional fields as needed.
 */
public abstract class GenericDTO {

    /**
     * Technical identifier of the resource represented by this DTO.
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}