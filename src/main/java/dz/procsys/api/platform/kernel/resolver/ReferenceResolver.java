/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ReferenceResolver
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 05-17-2026
 *
 *	@Type		: Class
 *	@Layer		: Template
 *	@Package	: Platform / Kernel
 *
 **/

package dz.procsys.api.platform.kernel.resolver;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Utility component for resolving references by identifier using JPA repositories.
 */
public class ReferenceResolver {

    /**
     * Resolves an entity reference by identifier using the given repository.
     *
     * @param repository repository used to load the entity
     * @param id identifier of the referenced entity
     * @param <E> entity type
     * @param <ID> identifier type
     * @return resolved entity
     * @throws IllegalArgumentException if the entity cannot be found
     */
    public <E, ID> E resolve(JpaRepository<E, ID> repository, ID id) {
        if (id == null) {
            throw new IllegalArgumentException("Reference identifier must not be null");
        }
        Optional<E> result = repository.findById(id);
        return result.orElseThrow(() ->
                new IllegalArgumentException("Referenced entity not found for id: " + id));
    }
}