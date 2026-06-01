/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.isa.osApi.api.controller;

import dev.isa.osApi.domain.dto.AtualizaStatusDTO;
import dev.isa.osApi.domain.model.OrdemServico;
import dev.isa.osApi.domain.model.StatusOrdemServico;
import dev.isa.osApi.domain.repository.OrdemServicoRepository;
import dev.isa.osApi.domain.service.OrdemServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    //-------------------------------------------------------------------------------
    //- SWAGGER ---------------------------------------------------------------------
    @Operation(
            summary = "Obter uma OS por ID",
            description = "Retorna os detalhes de uma Ordem de Serviço com base no ID fornecido."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ordem de Serviço encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Ordem de Serviço não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> getOS(
            @PathVariable("id")
            @Parameter(description = "ID da Ordem de Serviço a ser buscada", example = "1", required = true) Long id
    ) {
        return ordemServicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //-----------------------------------------------------------------------------------------------------------
    //Listar todos
    @GetMapping
    public List<OrdemServico> listas() {
        return ordemServicoRepository.findAll();
    }

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
    @GetMapping("/cliente/{clienteId}/{status}")

    public List<OrdemServico> listarAbertasPorCliente(@PathVariable Long clienteId, @PathVariable String statusString) {
        StatusOrdemServico status = StatusOrdemServico.valueOf(statusString);
        return ordemServicoService.listarPorClientePorStatus(clienteId, status);

    }

    //-----------------------------------------------------------------------------------------------------------
    //lISTAR sem comentarios
    @GetMapping("/sem-comentarios")

    public List<OrdemServico> listarSemComentarios() {

        return ordemServicoService.listarSemComentarios();

    }

    //-----------------------------------------------------------------------------------------------------------
    //lISTAR sem comentarios
    @GetMapping("/com-comentarios")

    public List<OrdemServico> listarComComentarios() {

        return ordemServicoService.listarComComentarios();

    }

    //-----------------------------------------------------------------------------------------------------------
    //lISTAR com comentarios por status 
    @GetMapping("/com-comentarios/{clienteId}/{status}")

    public List<OrdemServico> listarComComentarioPorStatus(@PathVariable String statusString) {
        StatusOrdemServico status = StatusOrdemServico.valueOf(statusString);
        return ordemServicoService.listarComComentarioPorStatus(status);
    }

    //-----------------------------------------------------------------------------------------------------------
    //lISTAR sem comentarios por status 
    @GetMapping("/sem-comentarios/{clienteId}/{status}")

    public List<OrdemServico> listarSemComentarioPorStatus(@PathVariable String statusString) {
        StatusOrdemServico status = StatusOrdemServico.valueOf(statusString);
        return ordemServicoService.listarSemComentarioPorStatus(status);
    }

}
