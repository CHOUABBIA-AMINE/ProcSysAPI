/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowInstanceStatus
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Workflow / Shared
 *
 *	Description : Reference entity for workflow instance lifecycle statuses.
 *	              Represents the overall state of a running WorkflowInstance
 *	              (e.g. ACTIVE, COMPLETED, CANCELLED, SUSPENDED).
 *	              Values are managed in the database — no hardcoded enum.
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
@Entity(name = "WorkflowInstanceStatus")
@Table(
		name = "T_03_00_02",
		uniqueConstraints = {
				@UniqueConstraint(name = "T_03_00_02_UK_01", columnNames = { "F_01" }),
				@UniqueConstraint(name = "T_03_00_02_UK_02", columnNames = { "F_05" })
		})
public class WorkflowInstanceStatus extends GenericModel {

	@Column(name = "F_01", length = 60, nullable = false)
	private String code;

	@Column(name = "F_02", length = 300)
	private String designationAr;

	@Column(name = "F_03", length = 300)
	private String designationEn;

	@Column(name = "F_04", length = 300, nullable = false)
	private String designationFr;

	@Column(name = "F_05", length = 20, nullable = false)
	private String acronymFr;

	@Column(name = "F_06", length = 20)
	private String acronymEn;

	@Column(name = "F_07", length = 20)
	private String acronymAr;

	@Column(name = "F_08", length = 1000)
	private String description;

	@Column(name = "F_09", nullable = false)
	private boolean active = true;

}

/*
INSERT INTO T_03_00_02 (F_00, F_01, F_02, F_03, F_04, F_05, F_06, F_07, F_08, F_09) VALUES
(1, 'ACTIVE',    'نشط',    'Active',    'Actif',    'ACT', 'ACT', 'نشط',    'Instance en cours d execution',            1),
(2, 'COMPLETED', 'مكتمل',  'Completed', 'Terminé',  'TRM', 'CMP', 'مكتمل',  'Instance arrivée à l étape terminale',     1),
(3, 'CANCELLED', 'ملغى',   'Cancelled', 'Annulé',   'ANN', 'CAN', 'ملغى',   'Instance annulée avant completion',        1),
(4, 'SUSPENDED', 'موقوف',  'Suspended', 'Suspendu', 'SUS', 'SUS', 'موقوف',  'Instance suspendue en attente de reprise', 1);
*/
