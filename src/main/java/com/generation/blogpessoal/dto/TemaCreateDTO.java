package com.generation.blogpessoal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class TemaCreateDTO {
	
	@NotNull(message = "O atributo Descrição é obrigatório")
	@Schema(description = "tema das postagens", example = "Meu tema escolhido")
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
