package com.bancosimplesproject.service;

import com.bancosimplesproject.dto.TransacaoDTO;
import com.bancosimplesproject.entity.transacao.TransacaoEntity;
import com.bancosimplesproject.entity.usuario.UsuarioEntity;
import com.bancosimplesproject.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransacaoService {

    @Autowired
     private UsuarioService usuarioService;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void criarTransacao(TransacaoDTO transacaoDTO) throws Exception {
        UsuarioEntity usuarioenvioid = this.usuarioService.findUserById(transacaoDTO.usuarioenvioid());
        UsuarioEntity usuariorecebeid = this.usuarioService.findUserById(transacaoDTO.usuariorecebeid());

        usuarioService.validacao(usuarioenvioid, transacaoDTO.value());

        boolean autorizado = this.autorizarTransacao(usuarioenvioid, transacaoDTO.value());
        if (!autorizado){
            throw new Exception("Transação não autorizada");
        }

        TransacaoEntity transacaoEntity = new TransacaoEntity();
        transacaoEntity.setValor(transacaoDTO.value());
        transacaoEntity.setUsuarioenvio(usuarioenvioid);
        transacaoEntity.setUsuariorecebe(usuariorecebeid);
        transacaoEntity.setTr_data(LocalDateTime.now());

        // Altera saldo do Usuario de envio subtraindo transacao
        usuarioenvioid.setSaldo(usuarioenvioid.getSaldo().subtract(transacaoDTO.value()));

        // Altera saldo do Usuario que recebe somando transacao
        usuariorecebeid.setSaldo(usuariorecebeid.getSaldo().add(transacaoDTO.value()));

        this.transacaoRepository.save(transacaoEntity);
        this.usuarioService.salvarUsuario(usuarioenvioid);
        this.usuarioService.salvarUsuario(usuariorecebeid);

    }

    public boolean autorizarTransacao (UsuarioEntity usuarioenvio, BigDecimal valor){
       ResponseEntity<Map> autorizacaoResposta = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class );

        if(autorizacaoResposta.getStatusCode() == HttpStatus.OK){
            String mensagem = (String) autorizacaoResposta.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(mensagem);

        } else return false;
    }

}
