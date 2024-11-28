package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_postagens")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Id da postagem", example = "5")
	private long id;
	
	@NotBlank(message = "O atributo título é obrigatório")
	@Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 05 caracteres, e no máximo 100")
	@Schema(description = "Título da postagem", example = "Meu título incrível")
	private String titulo;
	
	@NotBlank(message = "O atributo título é obrigatório")
	@Size(min = 10, max = 1000, message = "O atributo título deve conter no mínimo 10 caracteres, e no máximo 1000")
	@Schema(description = "Texto da postagem", example = "Este é um exemplo de texto para a postagem.")
	private String texto;
	
	@UpdateTimestamp
	private LocalDateTime data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	

}
