package br.com.ufscar.controlebancario.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.persistence.OneToMany;

public class Conta implements Serializable{

    private int idConta;
	private String tipoConta;
	private String numero;
	private Date dataAbertura;
	private float saldo;
	private Set<ContaCliente> contaCliente;

	
	public Set<ContaCliente> getContaCliente() {
		return contaCliente;
	}
	public void setContaCliente(Set<ContaCliente> contaCliente) {
		this.contaCliente = contaCliente;
	}
	public int getIdConta() {
		return idConta;
	}
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public float getSaldo() {
		return saldo;
	}
	
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public Conta(int idConta, String tipoConta, String numero,
			Date dataAbertura, float saldo, Set<ContaCliente> contaCliente) {
		super();
		this.idConta = idConta;
		this.tipoConta = tipoConta;
		this.numero = numero;
		this.dataAbertura = dataAbertura;
		this.saldo = saldo;
		this.contaCliente = contaCliente;
	}
	public Conta(){
		
	}

}
