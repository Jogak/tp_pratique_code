package org.sam.mines.address.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sam.mines.address.model.AddressEntity;
import org.sam.mines.address.model.PersonEntity;
import org.sam.mines.address.model.TownEntity;
import org.sam.mines.address.persistence.TownRepository;
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
@Sql(scripts = {"/data/clear.sql", "/data/person.sql"})
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldFindAll() {
        List<PersonEntity> all = personRepository.findAll();

        assertEquals(1, all.size());
    }
}

    /*@Test
    public void findAllByFirstName() {

        assertEquals(1, personRepository.findAllByFirstname()ame("Julia").size());

    }

    @Test
    public void findAllByLastName() {

        assertEquals(1, personRepository.findAllByLastName("Locatelli").size());

    }

    @Test
    public void findAllByPhoneNumber() {

        assertEquals(1, personRepository.findAllByPhoneNumber("064711XXXX").size());

    }

    @Test
    public void findAllByAddress() {

        assertEquals(1, personRepository.findAllByAddress("2 rue du chateau").size());

    }*/