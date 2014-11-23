package br.com.ufscar.controlebancario.client.presenter;

import java.util.Date;

import br.com.ufscar.controlebancario.client.ControleBancarioServiceAsync;
import br.com.ufscar.controlebancario.client.event.MenuEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.core.client.resources.ThemeStyles.Styles;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.DateMenu;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class MainPresenter implements Presenter, IsWidget{

	private final ControleBancarioServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;


	public MainPresenter(ControleBancarioServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}


	public interface Display {
		Widget asWidget();
	}

	@Override
	public void go(HasWidgets container) {
		//bind();
		container.clear();
		container.add(display.asWidget());	
		container.add(asWidget());
	}

	
	interface MyUiBinder extends UiBinder<Widget, MainPresenter> {

	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	DateMenu dateMenu;

	@UiField(provided = true)
	Styles styles = ThemeStyles.get().style();

	private Widget widget;

	public Widget asWidget() {
		if (widget == null) {
			widget = uiBinder.createAndBindUi(this);
		}

		return widget;
	}


	@UiHandler(value = {"menuNew", "menuOpen", "menuConta", "menuCliente", "menuEdit", "menuSearch", "menuFoo", "menuTheme"})
	public void onMenuSelection(SelectionEvent<Item> event) {
		MenuItem item = (MenuItem) event.getSelectedItem();
		Info.display("Action", "You selected the " + item.getText());
		eventBus.fireEvent(new MenuEvent(item.getText()));
	}
	
	@UiHandler("dateMenu")
	  public void onDateSelect(ValueChangeEvent<Date> event) {
	    Date d = event.getValue();
	    DateTimeFormat f = DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT);
	    Info.display("Value Changed", "You selected " + f.format(d));
	    dateMenu.hide(true);
	  }

}
