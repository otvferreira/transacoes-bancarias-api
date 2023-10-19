package com.bancosimplesproject.repository;

import com.bancosimplesproject.entity.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findUserByCPFCNPJ(String cpjcnpj);
    Optional<UsuarioEntity> findUsuarioById(Long aLong);
}
