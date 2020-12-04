package com.apresentacao3semana.AcademiaVivere3Semana.Controller;

import com.apresentacao3semana.AcademiaVivere3Semana.Entity.Clientes;
import com.apresentacao3semana.AcademiaVivere3Semana.Entity.Livro_Caixa;
import com.apresentacao3semana.AcademiaVivere3Semana.Exception.ResourceNotFoundException;
import com.apresentacao3semana.AcademiaVivere3Semana.Repository.ClientesRepository;
import com.apresentacao3semana.AcademiaVivere3Semana.Repository.Livros_caixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("vivere/")
public class Livros_caixaController {

    @Autowired
    private Livros_caixaRepository livros_caixaRepository;
    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping("/livros_caixa")
    public List<Livro_Caixa> getAllUsers() {
        List<Livro_Caixa> livro_caixas = livros_caixaRepository.findAll();
        Collections.sort(livro_caixas);
        return livro_caixas;
    }

    @GetMapping("/livros_caixa/{id}")
    public ResponseEntity<Livro_Caixa> getUserById(
            @PathVariable(value = "id") Integer livros_caixaId) throws ResourceNotFoundException {
        Livro_Caixa livro_caixa = livros_caixaRepository.findById(livros_caixaId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não econtrado :: " + livros_caixaId));
        return ResponseEntity.ok().body(livro_caixa);
    }

    @GetMapping("/livros_caixa/clienteId/{clienteId}")
    public List<Livro_Caixa> getUserByClienteId(
            @PathVariable(value = "clienteId") Integer clienteId) throws ResourceNotFoundException {
        Clientes clientes = clientesRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não econtrado :: " + clienteId));
        return livros_caixaRepository.findAllByCliente(clientes);
    }

    @PostMapping("/livros_caixa")
    Livro_Caixa createUser(@Valid @RequestBody Livro_Caixa livro_caixa) {
        return livros_caixaRepository.save(livro_caixa);
    }

    @PutMapping("/livros_caixa/{id}")
    public ResponseEntity<Livro_Caixa> updateUser(
            @PathVariable(value = "id") int livros_caixaId,
            @Valid @RequestBody Livro_Caixa livro_caixaDetails) throws ResourceNotFoundException {
        Livro_Caixa livro_caixa = livros_caixaRepository.findById(livros_caixaId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + livros_caixaId));

        livro_caixa.setID(livro_caixaDetails.getID());
        livro_caixa.setTipo(livro_caixaDetails.getTipo());
        livro_caixa.setDescricao(livro_caixaDetails.getDescricao());
        livro_caixa.setData_lancamento(livro_caixaDetails.getData_lancamento());
        livro_caixa.setValor(livro_caixaDetails.getValor());
        livro_caixa.setCliente(livro_caixaDetails.getCliente());
        final Livro_Caixa updatedLivros_Caixa = livros_caixaRepository.save(livro_caixa);
        return ResponseEntity.ok(updatedLivros_Caixa);
    }

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

}
