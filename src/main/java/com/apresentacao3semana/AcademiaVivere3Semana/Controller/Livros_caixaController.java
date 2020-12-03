package com.apresentacao3semana.AcademiaVivere3Semana.Controller;

import com.apresentacao3semana.AcademiaVivere3Semana.Entity.Clientes;
import com.apresentacao3semana.AcademiaVivere3Semana.Entity.Livro_Caixa;
import com.apresentacao3semana.AcademiaVivere3Semana.Exception.ResourceNotFoundException;
import com.apresentacao3semana.AcademiaVivere3Semana.Repository.Livros_caixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("vivere/")
public class Livros_caixaController {

    @Autowired
    private Livros_caixaRepository livros_caixaRepository;

    @GetMapping("/livros_caixa")
    public List<Livro_Caixa> getAllUsers() {
        return this.livros_caixaRepository.findAll();
    }

    @GetMapping("/livros_caixa/{id}")
    public ResponseEntity<Livro_Caixa> getUserById(
            @PathVariable(value = "id") Integer livros_caixaId) throws ResourceNotFoundException {
        Livro_Caixa livro_caixa = livros_caixaRepository.findById(livros_caixaId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não econtrado :: " + livros_caixaId));
        return ResponseEntity.ok().body(livro_caixa);
    }

    @PostMapping("/livros_caixa")
    Livro_Caixa createUser(@Valid @RequestBody Livro_Caixa livro_caixa) {
        return livros_caixaRepository.save(livro_caixa);
    }

//    @PutMapping("/users/{id}")
//    public ResponseEntity<Livro_Caixa> updateUser(
//            @PathVariable(value = "id") int livros_caixaId,
//            @Valid @RequestBody Livro_Caixa livro_caixaDetails) throws ResourceNotFoundException {
//        Livro_Caixa livro_caixa = livros_caixaRepository.findById(livros_caixaId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + livros_caixaId));
//
//        livro_caixa.setId(userDetails.getEmailId());
//        livro_caixa.setLastName(userDetails.getLastName());
//        livro_caixa.setFirstName(userDetails.getFirstName());
//        livro_caixa.setLastModifiedDate(new Date());
//        final Livro_Caixa updatedUser = livros_caixaRepository.save(livro_caixa);
//        return ResponseEntity.ok(updatedUser);
//    }

    @DeleteMapping("/livros_caixa/{id}")
    public Map<String, Boolean> deleteUser(
            @PathVariable(value = "id") Integer livros_caixaId) throws ResourceNotFoundException {
        Livro_Caixa livro_caixa = livros_caixaRepository.findById(livros_caixaId)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado :: " + livros_caixaId));

        livros_caixaRepository.delete(livro_caixa);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/livros_caixa")
    public void deleteUser() {
        livros_caixaRepository.deleteAll();
    }
}
