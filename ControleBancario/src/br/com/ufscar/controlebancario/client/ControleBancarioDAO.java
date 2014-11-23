package br.com.ufscar.controlebancario.client;

import java.util.List;

import br.com.ufscar.controlebancario.shared.Banco;

public interface ControleBancarioDAO {

	void addBanco(Banco banco);
	void removeBanco(Banco banco);
	void updateBanco(Banco banco);
	List<Banco> listBanco();
}
