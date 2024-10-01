package Luahn.client_manager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testAddressAttributes() {
        Address address = new Address();
        address.setId(1L);
        address.setRua("Rua Armando Lombardi");
        address.setNumero("940");
        address.setBairro("barra");
        address.setCidade("Rio de Janeiro");
        address.setEstado("RJ");
        address.setCep("22640-000");

        assertEquals(1L, address.getId());
        assertEquals("Rua Armando Lombardi", address.getRua());
        assertEquals("940", address.getNumero());
        assertEquals("barra", address.getBairro());
        assertEquals("Rio de Janeiro", address.getCidade());
        assertEquals("RJ", address.getEstado());
        assertEquals("22640-000", address.getCep());
    }
}