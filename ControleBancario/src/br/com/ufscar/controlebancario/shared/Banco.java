package br.com.ufscar.controlebancario.shared;

import java.io.Serializable;

public class Banco implements Serializable{

	private int codigo;
	private String nome;
	private String cnpj;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public Banco() {
		super();
	}
	
	public Banco(int codigo, String nome, String cnpj) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cnpj = cnpj;
	}

	
	

}
