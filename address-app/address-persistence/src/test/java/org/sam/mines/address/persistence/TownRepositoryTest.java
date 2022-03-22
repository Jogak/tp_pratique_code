package org.sam.mines.address.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sam.mines.address.model.AddressEntity;
import org.sam.mines.address.model.TownEntity;
import org.sam.mines.address.persistence.config.PersistenceTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners({SqlScriptsTestExecutionListener.class, TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(classes = {PersistenceTestConfig.class})
@Sql(scripts = {"/data/clear.sql", "/data/town.sql"})
class TownRepositoryTest {

    @Autowired
    private TownRepository townRepository;

    @Test
    public void shouldFindAll() {
        List<TownEntity> all = townRepository.findAll();

        assertEquals(1, all.size());
    }

    @Test
    public void shouldFindAllByName() {
        assertEquals(1, townRepository.findAllByName("Montpellier").size());

        Collection<TownEntity> montpellier = townRepository.findAllByNameAndPostCode("Montpellier", 34000);
        assertEquals(1, montpellier.size());
    }

    @Test
    @Transactional
    public void shouldShowGraph() {
        TownEntity town = townRepository.getOne(UUID.fromString("fb74d086-5a4a-11e7-907b-a6006ad3dba0"));
        town.getAddresses().forEach(System.out::println);
        town.getAddresses().stream().map(AddressEntity::getTargets).forEach(System.out::println);
    }

    @Test
    public void shouldFindByPostCode() {

        List<TownEntity> byPostCode = townRepository.findByPostCode(34000);

        assertEquals(1, byPostCode.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void shouldSave() {
        TownEntity one = townRepository.getOne(UUID.fromString("fb74d086-5a4a-11e7-907b-a6006ad3dba0"));
        String name = one.getName();
        one.setName("modified1");
        one.setName("modified2");
        one.setName("modified");

        one.setName(name);
    }
}
