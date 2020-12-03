package com.apresentacao3semana.AcademiaVivere3Semana.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Cascade;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.naming.Name;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "livros_caixa")
@EntityListeners(AuditingEntityListener.class)
public class Livro_Caixa implements Comparable<Livro_Caixa>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer ID;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "clienteId",nullable = false)
    private Clientes cliente;

    @Column(name = "data_lancamento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY")
    private Date data_lancamento;

    @NotBlank
    @Column(name = "Descricao", length = 50, nullable = false)
    private String Descricao;

    @Column(name = "Tipo", length = 1, nullable = false)
    private Character Tipo;

    @Column(name = "Valor", nullable = false, columnDefinition="decimal(12,2)")
    private Double Valor;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Date getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(Date data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Character getTipo() {
        return Tipo;
    }

    public void setTipo(Character tipo) {
        Tipo = tipo;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }


    public int compareTo(Livro_Caixa aux) {
        if (this.getData_lancamento().before(aux.getData_lancamento())) {
            return -1;
        }
        return 1;
    }

}
