package br.edu.ibmec.bigdatacloud.blog.controller;

import br.edu.ibmec.bigdatacloud.blog.model.Endereco;
import br.edu.ibmec.bigdatacloud.blog.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class EnderecoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EnderecoService enderecoService;

    @InjectMocks
    private EnderecoController enderecoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(enderecoController).build();
    }

    @Test
    public void shouldCreateEndereco() throws Exception {
        Long clienteId = 1L;
        Endereco endereco = new Endereco();
        endereco.setRua("Rua A");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("Cidade X");
        endereco.setEstado("Estado Y");
        endereco.setCep("12345-678");

        when(enderecoService.criaEndereco(any(Endereco.class), eq(clienteId))).thenReturn(endereco);

        mockMvc.perform(post("/endereco/{clienteId}", clienteId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(endereco)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.rua").value("Rua A"))
                .andExpect(jsonPath("$.numero").value("123"));
        
        verify(enderecoService, times(1)).criaEndereco(any(Endereco.class), eq(clienteId));
    }

    @Test
    public void shouldGetAllEnderecos() throws Exception {
        Endereco endereco1 = new Endereco();
        endereco1.setRua("Rua A");
        endereco1.setNumero("123");
        endereco1.setBairro("Centro");
        endereco1.setCidade("Cidade X");
        endereco1.setEstado("Estado Y");
        endereco1.setCep("12345-678");

        Endereco endereco2 = new Endereco();
        endereco2.setRua("Rua B");
        endereco2.setNumero("456");
        endereco2.setBairro("Bairro Y");
        endereco2.setCidade("Cidade Z");
        endereco2.setEstado("Estado W");
        endereco2.setCep("98765-432");

        List<Endereco> enderecos = Arrays.asList(endereco1, endereco2);

        when(enderecoService.getAllEnderecos()).thenReturn(enderecos);

        mockMvc.perform(get("/endereco")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rua").value("Rua A"))
                .andExpect(jsonPath("$[1].rua").value("Rua B"));

        verify(enderecoService, times(1)).getAllEnderecos();
    }

    @Test
    public void should_update_endereco() throws Exception {
        Long enderecoId = 1L;
        Endereco endereco = new Endereco();
        endereco.setRua("Rua A");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("Cidade X");
        endereco.setEstado("Estado Y");
        endereco.setCep("12345-678");

        when(enderecoService.updateEndereco(eq(enderecoId), any(Endereco.class))).thenReturn(endereco);

        mockMvc.perform(put("/endereco/{id}", enderecoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(endereco)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rua").value("Rua A"))
                .andExpect(jsonPath("$.numero").value("123"));

        verify(enderecoService, times(1)).updateEndereco(eq(enderecoId), any(Endereco.class));
    }

    @Test
    public void should_get_endereco_by_id() throws Exception {
        Long enderecoId = 1L;
        Endereco endereco = new Endereco();
        endereco.setRua("Rua A");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("Cidade X");
        endereco.setEstado("Estado Y");
        endereco.setCep("12345-678");

        when(enderecoService.buscaEndereco(enderecoId)).thenReturn(endereco);

        mockMvc.perform(get("/endereco/{id}", enderecoId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rua").value("Rua A"))
                .andExpect(jsonPath("$.numero").value("123"));

        verify(enderecoService, times(1)).buscaEndereco(enderecoId);
    }

    @Test
    public void should_delete_endereco() throws Exception {
        Long enderecoId = 1L;

        doNothing().when(enderecoService).deletaEndereco(enderecoId);

        mockMvc.perform(delete("/endereco/{id}", enderecoId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Endereço deletado com sucesso"));

        verify(enderecoService, times(1)).deletaEndereco(enderecoId);
    }

    // Utility method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}