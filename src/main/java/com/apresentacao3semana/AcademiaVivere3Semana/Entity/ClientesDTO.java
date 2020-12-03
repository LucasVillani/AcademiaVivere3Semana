package com.apresentacao3semana.AcademiaVivere3Semana.Entity;

import java.util.List;

public class ClientesDTO {

    private Integer id;
    private String nome;
    private String cpfCnpj;
    private String telefone;
    private List<Livro_CaixaDTO> contabil;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Livro_CaixaDTO> getContabil() {
        return contabil;
    }

    public void setContabil(List<Livro_CaixaDTO> contabil) {
        this.contabil = contabil;
    }
}
