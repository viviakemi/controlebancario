package br.com.ufscar.controlebancario.server;

import java.util.List;

import br.com.ufscar.controlebancario.shared.Banco;
import br.com.ufscar.controlebancario.shared.Conta;
import br.com.ufscar.controlebancario.client.ControleBancarioDAO;
import br.com.ufscar.controlebancario.client.ControleBancarioService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ControleBancarioServiceImpl extends RemoteServiceServlet implements ControleBancarioService {

	private ControleBancarioDAO bancoDAO = new ControleBancarioJdoDAO(); 
	
	public void addBanco(Banco banco) {
		bancoDAO.addBanco(banco);
		
	}


	public void removeBanco(Banco banco) {
		bancoDAO.removeBanco(banco);
	}


	public List<Banco> listBanco() {
		return bancoDAO.listBanco();
	}


	public void updateBanco(Banco banco) {
		bancoDAO.updateBanco(banco);
	}


	@Override
	public void addConta(Conta conta) {
		bancoDAO.addConta(conta);
	}


	@Override
	public List<Conta> listConta() {
		return bancoDAO.listConta();
	}


	@Override
	public void removeConta(Conta conta) {
		bancoDAO.removeConta(conta);
	}
 
}
