package com.algaworks.socialbooks.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public void salvar(@RequestBody Livro livro){
		livrosRepository.save(livro);
	}
	
	@RequestMapping(value = "/{idLivro}", method = RequestMethod.GET)
	public Livro buscar(@PathVariable("idLivro") Long idLivro){
		return livrosRepository.findOne(idLivro);
	}
}
