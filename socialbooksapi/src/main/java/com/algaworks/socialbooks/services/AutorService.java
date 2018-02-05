package com.algaworks.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.repository.AutorRepository;
import com.algaworks.socialbooks.services.exceptions.AutorExistenteException;
import com.algaworks.socialbooks.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	
	public List<Autor> listar(){
		return autorRepository.findAll();
	}
	
	public Autor salvar(Autor autor){
		if(autor.getIdAutor() != null){
			Autor a = autorRepository.findOne(autor.getIdAutor());
			
			if(a != null){
				throw new AutorExistenteException("O autor de id "+ autor.getIdAutor() +" já existe no banco de dados!");
			}
		}
		return autorRepository.save(autor);
	}
	
	public Autor buscar(Long idAutor){
		Autor autor = autorRepository.findOne(idAutor);
		
		if(autor == null){
			throw new AutorNaoEncontradoException("Autor de id "+ idAutor +" não foi encontrado.");
		}
		return autor;
	}
}
