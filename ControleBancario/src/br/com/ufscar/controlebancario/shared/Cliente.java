package br.com.ufscar.controlebancario.shared;

public class Cliente {

	private int idCliente;
	private String nome;
	
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cliente(){
		
	}
	
	public Cliente(int idCliente, String nome) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
	}


}
