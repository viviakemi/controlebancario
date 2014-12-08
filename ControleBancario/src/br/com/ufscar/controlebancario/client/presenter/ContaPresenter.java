package br.com.ufscar.controlebancario.client.presenter;

import java.util.List;

import br.com.ufscar.controlebancario.client.ControleBancarioServiceAsync;
import br.com.ufscar.controlebancario.client.event.MenuEvent;
import br.com.ufscar.controlebancario.shared.Constantes;
import br.com.ufscar.controlebancario.shared.Conta;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class ContaPresenter implements Presenter {

	private final ControleBancarioServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private List<Conta> conta;
	
	public interface Display {
		Widget asWidget();
		public HasSelectHandlers getAddButton();
		void setData(List<Conta> data);
		HasSelectHandlers getDeleteButton();
		void setEnabledRemoveButton(boolean enabled);
		Grid<Conta> getGrid();
		HasSelectHandlers getVoltarButton();
	}
	
	public ContaPresenter(ControleBancarioServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();
		fetchConta();
	}

	public void bind() {


		display.getAddButton().addSelectHandler(new SelectHandler() {
			@Override
	        public void onSelect(SelectEvent event) {
	          eventBus.fireEvent(new MenuEvent(Constantes.MENU_ITEM_INCLUIR_CONTA));
			}
	      });
		
		display.getGrid().getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<Conta>() {
			public void onSelectionChanged(SelectionChangedEvent<Conta> event) {
				display.setEnabledRemoveButton(!event.getSelection().isEmpty());
			}
		});
		
		display.getDeleteButton().addSelectHandler(new SelectHandler(){
			public void onSelect(SelectEvent event) {
				Grid<Conta> grid = display.getGrid();
				for(Conta conta : grid.getSelectionModel().getSelectedItems()) {
					grid.getStore().remove(conta);
					rpcService.removeConta(conta, new AsyncCallback<Void>() {

						public void onFailure(Throwable caught) {
							Window.alert("Falha ao deletar o conta");
						}

						public void onSuccess(Void result) {
							Window.alert("Conta deletada com sucesso");
						}

					});
				}
				display.setEnabledRemoveButton(false);
			}
		});
		
		display.getVoltarButton().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				eventBus.fireEvent(new MenuEvent("menu"));
			}
		});
			
	}
	
	public void setConta(List<Conta> conta) {
		this.conta = conta;
	}


	private void fetchConta() {
		rpcService.listConta(new AsyncCallback<List<Conta>>() {
			public void onSuccess(List<Conta> result) {
				display.setData(result);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Falha ao recuperar o banco");
			}

		});
	}
}
