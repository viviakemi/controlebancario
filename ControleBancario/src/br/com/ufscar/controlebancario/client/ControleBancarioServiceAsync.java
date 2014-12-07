package br.com.ufscar.controlebancario.client;

import java.util.List;

import br.com.ufscar.controlebancario.shared.Banco;
import br.com.ufscar.controlebancario.shared.Conta;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ControleBancarioServiceAsync {

	void addBanco(Banco banco, AsyncCallback<Void> callback);

	void listBanco(AsyncCallback<List<Banco>> callback);

	void removeBanco(Banco banco, AsyncCallback<Void> callback);

	void updateBanco(Banco banco, AsyncCallback<Void> callback);

	void addConta(Conta conta, AsyncCallback<Void> callback);

	void listConta(AsyncCallback<List<Conta>> callback);

}
