package com.algaworks.socialbooks.services.exceptions;

public class AutorExistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8741303003716298231L;
	
	public AutorExistenteException(String msg){
		super(msg);
	}
	
	public AutorExistenteException(String msg, Throwable causa){
		super(msg, causa);
	}
}
