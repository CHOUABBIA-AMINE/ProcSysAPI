/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: GenericMapper
 *	@CreatedOn	: 06-26-2025 
 *	@UpdatedOn	: 05-17-2026
 *
 *	@Type		: Abstract Class
 *	@Layer		: Template
 *	@Package	: Platform / Kernel
 *
 **/

package dz.procsys.api.platform.kernel.mapper;

import java.util.List;

/**
 * Generic mapper contract between domain entities and DTO types.
 *
 * @param <E> entity type
 * @param <D> DTO type
 */
public interface GenericMapper<E, D> {

    /**
     * Maps the given entity instance to its DTO representation.
     */
    D toDto(E entity);

    /**
     * Maps the given DTO instance to its entity representation.
     */
    E toEntity(D dto);

    /**
     * Maps a list of entities to a list of DTOs.
     */
    List<D> toDtoList(List<E> entities);

    /**
     * Maps a list of DTOs to a list of entities.
     */
    List<E> toEntityList(List<D> dtos);
}