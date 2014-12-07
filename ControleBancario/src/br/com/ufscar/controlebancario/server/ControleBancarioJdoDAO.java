package br.com.ufscar.controlebancario.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;

import br.com.ufscar.controlebancario.client.ControleBancarioDAO;
import br.com.ufscar.controlebancario.server.util.HibernateUtil;
import br.com.ufscar.controlebancario.shared.Banco;
import br.com.ufscar.controlebancario.shared.Conta;
import br.com.ufscar.controlebancario.shared.ContaCliente;

public class ControleBancarioJdoDAO implements ControleBancarioDAO{

	@Override
	public void addBanco(Banco banco) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.saveOrUpdate(banco);
	    session.getTransaction().commit();
	}
	
	@Override
	public void removeBanco(Banco banco) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.delete(banco);
	    session.getTransaction().commit();
	}

	@Override
	public void updateBanco(Banco banco) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Banco> listBanco() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Banco> bancos = new ArrayList<Banco>(session.createQuery("from Banco").list());
		
		session.getTransaction().commit();
		return bancos;
	}

	@Override
	public void addConta(Conta conta) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(conta);
	    System.out.println(conta.getIdConta());
	    
	    Set<ContaCliente> contaCliente = conta.getContaCliente();
	    Iterator<ContaCliente> i = contaCliente.iterator();
	    while(i.hasNext()){
	    	ContaCliente cc = i.next();
	    	cc.setConta(conta);
	    	session.save(cc);
	    }
	    session.getTransaction().commit();
	}

	@Override
	public List<Conta> listConta() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String hql = "FROM Conta ";
		org.hibernate.Query query = session.createQuery(hql);
		List<Conta> contas = query.list();
		List<Conta> contas2 = new ArrayList<Conta>();
		for(Conta conta:contas){
			Conta contaAux = new Conta(conta.getIdConta(), conta.getTipoConta(), conta.getNumero(),
					conta.getDataAbertura(), conta.getSaldo(), conta.getContaCliente());
			Set<ContaCliente> ccAux = conta.getContaCliente();
			Set<ContaCliente> ccAux2 = new HashSet<ContaCliente>();
			Iterator<ContaCliente> i = ccAux.iterator();
			while (i.hasNext()){
				ContaCliente cc = i.next();
				ccAux2.add(new ContaCliente(cc.getIdContaCliente(), contaAux, cc.getIdCliente(), cc.getTitular()));
				
			}
			contaAux.setContaCliente(ccAux2);
			contas2.add(contaAux);
		}
		
		session.getTransaction().commit();
		return contas2;
	}

}
