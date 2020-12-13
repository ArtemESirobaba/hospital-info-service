package net.kickit.hospitalinfoservice.repository;

import net.kickit.hospitalinfoservice.entity.FacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacilityRepository extends JpaRepository<FacilityEntity, Integer > {
    List<FacilityEntity> findByRecordId(Integer id);
    void deleteAllByRecordId(Integer id);
}
