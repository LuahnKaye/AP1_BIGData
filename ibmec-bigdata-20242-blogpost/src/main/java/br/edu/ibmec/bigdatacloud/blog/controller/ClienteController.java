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

import br.edu.ibmec.bigdatacloud.blog.model.Cliente;
import br.edu.ibmec.bigdatacloud.blog.model.Endereco;
import br.edu.ibmec.bigdatacloud.blog.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente cliente) throws Exception { 
        clienteService.criaCliente(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.updateCliente(id, cliente);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.buscaCliente(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/{id}/endereco/{idEndereco}")
    public ResponseEntity<List<Endereco>> getEnderecosById(@PathVariable Long id, @PathVariable Long idEndereco) {
        Cliente cliente = clienteService.buscaCliente(id);
        return new ResponseEntity<>(cliente.getEnderecos(), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        clienteService.deletaCliente(id);
        return new ResponseEntity<>("Cliente deletado com sucesso", HttpStatus.OK);
    }
    
}
