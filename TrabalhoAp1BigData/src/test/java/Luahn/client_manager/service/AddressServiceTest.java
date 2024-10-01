package Luahn.client_manager.service;

import Luahn.client_manager.exceptions.AddressNotFoundException;
import Luahn.client_manager.exceptions.ClientNotFoundException;
import Luahn.client_manager.model.Address;
import Luahn.client_manager.model.Client;
import Luahn.client_manager.repository.AddressRepository;
import Luahn.client_manager.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAddress_Success() {
        Client client = new Client();
        client.setId(1L);

        Address address = new Address();
        address.setRua("Rua Armando Lombardi");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address savedAddress = addressService.addAddress(1L, address);

        assertNotNull(savedAddress);
        assertEquals("Rua Armando Lombardi", savedAddress.getRua());
        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void testAddAddress_ClientNotFound() {
        Address address = new Address();

        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> addressService.addAddress(1L, address));
        verify(addressRepository, never()).save(any(Address.class));
    }

    @Test
    void testUpdateAddress_Success() {
        Address existingAddress = new Address();
        existingAddress.setId(1L);
        existingAddress.setRua("A rua");

        Address updatedAddress = new Address();
        updatedAddress.setRua("Rua atualizada");
        updatedAddress.setNumero("940");

        when(addressRepository.findById(1L)).thenReturn(Optional.of(existingAddress));
        when(addressRepository.save(any(Address.class))).thenReturn(updatedAddress);

        Address result = addressService.updateAddress(1L, updatedAddress);

        assertNotNull(result);
        assertEquals("Rua atualizada", result.getRua());
        assertEquals("940", result.getNumero());
        verify(addressRepository, times(1)).save(existingAddress);
    }

    @Test
    void testUpdateAddress_AddressNotFound() {
        Address address = new Address();
        address.setRua("Rua atualizada");

        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundException.class, () -> addressService.updateAddress(1L, address));
        verify(addressRepository, never()).save(any(Address.class));
    }

    @Test
    void testGetAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address());

        when(addressRepository.findAll()).thenReturn(addresses);

        List<Address> result = addressService.getAllAddresses();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void testGetAddressById_Success() {
        Address address = new Address();
        address.setId(1L);
        address.setRua("Rua Armando Lombardi");

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        Optional<Address> result = addressService.getAddressById(1L);

        assertTrue(result.isPresent());
        assertEquals("Rua Armando Lombardi", result.get().getRua());
        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAddressById_AddressNotFound() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Address> result = addressService.getAddressById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteAddress_Success() {
        when(addressRepository.existsById(1L)).thenReturn(true);

        addressService.deleteAddress(1L);

        verify(addressRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteAddress_AddressNotFound() {
        when(addressRepository.existsById(1L)).thenReturn(false);

        assertThrows(AddressNotFoundException.class, () -> addressService.deleteAddress(1L));
        verify(addressRepository, never()).deleteById(1L);
    }
}