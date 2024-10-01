package br.edu.ibmec.bigdatacloud.blog.controller;

import br.edu.ibmec.bigdatacloud.blog.model.Cliente;
import br.edu.ibmec.bigdatacloud.blog.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Fulano");
        cliente.setCpf("674.744.940-19");
        cliente.setEmail("fulano@exemplo.com");
        cliente.setTelefone("(24) 93414-2342");
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
    }

    @Test
    public void should_create_cliente() throws Exception {
        when(clienteService.criaCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Fulano"));
    }

    @Test
    public void should_get_all_clientes() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);

        when(clienteService.getAllClientes()).thenReturn(clientes);

        mockMvc.perform(get("/cliente")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Fulano"));
    }

    @Test
    public void should_get_cliente_by_id() throws Exception {
        when(clienteService.buscaCliente(1L)).thenReturn(cliente);

        mockMvc.perform(get("/cliente/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Fulano"));
    }

    @Test
    public void should_update_cliente() throws Exception {
        Cliente updatedCliente = new Cliente();
        updatedCliente.setId(1);
        updatedCliente.setNome("Beltrano");

        when(clienteService.updateCliente(eq(1L), any(Cliente.class))).thenReturn(updatedCliente);

        mockMvc.perform(put("/cliente/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Beltrano"));
    }

    @Test
    public void should_delete_cliente() throws Exception {
        doNothing().when(clienteService).deletaCliente(1L);

        mockMvc.perform(delete("/cliente/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente deletado com sucesso"));
    }
}
