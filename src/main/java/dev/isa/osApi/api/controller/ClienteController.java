package dev.isa.osApi.api.controller;

import dev.isa.osApi.domain.model.Cliente;
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

    List<Cliente> listaClientes;

    @GetMapping("/clientes")
    public ArrayList<Cliente> listas() {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        listaClientes.add(new Cliente(1, "Kge", "kge@teste.com", "11-99999-9999"));
        listaClientes.add(new Cliente(2, "isa", "kge@teste.com", "11-99999-9999"));
        listaClientes.add(new Cliente(3, "oii", "kge@teste.com", "11-99999-9999"));

        return listaClientes;

    }

}
