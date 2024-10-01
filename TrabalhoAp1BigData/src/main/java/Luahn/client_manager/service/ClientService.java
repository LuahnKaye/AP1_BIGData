package Luahn.client_manager.service;
import Luahn.client_manager.exceptions.*;
import Luahn.client_manager.model.Client;
import Luahn.client_manager.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client addClient(@Valid Client client) {
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new EmailAlreadyInUseException("O email já está em uso.");
        }
        if (clientRepository.existsByCpf(client.getCpf())) {
            throw new CpfAlreadyInUseException("O CPF já está em uso.");
        }

        if (Period.between(client.getDataNascimento(), LocalDate.now()).getYears() < 18) {
            throw new UnderageClientException("O cliente deve ter no mínimo 18 anos.");
        }
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, @Valid Client clientDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado com o id: " + id));
        client.setNome(clientDetails.getNome());
        client.setEmail(clientDetails.getEmail());
        client.setCpf(clientDetails.getCpf());
        client.setDataNascimento(clientDetails.getDataNascimento());
        client.setTelefone(clientDetails.getTelefone());
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public void deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new ClientNotFoundException("Cliente não encontrado com o id: " + id);
        }
    }
}