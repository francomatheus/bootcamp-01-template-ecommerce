package br.com.ecommerce.mercadolivre.storage;

import br.com.ecommerce.mercadolivre.domain.request.ProdutoImagemRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArmazenaImagem implements StorageFile {

//    @Autowired
//    private AmazonS3 amazonS3;

    private static final String BUCKET="produto-imagem";

    public List<String> salvaImagem(@Valid ProdutoImagemRequest imagensProduto) {
        List<String> imagemProdutos = new ArrayList<>();
        try {

            imagensProduto.getImagemProduto().forEach(imagemProduto -> {
//                amazonS3.putObject(new PutObjectRequest(BUCKET,imagemProduto.getOriginalFilename(),
//                        imagemProduto.getInputStream(),null)
//                        .withCannedAcl(CannedAccessControlList.PublicRead));

                String pathImage = "http://s3.amazonaws.com/" + BUCKET + "/" + imagemProduto.getOriginalFilename();
                imagemProdutos.add(pathImage);
            });

            return imagemProdutos;

        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }


}
