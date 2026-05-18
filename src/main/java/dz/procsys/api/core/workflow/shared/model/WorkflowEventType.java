/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowEventType
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Workflow / Shared
 *
 *	Description : Reference entity for workflow process event types.
 *	              Classifies each immutable ProcessEvent record by the
 *	              nature of the transition that produced it
 *	              (e.g. WORKFLOW_STARTED, STEP_ENTERED, STEP_COMPLETED,
 *	               TRANSITION_FIRED, WORKFLOW_COMPLETED, WORKFLOW_CANCELLED).
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
@Entity(name = "WorkflowEventType")
@Table(
		name = "T_03_00_04",
		uniqueConstraints = {
				@UniqueConstraint(name = "T_03_00_04_UK_01", columnNames = { "F_01" }),
				@UniqueConstraint(name = "T_03_00_04_UK_02", columnNames = { "F_05" })
		})
public class WorkflowEventType extends GenericModel {

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
INSERT INTO T_03_00_04 (F_00, F_01, F_02, F_03, F_04, F_05, F_06, F_07, F_08, F_09) VALUES
(1, 'WORKFLOW_STARTED',   'بدء المسار',           'Workflow Started',   'Démarrage du circuit',      'DEM', 'WKS', 'بدء',    'Instance créée et premier pas activé',           1),
(2, 'STEP_ENTERED',       'دخول الخطوة',          'Step Entered',       'Entrée dans une étape',     'ENT', 'STE', 'دخول',   'Un acteur prend en charge une étape',            1),
(3, 'STEP_COMPLETED',     'اكتمال الخطوة',        'Step Completed',     'Complétion d une étape',    'CMP', 'STC', 'اكتمال', 'Etape validée par l acteur responsable',         1),
(4, 'TRANSITION_FIRED',   'تفعيل الانتقال',       'Transition Fired',   'Transition déclenchée',     'TRA', 'TRF', 'انتقال', 'Règle de transition évaluée et appliquée',       1),
(5, 'WORKFLOW_COMPLETED', 'اكتمال المسار',        'Workflow Completed', 'Circuit terminé',           'TRM', 'WKC', 'اكتمل',  'Instance arrivée à l étape terminale',           1),
(6, 'WORKFLOW_CANCELLED', 'إلغاء المسار',         'Workflow Cancelled', 'Circuit annulé',            'ANN', 'WKX', 'إلغاء',  'Instance annulée avant atteindre l étape finale',1);
*/
