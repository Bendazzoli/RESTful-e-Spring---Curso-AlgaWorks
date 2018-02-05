package com.algaworks.socialbooks.services.exceptions;

public class AutorNaoEncontradoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public AutorNaoEncontradoException(String msg){
		super(msg);
	}
	
	public AutorNaoEncontradoException(String msg, Throwable causa){
		super(msg, causa);
	}
}
