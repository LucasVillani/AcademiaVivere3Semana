package com.apresentacao3semana.AcademiaVivere3Semana.Repository;

import com.apresentacao3semana.AcademiaVivere3Semana.Entity.Clientes;
import com.apresentacao3semana.AcademiaVivere3Semana.Entity.Livro_Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
    Iterable<Clientes> findByNomeAndCpfCnpjAndCidadeAndUf(String nome, String cpfCnpj, String cidade, String uf);
    Iterable<Clientes> findByNome(String nome);
    Iterable<Clientes> findByCpfCnpj(String cpfCnpj);
    Iterable<Clientes> findByCidade(String cidade);
    Iterable<Clientes> findByUf(String uf);
}
