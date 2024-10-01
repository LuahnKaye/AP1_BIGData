package br.edu.ibmec.bigdatacloud.blog.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.ibmec.bigdatacloud.blog.model.Endereco;
import br.edu.ibmec.bigdatacloud.blog.exception.EnderecoException;
import br.edu.ibmec.bigdatacloud.blog.repository.EnderecoRepository;

public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deve_criar_endereco() throws EnderecoException {
        Endereco endereco = new Endereco();
        endereco.setCep("40720-239");

        when(enderecoRepository.existsByCep(endereco.getCep())).thenReturn(false);
        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        Endereco result = enderecoService.criaEndereco(endereco, null);

        assertNotNull(result);
        verify(enderecoRepository).save(endereco);
    }

    @Test
    public void deve_nao_criar_endereco_porque_endereco_ja_esta_cadastrado() {
        Endereco endereco = new Endereco();
        endereco.setCep("40720-239");

        when(enderecoRepository.existsByCep(endereco.getCep())).thenReturn(true);

        assertThrows(EnderecoException.class, () -> enderecoService.criaEndereco(endereco, null));
        verify(enderecoRepository, never()).save(endereco);
    }

    @Test
    public void deve_deletar_endereco() {
        Long enderecoId = 9999L;
        when(enderecoRepository.existsById(enderecoId)).thenReturn(true);

        enderecoService.deletaEndereco(enderecoId);

        verify(enderecoRepository).deleteById(enderecoId);
    }

    @Test
    public void deve_nao_deletar_endereco_porque_nao_foi_encontrado() {
        Long enderecoId = 9999L;
        when(enderecoRepository.existsById(enderecoId)).thenReturn(false);

        assertThrows(EnderecoException.class, () -> enderecoService.deletaEndereco(enderecoId));
        verify(enderecoRepository, never()).deleteById(enderecoId);
    }
}
