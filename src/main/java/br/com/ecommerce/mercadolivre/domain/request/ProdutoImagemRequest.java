package br.com.ecommerce.mercadolivre.domain.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ProdutoImagemRequest {

    @NotNull
    @Size(min = 1)
    private List<MultipartFile> imagemProduto = new ArrayList<>();

    public ProdutoImagemRequest(@NotNull @Size(min = 1) List<MultipartFile> imagemProduto) {
        this.imagemProduto = imagemProduto;
    }

    public void setImagemProduto(List<MultipartFile> imagemProduto) {
        this.imagemProduto = imagemProduto;
    }

    public List<MultipartFile> getImagemProduto() {
        return imagemProduto;
    }

    @Override
    public String toString() {
        return "ProdutoImagemRequest{" +
                "imagemProduto=" + imagemProduto +
                '}';
    }
}
