package com.generation.blogpessoal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioLoginDTO {
	
	@NotNull(message = "O atributo usuário é obrigatorio")
	@Email(message = "O atributo usuário deve ser um e-mail válido")
	@Schema(description = "Email do usuario", example = "DracoM@email.com")
	private String usuario;
	
	@NotBlank(message = "O atributo senha é obrigatório")
	@Size(min=8, message ="O atributo senha deve ter no minimo 8 caracteres")
	@Schema(description = "Senha do usuario", example = "MinhaSenhaSecreta")
	private String senha;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
