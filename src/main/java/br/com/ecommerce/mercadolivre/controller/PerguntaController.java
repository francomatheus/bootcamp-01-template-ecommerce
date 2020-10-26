package br.com.ecommerce.mercadolivre.controller;

import br.com.ecommerce.mercadolivre.domain.model.MensagemEmail;
import br.com.ecommerce.mercadolivre.domain.model.PerguntaSobreProduto;
import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.model.Usuario;
import br.com.ecommerce.mercadolivre.domain.request.PerguntaSobreProdutoRequest;
import br.com.ecommerce.mercadolivre.domain.response.PerguntaSobreProdutoResponseDto;
import br.com.ecommerce.mercadolivre.repository.UsuarioRepository;
import br.com.ecommerce.mercadolivre.service.email.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

/**
 * Carga intrinseca máxima permitida - 7
 * Carga intrínseca da classe - 9
 */

@RestController
@RequestMapping("/v1/produtos")
public class PerguntaController {

    private static Logger logger = LoggerFactory.getLogger(PerguntaController.class);

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    // +1
    private UsuarioRepository usuarioRepository;

    @Autowired
    // +1
    private SendMail sendMail;


    @PostMapping("/{id}/perguntas")
    @Transactional
    // +1
    public ResponseEntity<?> criaPergunta(@PathVariable Long id,
                                          @RequestBody @Valid PerguntaSobreProdutoRequest perguntaSobreProdutoRequest,
                                          @AuthenticationPrincipal String emailUsuarioLogado,
                                          UriComponentsBuilder uriComponentsBuilder){
        logger.info("Recebendo dados para criar pergunta: {}", perguntaSobreProdutoRequest);
        // +1
        Produto produto = manager.find(Produto.class, id);
        // +1
        Assert.isTrue(produto!=null, "Produto não encontrado no Banco de Dados");
        // +1
        Usuario usuario = usuarioRepository.findByLogin(emailUsuarioLogado).get();
        // +1
        PerguntaSobreProduto perguntaSobreProduto = perguntaSobreProdutoRequest.toModel(produto, usuario);

        produto.adicionaPergunta(perguntaSobreProduto);

        manager.merge(produto);
        logger.info("Pergunta salva e produto autalizado com sucesso!!");

        // +1
        List<PerguntaSobreProdutoResponseDto> perguntaSobreProdutoResponseDtos = produto.listaDePerguntas();

        // +1
        MensagemEmail messagemEmail = new MensagemEmail(emailUsuarioLogado, produto.donoDoProduto(), perguntaSobreProduto.getTitulo());

        messagemEmail.enviarEmail(sendMail);


        return ResponseEntity
                .created(uriComponentsBuilder.path("/produto/{id}/pergunta/{perguntaId}").buildAndExpand(id,perguntaSobreProduto.getId()).toUri())
                .body(perguntaSobreProdutoResponseDtos);
    }
}
