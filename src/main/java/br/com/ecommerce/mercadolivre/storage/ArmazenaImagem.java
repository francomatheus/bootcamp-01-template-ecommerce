package br.com.ecommerce.mercadolivre.storage;

import br.com.ecommerce.mercadolivre.domain.request.ProdutoImagemRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 3
 */

@Service
public class ArmazenaImagem implements StorageFile {


    private static final String BUCKET="produto-imagem";

    // +1
    public List<String> salvaImagem(@Valid ProdutoImagemRequest imagensProduto) {
        List<String> imagemProdutos = new ArrayList<>();
        // +1
        try {

            imagensProduto.getImagemProduto().forEach(imagemProduto -> {

                String pathImage = "http://s3.amazonaws.com/" + BUCKET + "/" + imagemProduto.getOriginalFilename();
                imagemProdutos.add(pathImage);
            });

            return imagemProdutos;

        }
        // +1
        catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }


}
