package com.fin.control.dto;

import com.fin.control.entities.PessoaEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class PessoaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min=5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    private String cpf;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public PessoaDTO(PessoaEntity obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
    }

    public PessoaDTO(Integer id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }
}
