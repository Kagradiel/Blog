package com.generation.blogpessoal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CadastrarUsuarioDTO {
	
	@NotNull(message = "O atributo nome é obrigatório")
	@Schema(description = "Nome do usuario", example = "Draco Malfoy")
	private String nome;
	
	@NotNull(message = "O atributo usuário é obrigatorio")
	@Email(message = "O atributo usuário deve ser um e-mail válido")
	@Schema(description = "Email do usuario", example = "DracoM@email.com")
	private String usuario;
	
	@NotBlank(message = "O atributo senha é obrigatório")
	@Size(min=8, message ="O atributo senha deve ter no minimo 8 caracteres")
	@Schema(description = "Senha do usuario", example = "MinhaSenhaSecreta")
	private String senha;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Size(max=500, message = "O atributo foto não pode ser maior que 5000 caracteres")
	@Schema(description = "Foto do usuario", example = "https://i.imgur.com/Tk9f10k.png")
	private String foto;

}
