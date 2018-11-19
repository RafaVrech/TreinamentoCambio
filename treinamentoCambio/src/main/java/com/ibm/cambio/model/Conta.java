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
    private Double saldo;
    private String tipoConta;

    @ManyToOne(targetEntity = Cliente.class)
        private Cliente cliente;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

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
