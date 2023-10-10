package com.bancosimplesproject.entity.usuario;

import com.bancosimplesproject.entity.usuario.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    @Column(unique = true)
    private String cpfcnpj;

    @Column(unique = true)
    private String email;

    private String senha;

    private BigDecimal saldo;

    private Tipo tipoUsuario;

}
