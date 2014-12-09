package br.com.ufscar.controlebancario.shared;

import java.io.Serializable;

public class Agencia implements Serializable{

	private int numero;
	private int idAgencia;
	private int bancoCodigo;
	private String nome;
	
	

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}

	public int getBancoCodigo() {
		return bancoCodigo;
	}

	public void setBancoCodigo(int bancoCodigo) {
		this.bancoCodigo = bancoCodigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Agencia() {
		super();
	}

	public Agencia(int numero, int idagencia, int banco_codigo, String nome) {
		super();
		this.numero = numero;
		this.idAgencia = idagencia;
		this.bancoCodigo = banco_codigo;
		this.nome = nome;
	}

}
