/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.isa.osApi.domain.repository;

import dev.isa.osApi.domain.model.Cliente;
import dev.isa.osApi.domain.model.OrdemServico;
import dev.isa.osApi.domain.model.StatusOrdemServico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author digma
 */
@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    List<OrdemServico> findByClienteIdAndStatus(Long clienteId, StatusOrdemServico status);

    List<OrdemServico> findByComentariosIsNotEmpty();

    List<OrdemServico> findByComentariosIsEmpty();
    
    List<OrdemServico> findByStatusAndComentariosIsNotEmpty(StatusOrdemServico status);

    List<OrdemServico> findByStatusAndComentariosIsEmpty(StatusOrdemServico status);
    
    
    
    // O Spring lê o nome e monta a query automaticamente Funcionando como um tradutor para o 
    //banco de dados!
}



