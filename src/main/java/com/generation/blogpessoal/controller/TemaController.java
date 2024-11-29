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

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Temas", description = "Operações relacionadas aos temas do blog")
public class TemaController {
	
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(temaRepository.findAll());
	}
	
	@GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tema> getById(@PathVariable Long id) {
		return temaRepository.findById(id)
				.map(tema -> ResponseEntity.ok(tema))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/descricao/{descricao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tema>> getByTitle(@PathVariable String descricao){
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
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
	
	@PutMapping(
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    ) 
	public ResponseEntity<Tema> put(@Valid @RequestBody TemaUpdateDTO temaUpdateDTO){
		return temaRepository.findById(temaUpdateDTO.getId())
				.map(tema -> {
					
					tema.setId(temaUpdateDTO.getId());
					tema.setDescricao(temaUpdateDTO.getDescricao());
					
					return ResponseEntity.status(HttpStatus.CREATED)
						.body(temaRepository.save(tema));
					
				}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
			
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Tema> tema = temaRepository.findById(id);
		
		if(tema.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado");
		
		temaRepository.deleteById(id);
	}

}
