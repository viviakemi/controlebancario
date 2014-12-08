package br.com.ufscar.controlebancario.shared;

import java.io.Serializable;

public class ContaCliente implements Serializable{

    private int idContaCliente;
	private int idCliente;
	private int titular;
	private Conta conta;
	
	public ContaCliente(int idContaCliente, Conta conta, int idCliente,
			int titular) {
		super();
		this.idContaCliente = idContaCliente;
		this.conta = conta;
		this.idCliente = idCliente;
		this.titular = titular;
	}

	public ContaCliente(){
		
	}

	public int getIdContaCliente() {
		return idContaCliente;
	}

	public void setIdContaCliente(int idContaCliente) {
		this.idContaCliente = idContaCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}
	
	

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getTitular() {
		return titular;
	}

	public void setTitular(int titular) {
		this.titular = titular;
	}
	
	
}
