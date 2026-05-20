/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ArchiveBox
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Archive
 *
 **/

package dz.procsys.api.core.common.archive.model;

import dz.procsys.api.core.common.infrastructure.model.Shelf;
import dz.procsys.api.platform.kernel.model.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ArchiveBox Entity - Extends GenericModel
 * Represents archive boxes with code and location on shelf floor
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="ArchiveBox")
@Table(name="T_01_01_06", uniqueConstraints = { @UniqueConstraint(name = "T_01_01_06_UK_01", columnNames = { "F_01" })})
public class ArchiveBox extends GenericModel {
	
	@Column(name="F_01", length=50, nullable=false)
	private String code;
	
	@Column(name="F_02", length=200)
	private String description;
	
	@ManyToOne
    @JoinColumn(name="F_03", referencedColumnName = "F_00", foreignKey=@ForeignKey(name="T_01_01_06_FK_01"), nullable=false)
    private Shelf shelf;
}
