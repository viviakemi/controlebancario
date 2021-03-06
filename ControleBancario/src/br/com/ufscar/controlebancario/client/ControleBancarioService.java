package br.com.ufscar.controlebancario.client;

import java.util.List;

import br.com.ufscar.controlebancario.shared.Banco;
import br.com.ufscar.controlebancario.shared.Conta;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("controlebancario")
public interface ControleBancarioService extends RemoteService {

	void addBanco(Banco banco);
	void removeBanco(Banco banco);
	void updateBanco(Banco banco);
	List<Banco> listBanco();
	
	void addConta(Conta conta);
	List<Conta> listConta();
	void removeConta(Conta conta);
}
