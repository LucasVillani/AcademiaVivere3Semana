package com.apresentacao3semana.AcademiaVivere3Semana.Controller;

import com.apresentacao3semana.AcademiaVivere3Semana.Entity.*;
import com.apresentacao3semana.AcademiaVivere3Semana.Exception.ResourceNotFoundException;
import com.apresentacao3semana.AcademiaVivere3Semana.Repository.ClientesRepository;
import com.apresentacao3semana.AcademiaVivere3Semana.Repository.Livros_caixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
@RequestMapping("vivere/")
public class ClientesController {
    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private Livros_caixaRepository livros_caixaRepository;
    @GetMapping("/clientes")
    public List<Clientes> getAllUsers() {
        return this.clientesRepository.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Clientes> getUserById(
            @PathVariable(value = "id") Integer clientesId) throws ResourceNotFoundException {
        Clientes clientes = clientesRepository.findById(clientesId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado :: " + clientesId));
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/clientes/search")
    Iterable<Clientes> findByQuery(
            @RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "cpf_cnpj", required = false) String CpfCnpj, @RequestParam(value = "cidade", required = false) String cidade, @RequestParam(value = "uf", required = false) String uf)
    {
        if(nome!=null && CpfCnpj!=null && cidade!=null && uf!=null)
            return clientesRepository.findByNomeAndCpfCnpjAndCidadeAndUf(nome,CpfCnpj,cidade,uf);
        else if(nome!=null)
            return clientesRepository.findByNome(nome);
        else if(CpfCnpj!=null)
            return clientesRepository.findByCpfCnpj(CpfCnpj);
        else if(cidade!=null)
            return clientesRepository.findByCidade(cidade);
        else if(uf!=null)
            return clientesRepository.findByUf(uf);
        else
            return clientesRepository.findAll();
    }


    @GetMapping("/clientes/contabil/{id}")
    ClientesDTO getContabilId(
            @PathVariable(value = "id") Integer clientesDTOId) throws ResourceNotFoundException {
        Clientes clientes = clientesRepository.findById(clientesDTOId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado :: " + clientesDTOId));

        List <Livro_Caixa> livro_caixas = livros_caixaRepository.findAllByCliente(clientes);
        List<Livro_CaixaDTO> contabil = getContabil(livro_caixas);

        ClientesDTO clientesDTO = new ClientesDTO();
        clientesDTO.setId(clientes.getID());
        clientesDTO.setCpfCnpj(clientes.getCpfCnpj());
        clientesDTO.setNome(clientes.getNome());
        clientesDTO.setTelefone(clientes.getTelefone());
        clientesDTO.setContabil(contabil);

        return clientesDTO;
    }

    @GetMapping("/clientes/contabil")
    List<ClientesDTO> getListContabil(){
        List<Clientes> clientes = clientesRepository.findAll();
        List<ClientesDTO> clientesDTO = new ArrayList<>();
    clientes.forEach(cliente -> {
        List <Livro_Caixa> livro_caixas = livros_caixaRepository.findAllByCliente(cliente);
        List<Livro_CaixaDTO> contabil = getContabil(livro_caixas);

        ClientesDTO clienteDTO = new ClientesDTO();
        clienteDTO.setId(cliente.getID());
        clienteDTO.setCpfCnpj(cliente.getCpfCnpj());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setContabil(contabil);
        clientesDTO.add(clienteDTO);
    });
        return clientesDTO;
    }

//    @GetMapping("/clientes/contabil/search")
//    Iterable<ClientesDTO> findByQuery(
//            @RequestParam(value = "DataInicial", required = false) Date DataInicial, @RequestParam(value = "DataFinal", required = false) Date DataFinal){
//
//        List<Clientes> clientes = clientesRepository.findAll();
//        List<ClientesDTO> clientesDTO = new ArrayList<>();
//        clientes.forEach(cliente -> {
//                    List<Livro_Caixa> livro_caixas = livros_caixaRepository.findAllByCliente(cliente);
//                    List<Livro_CaixaDTO> contabil = getContabil(livro_caixas);
//
//                    ClientesDTO clienteDTO = new ClientesDTO();
//                    clienteDTO.setId(cliente.getID());
//                    clienteDTO.setCpfCnpj(cliente.getCpfCnpj());
//                    clienteDTO.setNome(cliente.getNome());
//                    clienteDTO.setTelefone(cliente.getTelefone());
//                    clienteDTO.setContabil(contabil);
//                    clientesDTO.add(clienteDTO);
//                });
//        List<ClientesDTO> contabilFiltered = new ArrayList<>();
//        if (DataInicial != null && DataFinal != null)
//            contabilFiltered = clientesDTO.stream()
//                    .filter(DTO -> DTO.getContabil().get(0).getData_lancamento().equals(DataInicial))
//                    .collect(Collectors.toList());
//
//            return contabilFiltered;
//        else if (DataInicial != null)
//            return usuarioRepository.findByNome(DataInicial);
//        else if (DataFinal != null)
//            return usuarioRepository.findByEmail(DataFinal);
//        else
//            return usuarioRepository.findAll();
//
//    }

    @PostMapping("/clientes")
    Clientes createUser(@Valid @RequestBody Clientes clientes) {
        return clientesRepository.save(clientes);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Clientes> updateUser(
            @PathVariable(value = "id") int clientesId,
            @Valid @RequestBody Clientes clientesDetails) throws ResourceNotFoundException {
        Clientes clientes = clientesRepository.findById(clientesId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado :: " + clientesId));

        clientes.setID(clientesDetails.getID());
        clientes.setNome(clientesDetails.getNome());
        clientes.setCpfCnpj(clientesDetails.getCpfCnpj());
        clientes.setLogradouro(clientesDetails.getLogradouro());
        clientes.setCidade(clientesDetails.getCidade());
        clientes.setUF(clientesDetails.getUF());
        clientes.setCEP(clientesDetails.getCEP());
        clientes.setTelefone(clientesDetails.getTelefone());
        clientes.setEmail(clientesDetails.getEmail());

        clientes.setLastModifiedDate(new Date());
        final Clientes updatedClientes = clientesRepository.save(clientes);
        return ResponseEntity.ok(updatedClientes);
    }

    @DeleteMapping("/clientes/{id}")
    public Map<String, Boolean> deleteUser(
            @PathVariable(value = "id") Integer clientesId) throws ResourceNotFoundException {
        Clientes clientes = clientesRepository.findById(clientesId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado :: " + clientesId));

        clientesRepository.delete(clientes);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    List<Livro_CaixaDTO> getContabil(List<Livro_Caixa> list){
        List <Livro_CaixaDTO> contabil = new ArrayList<>();
        AtomicReference<Double> saldoAUX = new AtomicReference<>((double) 0);
        Collections.sort(list);
        list.forEach(livro_caixa -> {
            Livro_CaixaDTO livro_caixaDTO = new Livro_CaixaDTO();
            livro_caixaDTO.setData_lancamento(livro_caixa.getData_lancamento());
            livro_caixaDTO.setDescricao(livro_caixa.getDescricao());
            livro_caixaDTO.setValor(livro_caixa.getValor());
            livro_caixaDTO.setTipo(livro_caixa.getTipo());
            if (livro_caixaDTO.getTipo().equals("C".charAt(0))) {
                saldoAUX.updateAndGet(v -> (double) (v + livro_caixaDTO.getValor()));
            } else if (livro_caixaDTO.getTipo().equals("D".charAt(0))) {
                saldoAUX.updateAndGet(v -> (double) (v - livro_caixaDTO.getValor()));
            }
            livro_caixaDTO.setSaldo(saldoAUX.get());
            contabil.add(livro_caixaDTO);
        });
        return contabil;
    }

}
