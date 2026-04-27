/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.isa.osApi.domain.service;

import dev.isa.osApi.domain.exception.DomainException;
import dev.isa.osApi.domain.model.Cliente;
import dev.isa.osApi.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        // Lembre-se que o método SAVE pode ser usado atualizar um cliente também!!!
        // ID vazio --> Novo Registro 
        // ID PRENCHIDO --> Alterar existente
        // Verifica se o cliente existe
        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            // Lançar exception 
            throw new DomainException("Já existe um cliente cadastrado com esse email!");
        }

        return clienteRepository.save(cliente);

    }

    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

}
