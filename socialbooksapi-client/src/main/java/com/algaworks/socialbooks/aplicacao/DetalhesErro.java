package com.algaworks.socialbooks.aplicacao;


public class DetalhesErro {
	
	private String titulo;
	private Long status;
	private Long timeStamp;
	private String msgDesenvolvedor;
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMsgDesenvolvedor() {
		return msgDesenvolvedor;
	}
	public void setMsgDesenvolvedor(String msgDesenvolvedor) {
		this.msgDesenvolvedor = msgDesenvolvedor;
	}
}
