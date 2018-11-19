package com.ibm.cambio.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente {
    @Id
    @GeneratedValue(generator="seq_cliente", strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(
            initialValue=1,
            allocationSize=1,
            name="seq_cliente",
            sequenceName ="seq_cliente")
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "cliente",
            targetEntity = Conta.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    
    private List<Conta> contas;

}
