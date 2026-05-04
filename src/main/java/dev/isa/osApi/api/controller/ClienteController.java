package dev.isa.osApi.api.controller;

import dev.isa.osApi.domain.model.Cliente;
import dev.isa.osApi.domain.repository.ClienteRepository;
import dev.isa.osApi.domain.service.ClienteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author digma
 */
@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    //- CRUD ---------------------------------------------------------------------
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {

        return clienteService.salvar(cliente);
    }

    @PutMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteID, @RequestBody Cliente cliente) {

        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteID);
        cliente = clienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{clienteID}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID) {

        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        clienteService.excluir(clienteID);
        return ResponseEntity.noContent().build();
    }

    //------------------------------------------------------------------------------
    // Listar todos os clientes
    @GetMapping("/clientes")
    public List<Cliente> listas() {
        return clienteRepository.findAll();
    }

    //------------------------------------------------------------------------------
    // Listar clientes por ID
    @GetMapping("/clientes/ID/{clienteID}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // ------------------------------------------------------------------------------
    // Listar Cliente por email
    @GetMapping("/clientes/email/{clienteEmail}")
    public ResponseEntity<Cliente> buscar(@PathVariable String clienteEmail) {
        Cliente clienteExistente = clienteRepository.findByEmail(clienteEmail);

        if (clienteExistente != null && !clienteExistente.equals(clienteEmail)) {

            return ResponseEntity.ok(clienteExistente);

        } else {

            return ResponseEntity.notFound().build();

        }
    }
    // ------------------------------------------------------------------------------
    //Listar Cliente por Telefone

    @GetMapping("/clientes/telefone/{clienteFone}")
    public ResponseEntity<Cliente> buscaa(@PathVariable String clienteFone) {
        Cliente clienteExistente = clienteRepository.findByFone(clienteFone);

        if (clienteExistente != null && !clienteExistente.equals(clienteFone)) {

            return ResponseEntity.ok(clienteExistente);

        } else {

            return ResponseEntity.notFound().build();

        }
    }

}
