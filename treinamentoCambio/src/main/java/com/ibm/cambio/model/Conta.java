package com.ibm.cambio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude="cliente")
public class Conta {
    @Id
    @GeneratedValue(generator="seq_conta", strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(
            initialValue=1,
            allocationSize=1,
            name="seq_conta",
            sequenceName ="seq_conta")
    private Long id;
    private Short agencia;
    private String numero;
    private Double saldo;
    private String tipoConta;

    @ManyToOne(targetEntity = Cliente.class)
    private Cliente cliente;
}
