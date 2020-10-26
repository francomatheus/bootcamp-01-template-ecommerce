package br.com.ecommerce.mercadolivre.domain.enums;

public enum StatusCompraFinalizada {

    SUCESSO, ERRO;

    public StatusTransacao normaliza(){
        if (this.equals(SUCESSO)){
            return StatusTransacao.sucesso;
        }
        return StatusTransacao.erro;
    }
}
