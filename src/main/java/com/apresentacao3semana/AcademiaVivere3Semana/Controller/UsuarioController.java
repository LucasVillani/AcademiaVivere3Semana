package com.apresentacao3semana.AcademiaVivere3Semana.Controller;

import com.apresentacao3semana.AcademiaVivere3Semana.Entity.Usuario;
import com.apresentacao3semana.AcademiaVivere3Semana.Exception.ResourceNotFoundException;
import com.apresentacao3semana.AcademiaVivere3Semana.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("vivere/")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/usuarios")
    public List<Usuario> getAllUsers() {
        return this.usuarioRepository.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUserById(
            @PathVariable(value = "id") Integer usuarioId) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado :: " + usuarioId));
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/usuarios/search")
    Iterable<Usuario> findByQuery(
            @RequestParam(value = "nome", required = false) String Nome, @RequestParam(value = "email", required = false) String Email) {
        if (Nome != null && Email != null)
            return usuarioRepository.findByNomeAndEmail(Nome, Email);
        else if (Nome != null)
            return usuarioRepository.findByNome(Nome);
        else if (Email != null)
            return usuarioRepository.findByEmail(Email);
        else
            return usuarioRepository.findAll();
    }

    @GetMapping("/usuarios/login")
    public Map<String, Boolean> loginQuery(
            @RequestParam(value = "login", required = false) String login, @RequestParam(value = "senha", required = false) String senha) {
        Map<String, Boolean> response = new HashMap<>();

        if (login.isEmpty() || senha.isEmpty()) {
            response.put("Login e Senha obrigatórios!", Boolean.FALSE);
            return response;
        } else {
            Usuario usuarioLogin = usuarioRepository.findByLogin(login);
            if (usuarioLogin == null) {
                response.put("Login não existe!", Boolean.FALSE);
                return response;
            } else if (!usuarioLogin.getSenha().equals(senha)) {
                response.put("Senha errada!", Boolean.FALSE);
                return response;
            } else if (!usuarioLogin.getStatus().equals("A".charAt(0))) {
                response.put("Está conta está cancelada!", Boolean.FALSE);
                return response;
            } else {
                response.put("Você foi logado!", Boolean.TRUE);
                return response;
            }
        }


    }

    @PostMapping("/usuarios")
    Usuario createUser(@Valid @RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> updateUser(
            @PathVariable(value = "id") int usuarioId,
            @Valid @RequestBody Usuario usuarioDetails) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado :: " + usuarioId));

        usuario.setID(usuarioDetails.getID());
        usuario.setNome(usuarioDetails.getNome());
        usuario.setLogin(usuarioDetails.getLogin());
        usuario.setSenha(usuarioDetails.getSenha());
        usuario.setTelefone(usuarioDetails.getTelefone());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setPerfil(usuarioDetails.getPerfil());
        usuario.setStatus(usuarioDetails.getStatus());
        usuario.setLastModifiedDate(new Date());
        final Usuario updatedUser = usuarioRepository.save(usuario);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/usuarios/{id}")
    public Map<String, Boolean> deleteUser(
            @PathVariable(value = "id") Integer usuarioId) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + usuarioId));

        usuarioRepository.delete(usuario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
