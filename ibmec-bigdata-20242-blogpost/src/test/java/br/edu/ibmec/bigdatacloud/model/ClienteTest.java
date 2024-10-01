import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testClientAttributes() {
        Client client = new Client();
        client.setId(1L);
        client.setNome("John Doe");
        client.setEmail("john.doe@example.com");
        client.setCpf("123.456.789-00");
        client.setDataNascimento(LocalDate.of(1990, 1, 1));
        client.setTelefone("(11) 91234-5678");

        assertEquals(1L, client.getId());
        assertEquals("John Doe", client.getNome());
        assertEquals("john.doe@example.com", client.getEmail());
        assertEquals("123.456.789-00", client.getCpf());
        assertEquals(LocalDate.of(1990, 1, 1), client.getDataNascimento());
        assertEquals("(11) 91234-5678", client.getTelefone());
    }
}