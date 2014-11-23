package br.com.ufscar.controlebancario.client;

import br.com.ufscar.controlebancario.client.event.AddBancoEvent;
import br.com.ufscar.controlebancario.client.event.AddBancoEventHandler;
import br.com.ufscar.controlebancario.client.event.BancoUpdatedEvent;
import br.com.ufscar.controlebancario.client.event.BancoUpdatedEventHandler;
import br.com.ufscar.controlebancario.client.event.EditBancoCancelledEvent;
import br.com.ufscar.controlebancario.client.event.EditBancoCancelledEventHandler;
import br.com.ufscar.controlebancario.client.event.EditBancoEvent;
import br.com.ufscar.controlebancario.client.event.EditBancoEventHandler;
import br.com.ufscar.controlebancario.client.event.MenuEvent;
import br.com.ufscar.controlebancario.client.event.MenuEventHandler;
import br.com.ufscar.controlebancario.client.presenter.BancoPresenter;
import br.com.ufscar.controlebancario.client.presenter.MainPresenter;
import br.com.ufscar.controlebancario.client.presenter.Presenter;
import br.com.ufscar.controlebancario.client.view.BancoView;
import br.com.ufscar.controlebancario.client.view.MainView;
import br.com.ufscar.controlebancario.shared.Constantes;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final ControleBancarioServiceAsync rpcService; 
	private HasWidgets container;

	public AppController(ControleBancarioServiceAsync rpcService, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(AddBancoEvent.TYPE,
				new AddBancoEventHandler() {
			public void onAddBanco(AddBancoEvent event) {
				doAddNewContact();
			}
		});  

		eventBus.addHandler(EditBancoEvent.TYPE,
				new EditBancoEventHandler() {
			public void onEditContact(EditBancoEvent event) {
				doEditContact(event.getId());
			}
		});  

		eventBus.addHandler(EditBancoCancelledEvent.TYPE,
				new EditBancoCancelledEventHandler() {
			public void onEditContactCancelled(EditBancoCancelledEvent event) {
				doEditContactCancelled();
			}
		});  

		eventBus.addHandler(BancoUpdatedEvent.TYPE,
				new BancoUpdatedEventHandler() {
			public void onContactUpdated(BancoUpdatedEvent event) {
				doContactUpdated();
			}
		});  

		eventBus.addHandler(MenuEvent.TYPE,
				new MenuEventHandler() {
			public void onClick(MenuEvent event) {
				doMenuClick(event.getMenuItem());
			}
		});  
	}

	private void doAddNewContact() {
		History.newItem("add");
	}

	private void doEditContact(String id) {
		History.newItem("edit", false);
		// Presenter presenter = new EditContactPresenter(rpcService, eventBus, new EditContactView(), id);
		// presenter.go(container);
	}

	private void doEditContactCancelled() {
		History.newItem("list");
	}

	private void doContactUpdated() {
		History.newItem("list");
	}

	private void doMenuClick(String menuItem) {
		History.newItem(menuItem);
	}

	public void go(final HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("menu");
		}
		else {
			History.fireCurrentHistoryState();
		}
	}



	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals(Constantes.MENU)){
				presenter = new MainPresenter(rpcService, eventBus, new MainView());
			}if (token.equals(Constantes.MENU_ITEM_BANCO)){
				presenter = new BancoPresenter(rpcService, eventBus, new BancoView());
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	} 
}
