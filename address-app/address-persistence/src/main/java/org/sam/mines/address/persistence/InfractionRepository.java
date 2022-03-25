package org.sam.mines.address.persistence;

import org.sam.mines.address.model.CondamnationEntity;
import org.sam.mines.address.model.InfractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface InfractionRepository extends JpaRepository<InfractionEntity, UUID> {

    List<InfractionEntity> findAllByNumber(int number);
    List<InfractionEntity> findAllByPlace(String place);
    List<InfractionEntity> findAllByMobile(String mobile);
    List<InfractionEntity> findAllByDescription(String description);
    //List<InfractionEntity> findAll();
}
