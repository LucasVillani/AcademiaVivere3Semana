package com.apresentacao3semana.AcademiaVivere3Semana.Repository;

import com.apresentacao3semana.AcademiaVivere3Semana.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Iterable<Usuario> findByNomeAndEmail(String Nome, String Email);
    Iterable<Usuario> findByNome(String Nome);
    Iterable<Usuario> findByEmail(String Email);

    Boolean existsByLoginAndSenha(String login, String senha);
    Boolean existsByLogin(String login);
    Usuario findByLogin(String login);
}
