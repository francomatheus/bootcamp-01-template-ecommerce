package br.com.ecommerce.mercadolivre.domain.model;

import br.com.ecommerce.mercadolivre.domain.response.DetalheProdutoCaracteristica;
import br.com.ecommerce.mercadolivre.domain.response.PerguntaSobreProdutoResponseDto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Carga intrinseca máxima permitida - 9
 * Carga intrínseca da classe - 22
 */

@Entity
@Table(name = "produto")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull @Positive
    private BigDecimal preco;
    @NotNull @Positive
    private Integer quantidadeDisponivel;
    @NotNull
    private LocalDateTime instanteCadastro = LocalDateTime.now();
    @NotBlank
    private String descricao;
    @Size(min = 3) @NotNull
    @OneToMany(cascade = CascadeType.PERSIST)
    // +1
    private Set<CaracteristicaProduto> caracteristicaProduto;
    @NotNull
    @ManyToOne
    // +1
    private Categoria categoria;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    // +1
    private Set<ImagemProduto> imagemProduto = new HashSet<>();
    @ManyToOne @NotNull
    // +1
    private Usuario usuario;
    @Valid @NotNull
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    // +1
    private Set<OpiniaoProduto> opiniaoProduto = new HashSet<>();
    @OneToMany(mappedBy = "produto",cascade = CascadeType.MERGE)
    // +1
    private List<PerguntaSobreProduto> pergunta = new ArrayList<>();

    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal preco, @NotNull @Positive Integer quantidadeDisponivel, @NotBlank String descricao, @Size(min = 3) @NotNull Set<CaracteristicaProduto> caracteristicaProduto, @NotNull Categoria categoria, @NotNull Usuario usuario) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.caracteristicaProduto = caracteristicaProduto;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicaProduto> getCaracteristicaProduto() {
        return caracteristicaProduto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<ImagemProduto> getImagemProduto() {
        return imagemProduto;
    }

    public Set<OpiniaoProduto> getOpiniaoProduto() {
        return opiniaoProduto;
    }

    public String donoDoProduto(){
        return this.usuario.getLogin();
    }

    public List<PerguntaSobreProduto> getPergunta() {
        return pergunta;
    }
    // +1
    public List<PerguntaSobreProdutoResponseDto> listaDePerguntas(){
        return this.pergunta.stream()
                // +1
                .map(perguntaSobreProduto -> {
                    return new PerguntaSobreProdutoResponseDto(perguntaSobreProduto);
                })
                // +1
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", instanteCadastro=" + instanteCadastro +
                ", descricao='" + descricao + '\'' +
                ", caracteristicaProduto=" + caracteristicaProduto +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                '}';
    }

    public void adicionaImagemProduto(List<String> pathImagemProduto) {

        Set<ImagemProduto> imagensProduto = pathImagemProduto.stream()
                // +1
                .map(path -> {
                    return new ImagemProduto(path, this);
                })
                // +1
                .collect(Collectors.toSet());

        this.imagemProduto.addAll(imagensProduto);

    }

    public void adicionaOpiniaoProduto(OpiniaoProduto opiniaoProduto) {

        this.opiniaoProduto.add(opiniaoProduto);

    }

    public void adicionaPergunta(PerguntaSobreProduto perguntaSobreProduto){
        this.pergunta.add(perguntaSobreProduto);
    }

    public List<PerguntaSobreProduto> todasPerguntasSobreProduto(){
        return this.pergunta;
    }

    public double mediaNotas(){
        return this.opiniaoProduto.stream()
                // +1
                .mapToDouble(opiniao -> {
                    return opiniao.getNota();
                })
                .average()
                // +1
                .orElse(0);
    }

    public long numeroTotalDeNotas(){
        return this.opiniaoProduto.stream()
                // +1
                .mapToDouble(opiniao -> {
                    return opiniao.getNota();
                }).count();
    }

    public Set<String> mapeiaPathImagens(){
        return this.imagemProduto.stream()
                // +1
                .map(imagemProduto -> {
                    return imagemProduto.getPathImagem();
                })
                // +1
                .collect(Collectors.toSet());
    }

    public Set<DetalheProdutoOpiniao> mapeiaOpiniao(){
        return this.opiniaoProduto.stream()
                // +1
                .map(opiniao -> {
                    return new DetalheProdutoOpiniao(opiniao);
                })
                // +1
                .collect(Collectors.toSet());
    }

    public Set<DetalheProdutoCaracteristica> mapeiaCaracteristica(){
        return this.caracteristicaProduto.stream()
                // +1
                .map(caracteristicaProduto -> {
                    return new DetalheProdutoCaracteristica(caracteristicaProduto);
                })
                // +1
                .collect(Collectors.toSet());
    }

    public Set<String> mapeiaPerguntas() {
        return this.pergunta.stream()
                // +1
                .map(perguntaSobreProduto -> {
                    return perguntaSobreProduto.getTitulo();
                })
                // +1
                .collect(Collectors.toSet());
    }
}
