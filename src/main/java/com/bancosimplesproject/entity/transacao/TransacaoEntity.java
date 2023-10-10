package com.bancosimplesproject.entity.transacao;

import com.bancosimplesproject.entity.usuario.UsuarioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacao")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name="id_envio")
    private UsuarioEntity usuarioenvio;

    @ManyToOne
    @JoinColumn(name="id_recebe")
    private UsuarioEntity usuariorecebe;

    private LocalDateTime tr_data;
}
