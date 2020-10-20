package br.com.ecommerce.mercadolivre.domain.request;

import br.com.ecommerce.mercadolivre.annotation.ValorValido;
import br.com.ecommerce.mercadolivre.domain.model.CaracteristicaProduto;
import br.com.ecommerce.mercadolivre.domain.model.Categoria;
import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.model.Usuario;
import br.com.ecommerce.mercadolivre.repository.UsuarioRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoRequest {

    @NotBlank
    private String nome;
    @Positive @NotNull
    private BigDecimal preco;
    @Positive  @NotNull
    private Integer quantidadeDisponivel;
    @Size(min = 3)
    @NotNull
    private Set<@Valid CaraceristicaProdutoRequest> caracteristicaProduto;
    @NotBlank @Length(max = 1000)
    private String descricao;
    @NotNull @ValorValido(className = Categoria.class)
    private Long categoriaId;

    public ProdutoRequest(@NotBlank String nome, @Positive @NotNull BigDecimal preco, @Positive @NotNull Integer quantidadeDisponivel, @Size(min = 3) Set<CaraceristicaProdutoRequest> caracteristicaProduto, @NotBlank @Length(max = 1000) String descricao, @NotNull Long categoriaId) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicaProduto = caracteristicaProduto;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Set<CaraceristicaProdutoRequest> getCaracteristicaProduto() {
        return caracteristicaProduto;
    }

    public void setCaracteristicaProduto(Set<CaraceristicaProdutoRequest> caracteristicaProduto) {
        this.caracteristicaProduto = caracteristicaProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Produto toModel(EntityManager manager, UsuarioRepository usuarioRepository, String emailDoUsuarioLogado){
        Categoria categoria = manager.find(Categoria.class, this.categoriaId);

        Usuario usuarioByLogin = usuarioRepository.findByLogin(emailDoUsuarioLogado).get();

        Set<CaracteristicaProduto> caracteristicaProduto = this.caracteristicaProduto.stream()
                .map(caraceristicaProdutoRequest -> {
                    return caraceristicaProdutoRequest.toModel();
                }).collect(Collectors.toSet());

        return new Produto(this.nome,this.preco,this.quantidadeDisponivel,this.descricao,caracteristicaProduto, categoria, usuarioByLogin);
    }

    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", caraceristicaProdutoRequestList=" + caracteristicaProduto +
                ", descricao='" + descricao + '\'' +
                ", categoriaId=" + categoriaId +
                '}';
    }
}
