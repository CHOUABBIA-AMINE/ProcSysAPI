package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.ArchiveLocation;
import dz.procsys.api.core.procurement.archive.repository.projection.ArchiveLocationSearchView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArchiveLocationRepository extends JpaRepository<ArchiveLocation, Long> {

    Optional<ArchiveLocation> findByCode(String code);

    @Query("""
        select l.id as id,
               l.code as code,
               f.code as facilityCode,
               r.code as roomCode,
               s.code as shelfCode,
               sl.code as shelfLevelCode
          from ArchiveLocation l
          join l.shelfLevel sl
          join sl.shelf s
          join s.room r
          join r.facility f
         where (:q is null or lower(l.code) like lower(concat('%', :q, '%')))
           and (:facilityCode is null or f.code = :facilityCode)
           and (:roomCode is null or r.code = :roomCode)
           and (:shelfCode is null or s.code = :shelfCode)
    """)
    Page<ArchiveLocationSearchView> searchPlacement(
        @Param("q") String q,
        @Param("facilityCode") String facilityCode,
        @Param("roomCode") String roomCode,
        @Param("shelfCode") String shelfCode,
        Pageable pageable
    );
}
