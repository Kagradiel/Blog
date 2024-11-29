package com.generation.blogpessoal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class PostagemTemaDTO {
	
	@NotNull(message = "O atributo Descrição é obrigatório")
    @Schema(description = "Id da postagem para GET", example = "5")
	private Long id;

	public Long getId() {
		return id;
	}
	
	

}
