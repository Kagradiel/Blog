package com.generation.blogpessoal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class PostagemUsuarioDTO {
	
	@NotNull(message = "O atributo Descrição é obrigatório")
    @Schema(description = "Id do usuario para GET", example = "1")
	private Long id;

	public Long getId() {
		return id;
	}
	
	

}
