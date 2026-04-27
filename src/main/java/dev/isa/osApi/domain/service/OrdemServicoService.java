/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.isa.osApi.domain.service;

import dev.isa.osApi.domain.exception.DomainException;
import dev.isa.osApi.domain.model.OrdemServico;
import dev.isa.osApi.domain.model.StatusOrdemServico;
import dev.isa.osApi.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());

        return ordemServicoRepository.save(ordemServico);

    }

    public Optional<OrdemServico> atualizaStatus(Long ordemServicoID, StatusOrdemServico status) {
        Optional<OrdemServico> optOrdemServico = ordemServicoRepository.findById(ordemServicoID);

        if (optOrdemServico.isPresent()) {

            OrdemServico ordemServico = optOrdemServico.get();

            //Verifica se ordem está ABERTA
            if (ordemServico.getStatus() != StatusOrdemServico.ABERTA && StatusOrdemServico.ABERTA != status) {
                
                ordemServico.setStatus(status);
                ordemServico.setDataFinalizacao(LocalDateTime.now());
                ordemServicoRepository.save(ordemServico);
                return Optional.of(ordemServico);
                
            } else {
                
                //ops.. ordem FINALIZADA ou CANCELADA. Não alterar
                return Optional.empty();
            }
        } else {
            //Lança exception se ID não encontrado.
            throw new DomainException("Não existe OS com id" + ordemServicoID);
        }
    }

}
