package com.example.devsuservice.unitTest;

import com.example.devsuservice.models.Client;
import com.example.devsuservice.repositories.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void saveClient() {

        Client cl = new Client();
        cl.setName("Juan Vergara");
        cl.setAddress("Av. Pensilvania");
        cl.setPhone("09943000");
        cl.setStatusPassword(1234);

        Client savedClient =  this.entityManager.persistAndFlush(cl);

        assertThat(savedClient.getId()).isNotNull();
        assertThat(savedClient.getName()).isEqualTo("Juan Vergara");
        assertThat(savedClient.getStatusPassword()).isEqualTo(1234);
    }

}
