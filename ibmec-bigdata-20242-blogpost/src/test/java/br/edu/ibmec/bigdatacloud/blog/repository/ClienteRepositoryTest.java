package br.edu.ibmec.bigdatacloud.blog.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.edu.ibmec.bigdatacloud.blog.model.Cliente;

@DataJpaTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Long clienteId;

    @BeforeEach
    void setUp() {
        Cliente cliente = new Cliente();
        cliente.setNome("Derek Jeter");
        cliente.setEmail("derek.jeter@example.com");
        cliente.setCpf("142.753.940-54");
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));

        Cliente salvaCliente = clienteRepository.save(cliente);
        clienteId = (long) salvaCliente.getId();
    }

    @Test
    void deve_passar_quando_encontrar_por_id() {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        assertTrue(cliente.isPresent());
        assertEquals("Derek Jeter", cliente.get().getNome());
    }

    @Test
    void deve_falhar_quando_encontrar_por_id() {
        Optional<Cliente> cliente = clienteRepository.findById(9999L);
        assertFalse(cliente.isPresent());
    }

    @Test
    void deve_passar_quando_existir_por_email() {
        assertTrue(clienteRepository.existsByEmail("derek.jeter@example.com"));
    }

    @Test
    void deve_falhar_quando_existir_por_email() {
        assertFalse(clienteRepository.existsByEmail("unknown@example.com"));
    }

    @Test
    void deve_passar_quando_existir_por_cpf() {
        assertTrue(clienteRepository.existsByCpf("142.753.940-54"));
    }

    @Test
    void deve_falhar_quando_existir_por_cpf() {
        assertFalse(clienteRepository.existsByCpf("987.654.321-00"));
    }

    @Test
    void deve_salvar_cliente() {
        Cliente newCliente = new Cliente();
        newCliente.setNome("Jane Doe");
        newCliente.setEmail("jane.doe@example.com");
        newCliente.setCpf("987.654.321-00");
        newCliente.setDataNascimento(LocalDate.of(1995, 5, 5));

        Cliente salvaCliente = clienteRepository.save(newCliente);

        assertNotNull(salvaCliente.getId());
        assertEquals("Jane Doe", salvaCliente.getNome());
    }
}
