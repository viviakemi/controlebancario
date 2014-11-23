package br.com.ufscar.controlebancario.server;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.ufscar.controlebancario.client.ControleBancarioDAO;
import br.com.ufscar.controlebancario.server.util.HibernateUtil;
import br.com.ufscar.controlebancario.shared.Banco;

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

}
