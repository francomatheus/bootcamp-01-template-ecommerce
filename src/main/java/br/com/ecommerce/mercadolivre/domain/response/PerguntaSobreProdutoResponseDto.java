package br.com.ecommerce.mercadolivre.domain.response;

import br.com.ecommerce.mercadolivre.domain.model.PerguntaSobreProduto;

import java.time.LocalDateTime;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 1
 */

public class PerguntaSobreProdutoResponseDto {

    private Long id;
    private String titulo;
    private LocalDateTime instantePergunta;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstantePergunta() {
        return instantePergunta;
    }

    // +1
    public PerguntaSobreProdutoResponseDto(PerguntaSobreProduto perguntaSobreProduto){
        this.id = perguntaSobreProduto.getId();
        this.titulo = perguntaSobreProduto.getTitulo();
        this.instantePergunta = perguntaSobreProduto.getInstantePergunta();
    }

    @Override
    public String toString() {
        return "PerguntaSobreProdutoResponseDto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", instantePergunta=" + instantePergunta +
                '}';
    }
}
