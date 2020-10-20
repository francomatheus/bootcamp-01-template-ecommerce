package br.com.ecommerce.mercadolivre.storage;

import br.com.ecommerce.mercadolivre.domain.request.ProdutoImagemRequest;

import javax.validation.Valid;
import java.util.List;

public interface StorageFile {

    List<String> salvaImagem(@Valid ProdutoImagemRequest imagensProduto);

}
