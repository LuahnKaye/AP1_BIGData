package br.edu.ibmec.bigdatacloud.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // Anotação do Lombok para Criação de Getters e Setters
@Entity // Anotação para incar Criação de Tabela no Banco de Dados
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    @NotBlank(message = "Rua é obrigatório")
    @Size(min = 3, max = 255, message = "Rua deve ter entre 3 e 255 caracteres")
    private String rua;

    @Column
    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @Column
    @NotBlank(message = "Bairro é obrigatório")
    @Size(min = 3, max = 100, message = "Bairro deve ter entre 3 e 100 caracteres")
    private String bairro;
    
    @Column
    @NotBlank(message = "Cidade é obrigatório")
    @Size(min = 2, max = 255, message = "Cidade deve ter entre 2 e 100 caracteres")
    private String cidade;

    @Column
    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    @Column
    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve seguir o formato XXXXX-XXX")
    private String cep;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

}
