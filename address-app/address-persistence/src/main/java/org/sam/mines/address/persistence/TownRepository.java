package org.sam.mines.address.persistence;

import org.sam.mines.address.model.TownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface TownRepository extends JpaRepository<TownEntity, UUID> {
    Collection<TownEntity> findAllByName(String name);

    Collection<TownEntity> findAllByNameAndPostCode(String name, int postCode);

    List<TownEntity> findByPostCode(int postcode);

    @Query(nativeQuery = true, value = "SELECT t FROM Town t WHERE t.name = :name")
    Collection<TownEntity> anotherFindByNameMethod(@Param("name") String name);
}
