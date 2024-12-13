package com.generation.blogpessoal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostagemCreateDTO {
	
    @NotBlank(message = "O atributo título é obrigatório")
    @Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 05 caracteres e no máximo 100")
    @Schema(description = "Título da postagem", example = "Meu título incrível")
    private String titulo;

    @NotBlank(message = "O atributo texto é obrigatório")
    @Size(min = 10, max = 1000, message = "O atributo texto deve conter no mínimo 10 caracteres e no máximo 1000")
    @Schema(description = "Texto da postagem", example = "Este é um exemplo de texto para a postagem.")
    private String texto;

    @JsonIgnoreProperties("postagem")
    private PostagemTemaDTO tema;
    
    @JsonIgnoreProperties("postagem")
    private PostagemUsuarioDTO usuario;
    
    
	public PostagemUsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(PostagemUsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public PostagemTemaDTO getTema() {
		return tema;
	}

	public void setTema(PostagemTemaDTO tema) {
		this.tema = tema;
	}

	
	
    
}