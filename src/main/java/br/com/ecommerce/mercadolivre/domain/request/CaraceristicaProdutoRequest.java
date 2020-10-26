package br.com.ecommerce.mercadolivre.domain.request;

import br.com.ecommerce.mercadolivre.domain.model.CaracteristicaProduto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 1
 */

public class CaraceristicaProdutoRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String valor;

    public CaraceristicaProdutoRequest(@NotBlank String nome, @NotBlank String valor) {
        this.nome = nome;
        this.valor = valor;
    }
    // +1
    public CaracteristicaProduto toModel(){
        return new CaracteristicaProduto(this.nome, this.valor);
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "CaraceristicaProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaraceristicaProdutoRequest that = (CaraceristicaProdutoRequest) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
