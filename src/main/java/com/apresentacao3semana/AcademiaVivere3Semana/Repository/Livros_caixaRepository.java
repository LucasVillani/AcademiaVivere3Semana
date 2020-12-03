package com.apresentacao3semana.AcademiaVivere3Semana.Repository;

import com.apresentacao3semana.AcademiaVivere3Semana.Entity.Clientes;
import com.apresentacao3semana.AcademiaVivere3Semana.Entity.Livro_Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Livros_caixaRepository extends JpaRepository<Livro_Caixa, Integer> {
    List<Livro_Caixa> findAllByCliente(Clientes clienteId);
}