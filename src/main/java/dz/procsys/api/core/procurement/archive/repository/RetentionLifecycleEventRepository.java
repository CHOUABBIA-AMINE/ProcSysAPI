package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.RetentionLifecycleEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetentionLifecycleEventRepository extends JpaRepository<RetentionLifecycleEvent, Long> {}
