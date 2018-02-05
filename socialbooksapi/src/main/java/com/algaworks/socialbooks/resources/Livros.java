package com.algaworks.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.services.LivrosService;

@RestController
@RequestMapping("/livros")
public class Livros {
	
	@Autowired
	private LivrosService livrosService;
	
	@RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Livro>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro){
		
		livrosService.salvar(livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idLivro}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{idLivro}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> buscar(@PathVariable("idLivro") Long idLivro){
		
		Livro livro = livrosService.buscar(idLivro);
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
	}
	
	@RequestMapping(value = "/{idLivro}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("idLivro") Long idLivro){
		
		livrosService.excluir(idLivro);		
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@RequestMapping(value = "/{idLivro}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("idLivro") Long idLivro){
		
		livro.setId(idLivro);
		livrosService.atualizar(livro);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@RequestMapping(value = "/{idLivro}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@RequestBody Comentario comentario, @PathVariable("idLivro") Long idLivro){
		
		livrosService.salvarComentario(idLivro, comentario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{idLivro}/comentarios", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("idLivro") Long idLivro){
		
		List<Comentario> comentarios = livrosService.listarComentario(idLivro);
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
}
