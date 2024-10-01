package Luahn.client_manager.repository;

import Luahn.client_manager.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    private Long clientId;

    @BeforeEach
    void setUp() {
        Client client = new Client();
        client.setNome("Luahn");
        client.setEmail("Luahn@gmail.com");
        client.setCpf("123.456.789-01");
        client.setDataNascimento(LocalDate.of(2004, 10, 7));

        Client savedClient = clientRepository.save(client);
        clientId = savedClient.getId();
    }

    @Test
    void testFindById_Success() {
        Optional<Client> client = clientRepository.findById(clientId);
        assertTrue(client.isPresent());
        assertEquals("Luahn", client.get().getNome());
    }

    @Test
    void testExistsByEmail_True() {
        boolean exists = clientRepository.existsByEmail("Luahn@gmail.com");
        assertTrue(exists);
    }

    @Test
    void testExistsByEmail_False() {
        boolean exists = clientRepository.existsByEmail("gabriel@gmail.com");
        assertFalse(exists);
    }

    @Test
    void testExistsByCpf_True() {
        boolean exists = clientRepository.existsByCpf("123.456.789-01");
        assertTrue(exists);
    }

    @Test
    void testExistsByCpf_False() {
        boolean exists = clientRepository.existsByCpf("987.654.321-11");
        assertFalse(exists);
    }

    @Test
    void testSaveClient() {
        Client newClient = new Client();
        newClient.setNome("Rafael");
        newClient.setEmail("Rafael@gmail.com");
        newClient.setCpf("232.555.666-78");
        newClient.setDataNascimento(LocalDate.of(1992, 10, 5));

        Client savedClient = clientRepository.save(newClient);

        assertNotNull(savedClient.getId());
        assertEquals("Rafael", savedClient.getNome());
    }
}
