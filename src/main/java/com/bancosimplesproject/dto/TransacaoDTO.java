package com.bancosimplesproject.dto;

import java.math.BigDecimal;

public record TransacaoDTO(BigDecimal value, Long usuarioenvioid, Long usuariorecebeid) {


}
