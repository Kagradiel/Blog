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
import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.PostagemRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Postagens", description = "Operações relacionadas às postagens do blog")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;
	
	
	@Operation(
		    summary = "Buscar todas as postagens", 
		    description = "Este endpoint retorna uma lista de todas as postagens do blog em formato JSON.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	
	@Operation(
		    summary = "Buscar postagem por ID", 
		    description = "Este endpoint retorna os detalhes de uma postagem baseada no seu ID. Se a postagem for encontrada, retorna os dados da postagem em formato JSON. Caso contrário, retorna um erro 404 (não encontrado).")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Postagem> getById(@Parameter(description = "ID da postagem", required = true) @PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@Operation(
		    summary = "Buscar postagens por título", 
		    description = "Este endpoint retorna uma lista de postagens cujos títulos contêm a palavra-chave fornecida. A busca é insensível a maiúsculas e minúsculas.")
	@GetMapping(value = "/titulo/{titulo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Postagem>> getByTitulo(@Parameter(description = "Título da postagem", required = true) @PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	
	@Operation(
		    summary = "Criar uma nova postagem", 
		    description = "Este endpoint cria uma nova postagem no blog. Recebe os dados da postagem em formato JSON e retorna a postagem criada em formato JSON.")
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
        
        Usuario usuario = new Usuario();
        usuario.setId(postagemCreateDTO.getUsuario().getId());
        	
        postagem.setTema(tema);
        postagem.setUsuario(usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
	}
	
	
	@Operation(
		    summary = "Atualizar uma postagem", 
		    description = "Este endpoint atualiza os dados de uma postagem existente. Recebe os dados atualizados da postagem em formato JSON e retorna a postagem atualizada em formato JSON.")
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
	                
	                Usuario usuario = new Usuario();
	                usuario.setId(postagemDTO.getUsuario().getId());
	                	
	                postagem.setTema(tema);
	                postagem.setUsuario(usuario);
	                
	                return ResponseEntity.status(HttpStatus.OK)
	                		.body(postagemRepository.save(postagem));
	            })
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	
	@Operation(
		    summary = "Deletar uma postagem", 
		    description = "Este endpoint remove uma postagem do blog pelo seu ID. Se a postagem for encontrada, ela será deletada. Caso contrário, retorna um erro 404 (não encontrado).")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@Parameter(description = "ID da postagem", required = true) @PathVariable Long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		
		if(postagem.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem não encontrada");
		
		postagemRepository.deleteById(id);
	}
	
}
