package Luahn.client_manager.repository;

import Luahn.client_manager.model.Address;
import Luahn.client_manager.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setNome("Luahn");
        client.setEmail("Luahn@gmail.com");
        client.setCpf("123.456.789-01");
        client.setDataNascimento(LocalDate.of(2004, 10, 7));
        clientRepository.save(client);
    }

    @Test
    void testSaveAddress() {
        Address address = new Address();
        address.setRua("Rua Armando Lombardi");
        address.setNumero("940");
        address.setBairro("barra");
        address.setCidade("Rio de Janeiro");
        address.setEstado("RJ");
        address.setCep("22640-000");
        address.setCliente(client);

        Address savedAddress = addressRepository.save(address);

        assertNotNull(savedAddress.getId());
        assertEquals("Rua Armando Lombardi", savedAddress.getRua());
        assertEquals("940", savedAddress.getNumero());
        assertEquals(client.getId(), savedAddress.getCliente().getId());
    }

    @Test
    void testFindById_Success() {
        Address address = new Address();
        address.setRua("Rua Armando Lombardi");
        address.setNumero("940");
        address.setBairro("barra");
        address.setCidade("Rio de Janeiro");
        address.setEstado("RJ");
        address.setCep("22640-000");
        address.setCliente(client);
        addressRepository.save(address);

        Optional<Address> foundAddress = addressRepository.findById(address.getId());

        assertTrue(foundAddress.isPresent());
        assertEquals("Rua Armando Lombardi", foundAddress.get().getRua());
        assertEquals("940", foundAddress.get().getNumero());
    }

    @Test
    void testFindById_NotFound() {
        Optional<Address> foundAddress = addressRepository.findById(999L);
        assertFalse(foundAddress.isPresent());
    }
}
