package com.ibm.cambio.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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
    private String tipoConta;

    @ManyToOne(targetEntity = Cliente.class)
        private Cliente cliente;


    public Short getAgencia() {
        return agencia;
    }

    public void setAgencia(Short agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
