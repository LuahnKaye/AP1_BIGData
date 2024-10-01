package Luahn.client_manager.controller;

import Luahn.client_manager.model.Address;
import Luahn.client_manager.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AddressControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AddressController addressController;

    @Mock
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
    }

    @Test
    void testCreateAddress() throws Exception {
        Address address = new Address();
        address.setRua("Rua Teste");

        when(addressService.addAddress(anyLong(), any(Address.class))).thenReturn(address);

        mockMvc.perform(post("/api/enderecos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rua\":\"Rua Teste\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rua").value("Rua Teste"));
    }

    @Test
    void testUpdateAddress() throws Exception {
        Address updatedAddress = new Address();
        updatedAddress.setRua("Updated Rua");

        when(addressService.updateAddress(anyLong(), any(Address.class))).thenReturn(updatedAddress);

        mockMvc.perform(put("/api/enderecos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rua\":\"Updated Rua\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rua").value("Updated Rua"));
    }

    @Test
    void testGetAllAddresses() throws Exception {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address());

        when(addressService.getAllAddresses()).thenReturn(addresses);

        mockMvc.perform(get("/api/enderecos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetAddressById_Success() throws Exception {
        Address address = new Address();
        address.setId(1L);
        address.setRua("Rua Teste");

        when(addressService.getAddressById(1L)).thenReturn(Optional.of(address));

        mockMvc.perform(get("/api/enderecos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rua").value("Rua Teste"));
    }

    @Test
    void testGetAddressById_NotFound() throws Exception {
        when(addressService.getAddressById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/enderecos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteAddress() throws Exception {
        doNothing().when(addressService).deleteAddress(1L);

        mockMvc.perform(delete("/api/enderecos/1"))
                .andExpect(status().isNoContent());
    }
}