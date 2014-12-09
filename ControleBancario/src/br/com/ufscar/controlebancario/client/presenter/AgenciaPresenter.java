package br.com.ufscar.controlebancario.client.presenter;

import java.util.List;

import br.com.ufscar.controlebancario.client.ControleBancarioServiceAsync;
import br.com.ufscar.controlebancario.client.event.MenuEvent;
import br.com.ufscar.controlebancario.shared.Constantes;
import br.com.ufscar.controlebancario.shared.Agencia;

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

public class AgenciaPresenter implements Presenter {

	private final ControleBancarioServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private List<Agencia> agencia;
	
	public interface Display {
		Widget asWidget();
		public HasSelectHandlers getAddButton();
		void setData(List<Agencia> data);
		HasSelectHandlers getDeleteButton();
		void setEnabledRemoveButton(boolean enabled);
		Grid<Agencia> getGrid();
		HasSelectHandlers getVoltarButton();
	}
	
	public AgenciaPresenter(ControleBancarioServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	
	@Override
	public void go(HasWidgets Agenciainer) {
		Agenciainer.clear();
		Agenciainer.add(display.asWidget());
		bind();
		fetchAgencia();
	}

	public void bind() {


		display.getAddButton().addSelectHandler(new SelectHandler() {
			@Override
	        public void onSelect(SelectEvent event) {
	          eventBus.fireEvent(new MenuEvent(Constantes.MENU_ITEM_INCLUIR_CONTA));
			}
	      });
		
		display.getGrid().getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<Agencia>() {
			public void onSelectionChanged(SelectionChangedEvent<Agencia> event) {
				display.setEnabledRemoveButton(!event.getSelection().isEmpty());
			}
		});
		
		display.getDeleteButton().addSelectHandler(new SelectHandler(){
			public void onSelect(SelectEvent event) {
				Grid<Agencia> grid = display.getGrid();
				for(Agencia Agencia : grid.getSelectionModel().getSelectedItems()) {
					grid.getStore().remove(Agencia);
					rpcService.removeAgencia(Agencia, new AsyncCallback<Void>() {

						public void onFailure(Throwable caught) {
							Window.alert("Falha ao deletar o Agencia");
						}

						public void onSuccess(Void result) {
							Window.alert("Agencia deletada com sucesso");
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
	
	public void setAgencia(List<Agencia> agencia) {
		this.agencia = agencia;
	}


	private void fetchAgencia() {
		rpcService.listAgencia(new AsyncCallback<List<Agencia>>() {
			public void onSuccess(List<Agencia> result) {
				display.setData(result);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Falha ao recuperar o banco");
			}

		});
	}
}
