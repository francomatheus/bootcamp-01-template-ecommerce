package br.com.ecommerce.mercadolivre.repository;

import br.com.ecommerce.mercadolivre.domain.model.PerguntaSobreProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerguntaSobreProdutoRepository extends JpaRepository<PerguntaSobreProduto, Long> {

    List<PerguntaSobreProduto> findAllByProdutoId(Long id);
}
