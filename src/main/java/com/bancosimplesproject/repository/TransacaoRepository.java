package com.bancosimplesproject.repository;

import com.bancosimplesproject.entity.transacao.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {

}
