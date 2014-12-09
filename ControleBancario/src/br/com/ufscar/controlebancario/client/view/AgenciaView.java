package br.com.ufscar.controlebancario.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.openjpa.jdbc.schema.PrimaryKey;

import br.com.ufscar.controlebancario.client.presenter.AgenciaPresenter;
import br.com.ufscar.controlebancario.shared.Agencia;
import br.com.ufscar.controlebancario.shared.Cliente;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.ButtonCell.IconAlign;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.Resources;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowExpander;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class AgenciaView implements IsWidget, AgenciaPresenter.Display {

	private ContentPanel panel;
	private static final AgenciaProperties props = GWT.create(AgenciaProperties.class);
	private TextButton incluirButton;
	private TextButton removeButton;
	private TextButton voltarButton;
	private ToolBar toolBar;
	ListStore<Agencia> store;
	Grid<Agencia> grid;
	
	@Override
	public Widget asWidget() {

		createGrid();

		return panel;
	}

	interface AgenciaProperties extends PropertyAccess<Cliente> {
		ModelKeyProvider<Agencia> idAgencia();

		ValueProvider<Agencia, Integer> numero();
		ValueProvider<Agencia, Integer> bancoCodigo();
		ValueProvider<Agencia, String> nome();
		//ValueProvider<Agencia, Integer> bancoCodigo();
	}

	private void createGrid(){
		
		IdentityValueProvider<Agencia> identity = new IdentityValueProvider<Agencia>();
		CheckBoxSelectionModel<Agencia> chk = new CheckBoxSelectionModel<Agencia>(identity);

		ColumnConfig<Agencia, Integer> bancoCol = new ColumnConfig<Agencia, Integer>(props.bancoCodigo(), 50, "Codigo Banco");
		ColumnConfig<Agencia, Integer> numeroCol = new ColumnConfig<Agencia, Integer>(props.numero(), 200, "Numero");
		ColumnConfig<Agencia, String> nomeCol = new ColumnConfig<Agencia, String>(props.nome(), 130, "Nome");
		List<ColumnConfig<Agencia, ?>> l = new ArrayList<ColumnConfig<Agencia, ?>>();
		
		l.add(bancoCol);
		l.add(numeroCol);
		l.add(nomeCol);
		ColumnModel<Agencia> cm = new ColumnModel<Agencia>(l);

		store = new ListStore<Agencia>(props.idAgencia());
		//store.addAll(TestData.getStocks());

		panel = new ContentPanel();
		panel.setHeadingText("Lista de Agencias");
		panel.getHeader().setIcon(ExampleImages.INSTANCE.table());
		panel.setPixelSize(600, 320);
		panel.addStyleName("margin-10");

		grid = new Grid<Agencia>(store, cm);
		grid.setSelectionModel(chk);
		grid.getView().setAutoExpandColumn(numeroCol);
		grid.setBorders(false);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);

		panel.setWidget(grid);
		
		
		incluirButton = new TextButton("Incluir Agencia");
		incluirButton.setIconAlign(IconAlign.BOTTOM);
		incluirButton.setIcon(Resources.IMAGES.add16());
		toolBar = new ToolBar();
		toolBar.add(incluirButton);
		panel.addTool(toolBar);
		removeButton = new TextButton("Remover as linhas selecionada(s)");
		removeButton.setEnabled(false);
		panel.addButton(removeButton);
		voltarButton = new TextButton("Voltar");
		panel.addButton(voltarButton);
	}

	@Override
	public HasSelectHandlers getAddButton() {
		return incluirButton;
	}

	@Override
	public void setData(List<Agencia> data) {
		store.addAll(data);
		grid.clearSizeCache();
	}

	@Override
	public HasSelectHandlers getDeleteButton() {
		return removeButton;
	}

	@Override
	public void setEnabledRemoveButton(boolean enabled) {
		removeButton.setEnabled(enabled);
		
	}

	@Override
	public Grid<Agencia> getGrid() {
		return this.grid;
	}

	@Override
	public HasSelectHandlers getVoltarButton() {
		return voltarButton;
	}




}
