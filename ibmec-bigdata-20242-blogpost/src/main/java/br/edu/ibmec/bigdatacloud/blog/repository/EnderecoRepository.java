package br.edu.ibmec.bigdatacloud.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ibmec.bigdatacloud.blog.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findEnderecoByCep(String cep);
    boolean existsByCep(String cep);
}
