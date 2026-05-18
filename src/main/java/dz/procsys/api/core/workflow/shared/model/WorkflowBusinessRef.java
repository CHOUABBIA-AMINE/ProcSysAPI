/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowBusinessRef
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Workflow / Shared
 *
 *	Description : Lightweight reference to a business aggregate participating
 *	              in a workflow (Consultation, Contract, Amendment, Plan, ...).
 *	              Avoids loading full aggregate graphs while keeping a typed
 *	              identifier and a human-readable label.
 *
 **/

package dz.procsys.api.core.workflow.shared.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WorkflowBusinessRef")
@Table(
		name = "T_03_00_05",
		uniqueConstraints = {
				@UniqueConstraint(name = "T_03_00_05_UK_01", columnNames = { "F_01", "F_02" })
		})
public class WorkflowBusinessRef extends GenericModel {

	/**
	 * Technical identifier of the business aggregate.
	 * Stored as String to remain polymorphic across Consultation, Contract, etc.
	 */
	@Column(name = "F_01", length = 100, nullable = false)
	private String businessId;

	/**
	 * Type of business aggregate: CONSULTATION, CONTRACT, AMENDMENT, PLAN, ...
	 * Should match a WorkflowProcessType.code when the aggregate is bound to
	 * a workflow definition.
	 */
	@Column(name = "F_02", length = 60, nullable = false)
	private String businessType;

	/**
	 * Human readable label (e.g. consultation number, contract reference).
	 */
	@Column(name = "F_03", length = 300)
	private String label;

}

/*
-- Example insert values (to be managed by application/services):
-- INSERT INTO T_03_00_05 (F_00, F_01, F_02, F_03) VALUES
-- (1, '42', 'CONSULTATION', 'CONS-2026-00042'),
-- (2, '10', 'CONTRACT',     'CTR-2026-00010');
*/
