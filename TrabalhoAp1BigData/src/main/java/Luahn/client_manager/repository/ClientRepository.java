package Luahn.client_manager.repository;

import Luahn.client_manager.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}