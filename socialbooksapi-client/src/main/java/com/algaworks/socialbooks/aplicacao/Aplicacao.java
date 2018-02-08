package com.algaworks.socialbooks.aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.algaworks.socialbooks.client.LivrosClient;
import com.algaworks.socialbooks.client.domain.Livro;

public class Aplicacao {
	
	public static void main(String[] args) throws ParseException {
		
		LivrosClient cliente = new LivrosClient();
		
		Livro livro = new Livro();
		livro.setNome("REST Aplicado");
		livro.setEditora("Algaworks");
		livro.setResumo("Aborda tudo o que você presica sobre APIs Rest!");
		SimpleDateFormat publicacao = new SimpleDateFormat("dd/MM/yyyy");
		livro.setPublicacao(publicacao.parse("08/02/2018"));
		
		String localLivroSalvo = cliente.salvar(livro);
		System.out.println("URI do livro salvo: " + localLivroSalvo);
		
		List<Livro> listaLivros = cliente.listar();
		
		for(Livro livros : listaLivros){
			System.out.println("--------------------------------------------------------------");
			System.out.println("Livro ID: " + livros.getId());
			System.out.println("Livro: " + livros.getNome());
			System.out.println("Editora: " + livros.getEditora());
		}
	}
}
