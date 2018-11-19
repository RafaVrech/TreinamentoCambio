package com.ibm.cambio.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(generator="seq_contato", strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(
            initialValue=1,
            allocationSize=1,
            name="seq_contato",
            sequenceName ="seq_contato")

    private Long id;
    private String nome;

    @OneToMany(mappedBy = "cliente",
            targetEntity = Conta.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Conta> contas;

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente() {
    }*/
}
