package com.bancosimplesproject.repository;

import com.bancosimplesproject.entity.transacao.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {

}
