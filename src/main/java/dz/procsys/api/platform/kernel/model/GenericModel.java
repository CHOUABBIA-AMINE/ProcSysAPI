/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: GenericModel
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 05-17-2026
 *
 *	@Type		: Abstract Class
 *	@Layer		: Template
 *	@Package	: Platform / Kernel
 *
 **/

package dz.procsys.api.platform.kernel.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

/**
 * Technical base entity type providing an identifier and optimistic lock version.
 * Domain entities should extend this class and add their own domain-specific fields.
 */
@Setter
@Getter
@MappedSuperclass
public abstract class GenericModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;
}