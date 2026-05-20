package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.ArchiveFolder;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArchiveFolderRepository extends JpaRepository<ArchiveFolder, Long> {
    boolean existsByCode(String code);
    boolean existsByCodeAndIdNot(String code, Long id);
    Optional<ArchiveFolder> findByCode(String code);

    @Query("""
        select af
          from ArchiveFolder af
          join af.archiveBox ab
          join ab.shelf s
          join s.room r
          join r.facility f
         where (:code is null or lower(af.code) like lower(concat('%', :code, '%')))
           and (:facilityCode is null or lower(f.code) = lower(:facilityCode))
           and (:roomCode is null or lower(r.code) = lower(:roomCode))
           and (:shelfCode is null or lower(s.code) = lower(:shelfCode))
           and (:boxCode is null or lower(ab.code) = lower(:boxCode))
    """)
    Page<ArchiveFolder> search(@Param("code") String code,
                               @Param("facilityCode") String facilityCode,
                               @Param("roomCode") String roomCode,
                               @Param("shelfCode") String shelfCode,
                               @Param("boxCode") String boxCode,
                               Pageable pageable);
}
