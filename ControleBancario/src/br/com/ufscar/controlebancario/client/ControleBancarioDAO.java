package br.com.ufscar.controlebancario.client;

import java.util.List;

import br.com.ufscar.controlebancario.shared.Agencia;
import br.com.ufscar.controlebancario.shared.Banco;
import br.com.ufscar.controlebancario.shared.Conta;

public interface ControleBancarioDAO {

	void addBanco(Banco banco);
	void removeBanco(Banco banco);
	void updateBanco(Banco banco);
	List<Banco> listBanco();
	
	void addConta(Conta conta);
	List<Conta> listConta();
	void removeConta(Conta conta);
	
	void removeAgencia(Agencia agencia);
	void addAgencia(Agencia agencia);
	List<Agencia> listAgencia();
	void updateAgencia(Agencia agencia);
}
