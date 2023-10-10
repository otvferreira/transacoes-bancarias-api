package com.bancosimplesproject.repository;

import com.bancosimplesproject.entity.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findUserByCPFCNPJ(String cpjcnpj);
    Optional<UsuarioEntity> findById(Long aLong);
}
