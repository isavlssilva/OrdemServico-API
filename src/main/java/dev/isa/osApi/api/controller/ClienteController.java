package dev.isa.osApi.api.controller;

import dev.isa.osApi.domain.model.Cliente;
import dev.isa.osApi.domain.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
   
   @GetMapping("/cliente")
   public List<Cliente> listas(){
       return clienteRepository.findAll();
       
   }
   
    
    
    
    
    
}
