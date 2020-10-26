package br.com.ecommerce.mercadolivre.repository;

import br.com.ecommerce.mercadolivre.domain.model.PerguntaSobreProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Carga intrínseca máxima permitida - 3
 * Carga intrínseca da classe - 1
 */

public interface PerguntaSobreProdutoRepository extends JpaRepository<PerguntaSobreProduto, Long> {
    // +1
    List<PerguntaSobreProduto> findAllByProdutoId(Long id);
}
