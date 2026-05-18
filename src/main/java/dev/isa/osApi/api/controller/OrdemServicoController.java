/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.isa.osApi.api.controller;

import dev.isa.osApi.domain.dto.AtualizaStatusDTO;
import dev.isa.osApi.domain.model.OrdemServico;
import dev.isa.osApi.domain.repository.OrdemServicoRepository;
import dev.isa.osApi.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    //-----------------------------------------------------------------------------------------------------------
    //CRUD
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }

    @PutMapping("/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizaStatus(
            @PathVariable Long ordemServicoID, @Valid @RequestBody AtualizaStatusDTO statusDTO) {

        Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(ordemServicoID, statusDTO.status());

        if (optOS.isPresent()) {
            return ResponseEntity.ok(optOS.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    //-----------------------------------------------------------------------------------------------------------
    //lISTAR POR ID
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @GetMapping("/ordem-servico/{clienteID}/abertas")
    public ResponseEntity<List<OrdemServico>> listarAbertasPorCliente(@PathVariable Long clienteID) {

        List<OrdemServico> abertas = ordemServicoRepository.findByClienteIdAndStatus(clienteID, "ABERTA");

        return ResponseEntity.ok(abertas);
    }

    @GetMapping("/ordem-servico/{clienteID}/fechadas")
    public ResponseEntity<List<OrdemServico>> listarFechadasPorCliente(@PathVariable Long clienteID) {
        List<OrdemServico> fechadas = ordemServicoRepository.findByClienteIdAndStatus(clienteID, "FECHADA");

        return ResponseEntity.ok(fechadas);
    }

    //-----------------------------------------------------------------------------------------------------------
}
