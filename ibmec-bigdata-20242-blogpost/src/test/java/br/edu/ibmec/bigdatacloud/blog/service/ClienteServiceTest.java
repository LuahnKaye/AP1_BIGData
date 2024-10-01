package br.edu.ibmec.bigdatacloud.blog.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.ibmec.bigdatacloud.blog.model.Cliente;
import br.edu.ibmec.bigdatacloud.blog.exception.ClienteException;
import br.edu.ibmec.bigdatacloud.blog.repository.ClienteRepository;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deve_criar_cliente() throws ClienteException {
        Cliente cliente = new Cliente();
        cliente.setCpf("142.753.940-54");

        when(clienteRepository.findClienteByCpf(cliente.getCpf())).thenReturn(Optional.empty());
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.criaCliente(cliente);

        assertNotNull(result);
        verify(clienteRepository).save(cliente);
    }

    @Test
    public void deve_nao_criar_cliente_porque_cliente_ja_esta_cadastrado() {
        Cliente cliente = new Cliente();
        cliente.setCpf("142.753.940-54");

        when(clienteRepository.findClienteByCpf(cliente.getCpf())).thenReturn(Optional.of(cliente));

        assertThrows(ClienteException.class, () -> clienteService.criaCliente(cliente));
        verify(clienteRepository, never()).save(cliente);
    }

    @Test
    public void deve_obter_todos_os_clientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente());
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.getAllClientes();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void deve_deletar_cliente() {
        Long clienteId = 9999L;
        when(clienteRepository.existsById(clienteId)).thenReturn(true);

        clienteService.deletaCliente(clienteId);

        verify(clienteRepository).deleteById(clienteId);
    }

    @Test
    public void deve_nao_deletar_cliente_porque_nao_foi_encontrado() {
        Long clienteId = 9999L;
        when(clienteRepository.existsById(clienteId)).thenReturn(false);

        assertThrows(ClienteException.class, () -> clienteService.deletaCliente(clienteId));
        verify(clienteRepository, never()).deleteById(clienteId);
    }
}
