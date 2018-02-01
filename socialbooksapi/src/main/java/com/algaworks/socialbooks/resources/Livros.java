package com.algaworks.socialbooks.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;

@RestController
@RequestMapping("/livros")
public class Livros {
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Livro> listar(){
		return livrosRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Livro livro){
		livrosRepository.save(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/idLivro}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{idLivro}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("idLivro") Long idLivro){
		Livro livro = livrosRepository.findOne(idLivro);
		
		if(livro == null){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	
	@RequestMapping(value = "/{idLivro}", method = RequestMethod.DELETE)
	public void excluir(@PathVariable("idLivro") Long idLivro){
		livrosRepository.delete(idLivro);
	}
	
	@RequestMapping(value = "/{idLivro}", method = RequestMethod.PUT)
	public void atualizar(@RequestBody Livro livro, @PathVariable("idLivro") Long idLivro){
		livro.setId(idLivro);
		livrosRepository.save(livro);
	}
}
