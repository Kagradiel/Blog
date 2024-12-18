package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.dto.TemaCreateDTO;
import com.generation.blogpessoal.dto.TemaUpdateDTO;
import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.TemaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Temas", description = "Operações relacionadas aos temas do blog")
public class TemaController {
	
	@Autowired
	private TemaRepository temaRepository;
	
	
	@Operation(
		    summary = "Buscar todos os temas",
		    description = "Este endpoint retorna uma lista de todos os temas disponíveis no sistema em formato JSON.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(temaRepository.findAll());
	}
	
	@Operation(
		    summary = "Buscar tema por ID", 
		    description = "Este endpoint retorna os detalhes de um tema baseado no seu ID. Se o tema for encontrado, retorna os dados do tema em formato JSON. Caso contrário, retorna um erro 404 (não encontrado).")
	@GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tema> getById(@Parameter(description = "ID do tema", required = true) @PathVariable Long id) {
		return temaRepository.findById(id)
				.map(tema -> ResponseEntity.ok(tema))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@Operation(
		    summary = "Buscar temas por descrição", 
		    description = "Este endpoint retorna uma lista de temas cujas descrições contêm a palavra-chave fornecida. A busca é case insensitive.")
	@GetMapping(value = "/descricao/{descricao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tema>> getByTitle(@Parameter(description = "Descrição do tema", required = true) @PathVariable String descricao){
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	
	@Operation(
		    summary = "Cadastrar um novo tema", 
		    description = "Este endpoint cria um novo tema no sistema. Recebe a descrição do tema e retorna o tema criado em formato JSON.")
	@PostMapping(
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    )
	public ResponseEntity<Tema> post(@Valid @RequestBody TemaCreateDTO temaCreateDTO){
		
		Tema tema = new Tema();
		tema.setDescricao(temaCreateDTO.getDescricao());
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(temaRepository.save(tema));
	}

	
	@Operation(
		    summary = "Atualizar um tema existente", 
		    description = "Este endpoint atualiza os dados de um tema existente no sistema. Recebe os dados atualizados do tema e retorna o tema atualizado em formato JSON.")
	@PutMapping(
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    ) 
	public ResponseEntity<Tema> put(@Valid @RequestBody TemaUpdateDTO temaUpdateDTO){
		return temaRepository.findById(temaUpdateDTO.getId())
				.map(tema -> {
					
					tema.setDescricao(temaUpdateDTO.getDescricao());
					
					return ResponseEntity.status(HttpStatus.CREATED)
						.body(temaRepository.save(tema));
					
				}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
			
	}
	
	@Operation(
		    summary = "Deletar um tema", 
		    description = "Este endpoint remove um tema do sistema pelo seu ID. Se o tema for encontrado, ele será deletado, caso contrário, retorna um erro 404 (não encontrado).")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@Parameter(description = "ID do tema", required = true) @PathVariable Long id) {
		Optional<Tema> tema = temaRepository.findById(id);
		
		if(tema.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado");
		
		temaRepository.deleteById(id);
	}

}
