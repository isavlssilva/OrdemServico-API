/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.isa.osApi.domain.repository;

import dev.isa.osApi.domain.model.Cliente;
import dev.isa.osApi.domain.model.OrdemServico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digma
 */
@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    
    List<OrdemServico> findByClienteIdAndStatus(Long clienteId, String status);
}
