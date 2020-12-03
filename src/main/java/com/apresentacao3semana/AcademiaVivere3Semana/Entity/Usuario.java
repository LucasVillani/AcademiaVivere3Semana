package com.apresentacao3semana.AcademiaVivere3Semana.Entity;

import com.apresentacao3semana.AcademiaVivere3Semana.auditing.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)
public class Usuario extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer ID;

    @NotBlank
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "Login", length = 15, nullable = false, unique = true)
    private String login;

    @NotBlank
    @Column(name = "Senha", length = 10, nullable = false)
    private String senha;

    @Column(name = "Telefone", length = 11)
    private String Telefone;

    @Column(name = "email", length = 100)
    private String email;


    @Column(name = "Perfil", length = 1, nullable = false)
    private Character Perfil;


    @Column(name = "Status", length = 1, nullable = false)
    private Character Status;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String Nome) {
        this.nome = Nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public Character getPerfil() {
        return Perfil;
    }

    public void setPerfil(Character perfil) {
        Perfil = perfil;
    }

    public Character getStatus() {
        return Status;
    }

    public void setStatus(Character status) {
        Status = status;
    }
}
