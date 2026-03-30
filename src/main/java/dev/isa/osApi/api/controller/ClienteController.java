package dev.isa.osApi.api.controller;

import dev.isa.osApi.domain.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
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

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/clientes")
    public ArrayList<Cliente> listas() {
        // lINGUAGEM JPQL 
        return (ArrayList<Cliente>) manager.createQuery("From Cliente", Cliente.class).getResultList();
    }

}
