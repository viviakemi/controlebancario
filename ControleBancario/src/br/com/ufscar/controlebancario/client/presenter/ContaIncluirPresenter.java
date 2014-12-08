package br.com.ufscar.controlebancario.client.presenter;

import java.util.HashSet;
import java.util.Set;

import br.com.ufscar.controlebancario.client.ControleBancarioServiceAsync;
import br.com.ufscar.controlebancario.client.event.MenuEvent;
import br.com.ufscar.controlebancario.shared.Cliente;
import br.com.ufscar.controlebancario.shared.Constantes;
import br.com.ufscar.controlebancario.shared.Conta;
import br.com.ufscar.controlebancario.shared.ContaCliente;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class ContaIncluirPresenter implements Presenter {

	private final ControleBancarioServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	public interface Display {
		Widget asWidget();
		public HasSelectHandlers getIncluirButton();
		public HasSelectHandlers getVoltarButton();
		public FramedPanel getPanel();
		public TextField getNumero();
		public TextField getSaldo();
		public Radio getRadio1();
		public Radio getRadio2();
		public DateField getDataAbertura();
		public ComboBox<Cliente> getComboTitular();
		public Grid<Cliente> getGrid2();
	}
	
	public ContaIncluirPresenter(ControleBancarioServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	public void bind() {


		display.getVoltarButton().addSelectHandler(new SelectHandler() {
			@Override
	        public void onSelect(SelectEvent event) {
	          eventBus.fireEvent(new MenuEvent(Constantes.MENU_ITEM_CONTA));
			}
	      });
		
		display.getIncluirButton().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {

				Conta conta = new Conta(
						1,
						display.getRadio1().getValue()?"1":"2",
						display.getNumero().getText(),
						display.getDataAbertura().getValue(),
						Float.parseFloat(display.getSaldo().getValue()),
						null);
				Set<ContaCliente> contaCliente = new HashSet<ContaCliente>();
				
				//titular
				contaCliente.add(new ContaCliente(1,conta,
						0,
						display.getComboTitular().getValue().getIdCliente()));
				
				//outros clientes
				Grid<Cliente> grid2 = display.getGrid2();
				ListStore<Cliente> store = grid2.getStore();
				for (Cliente cliente:store.getAll()){
					contaCliente.add(new ContaCliente(1,conta,
							cliente.getIdCliente(),
							0));
				}
				conta.setContaCliente(contaCliente);
				rpcService.addConta(conta, new AsyncCallback<Void>() {

					public void onFailure(Throwable caught) {
						Window.alert("Falha ao incluir a conta");
					}

					public void onSuccess(Void result) {
						Window.alert("Sucesso ao incluir conta");
					}

				});
			}

			
		});
	}
}
