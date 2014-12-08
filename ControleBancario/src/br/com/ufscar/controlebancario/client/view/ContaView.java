package br.com.ufscar.controlebancario.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.ufscar.controlebancario.client.presenter.ContaPresenter;
import br.com.ufscar.controlebancario.shared.Banco;
import br.com.ufscar.controlebancario.shared.Cliente;
import br.com.ufscar.controlebancario.shared.Conta;
import br.com.ufscar.controlebancario.shared.ContaCliente;

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

public class ContaView implements IsWidget, ContaPresenter.Display {

	private ContentPanel panel;
	private static final ContaProperties props = GWT.create(ContaProperties.class);
	private TextButton incluirButton;
	private TextButton removeButton;
	private TextButton voltarButton;
	private ToolBar toolBar;
	ListStore<Conta> store;
	Grid<Conta> grid;
	
	@Override
	public Widget asWidget() {

		createGrid();

		return panel;
	}

	interface ContaProperties extends PropertyAccess<Cliente> {
		ModelKeyProvider<Conta> idConta();

		LabelProvider<Conta> numero();

		@Path("numero")
		ValueProvider<Conta, String> numeroValueProvider();
		ValueProvider<Conta, String> tipoConta();
		ValueProvider<Conta, Date> dataAbertura();
		ValueProvider<Conta, Float> saldo();
		ValueProvider<Conta, String> titular();
	}

	private void createGrid(){
		
		IdentityValueProvider<Conta> identity = new IdentityValueProvider<Conta>();
		CheckBoxSelectionModel<Conta> chk = new CheckBoxSelectionModel<Conta>(identity);
		final NumberFormat number = NumberFormat.getFormat("0.00");

		RowExpander<Conta> expander = new RowExpander<Conta>(new AbstractCell<Conta>() {
			@Override
			public void render(Context context, Conta value, SafeHtmlBuilder sb) {
				sb.appendHtmlConstant("<p style='margin: 5px 5px 10px'><b>Numero:</b>" + value.getNumero()+ "</p>");
				Iterator<ContaCliente> i = value.getContaCliente().iterator();
				sb.appendHtmlConstant("<p style='margin: 5px 5px 10px'><b>Titular:</b> " + ((i.hasNext())?i.next().getTitular():""));
			}
		});

		ColumnConfig<Conta, String> numeroCol = new ColumnConfig<Conta, String>(props.numeroValueProvider(), 200, "Numero");
		ColumnConfig<Conta, String> tipoContaCol = new ColumnConfig<Conta, String>(props.tipoConta(), 100, "Tipo Conta");
		ColumnConfig<Conta, Float> saldoCol = new ColumnConfig<Conta, Float>(props.saldo(), 75, "Saldo");
		
		saldoCol.setCell(new AbstractCell<Float>() {

			@Override
			public void render(Context context, Float value, SafeHtmlBuilder sb) {
				String style = "style='color: " + (value < 0 ? "red" : "green") + "'";
				String v = number.format(value);
				sb.appendHtmlConstant("<span " + style + " qtitle='Change' qtip='" + v + "'>" + v + "</span>");
			}
		});

		ColumnConfig<Conta, Date> dataCol = new ColumnConfig<Conta, Date>(props.dataAbertura(), 100, "Data Abertura");
		dataCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

		List<ColumnConfig<Conta, ?>> l = new ArrayList<ColumnConfig<Conta, ?>>();
		l.add(expander);
		l.add(numeroCol);
		l.add(tipoContaCol);
		l.add(saldoCol);
		l.add(dataCol);
		l.add(chk.getColumn());
		ColumnModel<Conta> cm = new ColumnModel<Conta>(l);

		store = new ListStore<Conta>(props.idConta());
		//store.addAll(TestData.getStocks());

		panel = new ContentPanel();
		panel.setHeadingText("RowExpander Grid");
		panel.getHeader().setIcon(ExampleImages.INSTANCE.table());
		panel.setPixelSize(600, 320);
		panel.addStyleName("margin-10");

		grid = new Grid<Conta>(store, cm);
		grid.setSelectionModel(chk);
		grid.getView().setAutoExpandColumn(numeroCol);
		grid.setBorders(false);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);

		expander.initPlugin(grid);
		panel.setWidget(grid);
		
		
		incluirButton = new TextButton("Incluir Conta");
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
	public void setData(List<Conta> data) {
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
	public Grid<Conta> getGrid() {
		return this.grid;
	}

	@Override
	public HasSelectHandlers getVoltarButton() {
		return voltarButton;
	}




}
