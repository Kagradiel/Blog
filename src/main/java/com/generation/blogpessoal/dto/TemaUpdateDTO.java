package com.generation.blogpessoal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class TemaUpdateDTO {
	
	@NotNull(message = "O atributo ID é obrigatório")
    @Schema(description = "Id da postagem", example = "5")
	private Long id;
	
	@NotNull(message = "O atributo Descrição é obrigatório")
	@Schema(description = "tema das postagens", example = "Meu tema escolhido")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
