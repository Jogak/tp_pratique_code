package org.sam.mines.address.persistence;


import org.sam.mines.address.model.CondamnationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CondamnationRepository extends JpaRepository<CondamnationEntity, UUID> {
    List<CondamnationEntity> findAllByNumber(int number);
    List<CondamnationEntity> findAllByComdamnationDurationDay(int duration);
    List<CondamnationEntity> findAllByReprieveDurationDay(int duration);
    List<CondamnationEntity> findAllByFine(float fine);
    /*
    *private UUID id;
    private int comdamnationDurationDay;
    private int reprieveDurationDay;
    private float fine;
    *  */
}
