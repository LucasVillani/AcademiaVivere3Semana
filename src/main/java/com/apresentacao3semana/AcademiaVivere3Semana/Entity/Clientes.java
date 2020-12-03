package com.apresentacao3semana.AcademiaVivere3Semana.Entity;

import com.apresentacao3semana.AcademiaVivere3Semana.auditing.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "clientes")
@EntityListeners(AuditingEntityListener.class)
public class Clientes extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer ID;

    @NotBlank
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "cpfCnpj", length = 14, nullable = false)
    private String cpfCnpj;

    @NotBlank
    @Column(name = "Logradouro", length = 50, nullable = false)
    private String Logradouro;

    @NotBlank
    @Column(name = "cidade", length = 40, nullable = false)
    private String cidade;

    @NotBlank
    @Column(name = "uf", length = 2, nullable = false, columnDefinition="char(8)")
    private String uf;

    @NotBlank
    @Column(name = "CEP", nullable = false, columnDefinition="char(8)")
    private String CEP;

    @Column(name = "Telefone", length = 11)
    private String Telefone;

    @Column(name = "Email", length = 100)
    private String Email;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
//    private List<Livro_Caixa> livros_caixa;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return uf;
    }

    public void setUF(String  uf) {
        this.uf = uf;
    }

    public String  getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
