/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowStepStatus
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Workflow / Shared
 *
 *	Description : Reference entity for workflow step execution statuses.
 *	              Represents the execution state of a single WorkflowStepInstance
 *	              (e.g. PENDING, IN_PROGRESS, COMPLETED, SKIPPED, REJECTED).
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
@Entity(name = "WorkflowStepStatus")
@Table(
		name = "T_03_00_03",
		uniqueConstraints = {
				@UniqueConstraint(name = "T_03_00_03_UK_01", columnNames = { "F_01" }),
				@UniqueConstraint(name = "T_03_00_03_UK_02", columnNames = { "F_05" })
		})
public class WorkflowStepStatus extends GenericModel {

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
INSERT INTO T_03_00_03 (F_00, F_01, F_02, F_03, F_04, F_05, F_06, F_07, F_08, F_09) VALUES
(1, 'PENDING',     'في الانتظار', 'Pending',     'En attente',       'ATT', 'PND', 'انتظار', 'Etape créée, en attente d activation',      1),
(2, 'IN_PROGRESS', 'قيد التنفيذ', 'In Progress', 'En cours',         'ENC', 'INP', 'تنفيذ',  'Etape active et en cours de traitement',     1),
(3, 'COMPLETED',   'مكتمل',      'Completed',   'Complété',         'CMP', 'CMP', 'مكتمل',  'Etape terminée avec succès',                 1),
(4, 'SKIPPED',     'متجاوز',     'Skipped',     'Ignoré',           'IGN', 'SKP', 'تجاوز',  'Etape ignorée selon la règle de transition', 1),
(5, 'REJECTED',    'مرفوض',      'Rejected',    'Rejeté / Retourné','REJ', 'REJ', 'رفض',    'Etape rejetée par l acteur responsable',     1);
*/
