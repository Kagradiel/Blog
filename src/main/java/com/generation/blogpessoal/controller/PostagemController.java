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

import com.generation.blogpessoal.dto.PostagemCreateDTO;
import com.generation.blogpessoal.dto.PostagemUpdateDTO;
import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.PostagemRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Postagens", description = "Operações relacionadas às postagens do blog")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/titulo/{titulo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping(
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    ) 
	public ResponseEntity<Postagem> post(@Valid @RequestBody PostagemCreateDTO postagemCreateDTO){
		
		Postagem postagem = new Postagem();
        postagem.setTitulo(postagemCreateDTO.getTitulo());
        postagem.setTexto(postagemCreateDTO.getTexto());
        
        Tema tema = new Tema();
        tema.setId(postagemCreateDTO.getTema().getId());
        	
        postagem.setTema(tema);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
	}
	
	@PutMapping(
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    ) 
	public ResponseEntity<Postagem> put(@Valid @RequestBody PostagemUpdateDTO postagemDTO) {
	    return postagemRepository.findById(postagemDTO.getId())
	            .map(postagem -> 
	            {
	            	
	                postagem.setTitulo(postagemDTO.getTitulo());
	                postagem.setTexto(postagemDTO.getTexto());
	                
	                Tema tema = new Tema();
	                tema.setId(postagemDTO.getTema().getId());
	                
	                postagem.setTema(tema);
	                
	                return ResponseEntity.status(HttpStatus.OK)
	                		.body(postagemRepository.save(postagem));
	            })
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		
		if(postagem.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem não encontrada");
		
		postagemRepository.deleteById(id);
	}
	
}
