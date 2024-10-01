package br.edu.ibmec.bigdatacloud.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ibmec.bigdatacloud.blog.model.Endereco;
import br.edu.ibmec.bigdatacloud.blog.service.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/{clienteId}")
    public ResponseEntity<Endereco> criarEndereco(@PathVariable Long clienteId, @Valid @RequestBody Endereco endereco) throws Exception {
        enderecoService.criaEndereco(endereco, clienteId);
        return new ResponseEntity<>(endereco, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> getAllEnderecos() {
        List<Endereco> enderecos = enderecoService.getAllEnderecos();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        Endereco enderecoAtualizado = enderecoService.updateEndereco(id, endereco);
        return new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getEnderecoById(@PathVariable Long id) {
        Endereco endereco = enderecoService.buscaEndereco(id);
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEndereco(@PathVariable Long id) {
        enderecoService.deletaEndereco(id);
        return new ResponseEntity<>("Endereço deletado com sucesso", HttpStatus.OK);
    }

}
