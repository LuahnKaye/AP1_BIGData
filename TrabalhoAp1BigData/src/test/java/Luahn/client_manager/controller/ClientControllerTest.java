package Luahn.client_manager.controller;

import Luahn.client_manager.model.Client;
import Luahn.client_manager.service.ClientService;
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

class ClientControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void testCreateClient() throws Exception {
        Client client = new Client();
        client.setNome("Test Client");

        when(clientService.addClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Test Client\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Test Client"));
    }

    @Test
    void testUpdateClient() throws Exception {
        Client updatedClient = new Client();
        updatedClient.setNome("Updated Client");

        when(clientService.updateClient(anyLong(), any(Client.class))).thenReturn(updatedClient);

        mockMvc.perform(put("/api/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Updated Client\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Updated Client"));
    }

    @Test
    void testGetAllClients() throws Exception {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());

        when(clientService.getAllClients()).thenReturn(clients);

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetClientById_Success() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setNome("Test Client");

        when(clientService.getClientById(1L)).thenReturn(Optional.of(client));

        mockMvc.perform(get("/api/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Test Client"));
    }

    @Test
    void testGetClientById_NotFound() throws Exception {
        when(clientService.getClientById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/clientes/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteClient() throws Exception {
        doNothing().when(clientService).deleteClient(1L);

        mockMvc.perform(delete("/api/clientes/1"))
                .andExpect(status().isNoContent());
    }
}
