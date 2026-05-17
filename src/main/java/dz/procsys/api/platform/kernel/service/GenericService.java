/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: GenericService
 *	@CreatedOn	: 06-26-2025 
 *	@UpdatedOn	: 05-17-2026
 *
 *	@Type		: Abstract Class
 *	@Layer		: Template
 *	@Package	: Platform / Kernel
 *
 **/

package dz.procsys.api.platform.kernel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Generic service abstraction providing basic CRUD operations for aggregate roots.
 *
 * @param <E> entity type
 * @param <ID> identifier type
 */
public interface GenericService<E, ID> {

    /**
     * Persists a new entity instance.
     */
    E create(E entity);

    /**
     * Updates an existing entity instance.
     */
    E update(ID id, E entity);

    /**
     * Retrieves an entity by its identifier.
     */
    Optional<E> findById(ID id);

    /**
     * Returns a page of entities matching the given pagination parameters.
     */
    Page<E> findAll(Pageable pageable);

    /**
     * Deletes an entity by its identifier.
     */
    void deleteById(ID id);

    /**
     * Checks whether an entity with the given identifier exists.
     */
    boolean existsById(ID id);
}