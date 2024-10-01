package Luahn.client_manager.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testClientAttributes() {
        Client client = new Client();
        client.setId(1L);
        client.setNome("Luahn");
        client.setEmail("Luahn@gmail.com");
        client.setCpf("123.456.789-01");
        client.setDataNascimento(LocalDate.of(2004, 10, 7));
        client.setTelefone("(21) 99999-8888");

        assertEquals(1L, client.getId());
        assertEquals("Luahn", client.getNome());
        assertEquals("Luahn@gmail.com", client.getEmail());
        assertEquals("123.456.789-01", client.getCpf());
        assertEquals(LocalDate.of(2004, 10, 7), client.getDataNascimento());
        assertEquals("(21) 99999-8888", client.getTelefone());
    }
}