package com.bancosimplesproject.service;

import com.bancosimplesproject.entity.usuario.Tipo;
import com.bancosimplesproject.entity.usuario.UsuarioEntity;
import com.bancosimplesproject.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuario;

    public void validacao(UsuarioEntity usuarioenvio, BigDecimal valor) throws Exception {
        if(usuarioenvio.getTipoUsuario() == Tipo.LOJISTA){
            throw new Exception("Sem permissão para realizar transações");
        }

        if(usuarioenvio.getSaldo().compareTo(valor) < 0){
            throw new Exception("Sem saldo suficiente");
        }


    }

    public UsuarioEntity findUserById(Long id) throws Exception {
        return this.usuario.findUsuarioById(id).orElseThrow(() -> new Exception("Usuario não existe"));
    }

    public void salvarUsuario(UsuarioEntity usuario){
        this.usuario.save(usuario);
    }



}
