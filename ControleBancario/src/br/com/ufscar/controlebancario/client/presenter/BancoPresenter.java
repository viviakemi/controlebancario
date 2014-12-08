package br.com.ufscar.controlebancario.client.presenter;

import java.util.Collection;
import java.util.List;

import br.com.ufscar.controlebancario.client.ControleBancarioServiceAsync;
import br.com.ufscar.controlebancario.client.event.MenuEvent;
import br.com.ufscar.controlebancario.shared.Banco;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.ibm.icu.util.CharsTrie.Iterator;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.Grid.GridCell;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class BancoPresenter implements Presenter{

	private final ControleBancarioServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public BancoPresenter(ControleBancarioServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	private List<Banco> banco;
	public interface Display {
		HasSelectHandlers getAddButton();
		HasSelectHandlers getSalvarButton();
		HasSelectHandlers getDeleteButton();
		HasSelectHandlers getVoltarButton();
		HasClickHandlers getList();
		void setData(List<Banco> data);
		Widget asWidget();
		Grid<Banco> getGrid();
		GridRowEditing<Banco> getGridEditing();
		void setEnabledRemoveButton(boolean enabled);
	}

	public void go(final HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();
		fetchBanco();
	}

	public void bind() {

		display.getDeleteButton().addSelectHandler(new SelectHandler(){
			public void onSelect(SelectEvent event) {
				Grid<Banco> grid = display.getGrid();
				for(Banco banco : grid.getSelectionModel().getSelectedItems()) {
					grid.getStore().remove(banco);
					rpcService.removeBanco(banco, new AsyncCallback<Void>() {

						public void onFailure(Throwable caught) {
							Window.alert("Falha ao recuperar o banco");
						}

						public void onSuccess(Void result) {

						}

					});
				}
				display.setEnabledRemoveButton(false);
			}
		});

		display.getGrid().getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<Banco>() {
			public void onSelectionChanged(SelectionChangedEvent<Banco> event) {
				display.setEnabledRemoveButton(!event.getSelection().isEmpty());
			}
		});
		
		display.getAddButton().addSelectHandler(new SelectHandler() {
			@Override
	        public void onSelect(SelectEvent event) {
	          Banco banco = new Banco();
	          //banco.setCodigo(display.getGrid().getStore().size() + 1);
	          GridRowEditing<Banco> editing = display.getGridEditing();
	          editing.cancelEditing();
	          display.getGrid().getStore().add(banco);
	 
	          int row =  display.getGrid().getStore().indexOf(banco);
	          editing.startEditing(new GridCell(row, 0));
	        }
	      });
		
		display.getVoltarButton().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				eventBus.fireEvent(new MenuEvent("menu"));
			}
		});
		
		display.getSalvarButton().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				Grid<Banco> grid = display.getGrid();
				//Collection<Store<Banco>.Record> records = grid.getStore().getModifiedRecords();
				ListStore<Banco> list = grid.getStore();
				list.commitChanges();
				//java.util.Iterator<Store<Banco>.Record> i = records.iterator();
				//while (i.hasNext()) {
					//Banco banco = i.next().getModel();
				for (Banco banco: list.getAll()){
					rpcService.addBanco(banco, new AsyncCallback<Void>() {

						public void onFailure(Throwable caught) {
							Window.alert("Falha ao incluir no banco");
						}

						public void onSuccess(Void result) {
						}

					});
				}
				
			}
		});
			
	}
	 
	 
	public void setBanco(List<Banco> banco) {
		this.banco = banco;
	}

	public Banco getBancoDetail(int index) {
		return banco.get(index);
	}


	private void fetchBanco() {
		rpcService.listBanco(new AsyncCallback<List<Banco>>() {
			public void onSuccess(List<Banco> result) {
				display.setData(result);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Falha ao recuperar o banco");
			}

		});
	}
}
