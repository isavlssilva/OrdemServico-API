/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.isa.osApi.domain.service;

import dev.isa.osApi.domain.exception.DomainException;
import dev.isa.osApi.domain.model.Cliente;
import dev.isa.osApi.domain.model.OrdemServico;
import dev.isa.osApi.domain.model.StatusOrdemServico;
import dev.isa.osApi.domain.repository.ClienteRepository;
import dev.isa.osApi.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
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

    public OrdemServico criar(OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());

        return OrdemServicoRepository.save(ordemServico);

    }

}
