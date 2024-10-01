package gvfirmeza.client_manager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testAddressAttributes() {
        Address address = new Address();
        address.setId(1L);
        address.setRua("Rua Teste");
        address.setNumero("123");
        address.setBairro("Centro");
        address.setCidade("São Paulo");
        address.setEstado("SP");
        address.setCep("12345-678");

        assertEquals(1L, address.getId());
        assertEquals("Rua Teste", address.getRua());
        assertEquals("123", address.getNumero());
        assertEquals("Centro", address.getBairro());
        assertEquals("São Paulo", address.getCidade());
        assertEquals("SP", address.getEstado());
        assertEquals("12345-678", address.getCep());
    }
}