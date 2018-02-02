package com.algaworks.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	private boolean livroExiste(Livro livro){
		try {
			buscar(livro.getId());
		} catch (LivroNaoEncontradoException e) {
			return false;
		}
		return true;
	}
	
	public List<Livro> listar(){
		return livrosRepository.findAll();
	}
	
	public Livro buscar(Long idLivro){
		Livro livro = livrosRepository.findOne(idLivro);
		
		if(livro == null){
			throw new LivroNaoEncontradoException("O livro " + idLivro + " não foi encontrado.");
		}
		
		return livro;
	}
	
	public Livro salvar(Livro livro){
		livro.setId(null);
		return livrosRepository.save(livro);
	}
	
	public void excluir(Long idLivro){
		try {
			livrosRepository.delete(idLivro);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("Não existe livro de id "+ idLivro +" para ser excluído.");
		}		
	}
	
	public void atualizar(Livro livro){
		if (livroExiste(livro)){
			livrosRepository.save(livro);
		}else{
			throw new LivroNaoEncontradoException("Não existe livro de id "+ livro.getId() +" para ser atualizado.");
		}
	}
}
