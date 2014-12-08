package br.com.ufscar.controlebancario.client.view;

import java.util.ArrayList;
import java.util.List;

import br.com.ufscar.controlebancario.client.presenter.ContaIncluirPresenter;
import br.com.ufscar.controlebancario.shared.Cliente;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.theme.base.client.listview.ListViewDefaultAppearance;
import com.sencha.gxt.theme.base.client.listview.ListViewDefaultAppearance.ListViewDefaultResources;
import com.sencha.gxt.theme.base.client.listview.ListViewDefaultAppearance.ListViewDefaultStyle;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.FormPanelHelper;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class ContaIncluirView implements IsWidget, ContaIncluirPresenter.Display {

	private static final int COLUMN_FORM_WIDTH = 680;
	private VerticalPanel vp;
	private TextButton incluir;
	private TextButton voltar;
	private FramedPanel panel;
	private ComboBox<Cliente> comboTitular;
	private TextField numero;
	private TextField saldo;
	private DateField dataAbertura;
	private Radio radio1;
	private Radio radio2;
	private Grid<Cliente> grid2;


	@Override
	public Widget asWidget() {
		if (vp == null) {
			vp = new VerticalPanel();
			vp.setSpacing(10);
			createColumnForm();
			createTabForm();
		}
		return vp;
	}
	
	 private native String getTableMarkup() /*-{
	    return [ '<table width=100% cellpadding=0 cellspacing=0>',
	        '<tr><td class=fn width=50%></td><td class=ln width=50%></td></tr>',
	        '<tr><td class=company></td><td class=email></td></tr>',
	        '<tr><td class=birthday></td><td class=user></td></tr>',
	        '<tr><td class=editor colspan=2></td></tr>', '</table>'
	 
	    ].join("");
	  }-*/;
	 
	 interface CustomListViewStyle extends ListViewDefaultStyle {
	 }
	 
	 interface CustomListViewResources extends ListViewDefaultResources {

		 @Override
		 @Source({"com/sencha/gxt/theme/base/client/listview/ListView.css", "StyledComboBox.css"})
		 CustomListViewStyle css();

	 }
	 
	 interface ClientProperties extends PropertyAccess<Cliente> {
		 ModelKeyProvider<Cliente> idCliente();

		 LabelProvider<Cliente> nome();

		 @Path("nome")
		 ValueProvider<Cliente, String> nomeValueProvider();
	 }
	 
	 
	 private ColumnModel<Cliente> createColumnList(ClientProperties props, boolean sortable) {
		 ColumnConfig<Cliente, String> cc1 = new ColumnConfig<Cliente, String>(props.nomeValueProvider());
		 cc1.setHeader(SafeHtmlUtils.fromString("Nome"));
		 cc1.setSortable(sortable);

		 List<ColumnConfig<Cliente, ?>> l = new ArrayList<ColumnConfig<Cliente, ?>>();
		 l.add(cc1);
		 return new ColumnModel<Cliente>(l);
	 }

	private void createColumnForm() {
	    panel = new FramedPanel();
	    panel.setHeadingText("Form Example");
	    panel.setWidth(COLUMN_FORM_WIDTH);
	 
	    HtmlLayoutContainer con = new HtmlLayoutContainer(getTableMarkup());
	    panel.add(con, new MarginData(15));
	 
	    int cw = ((COLUMN_FORM_WIDTH - 30)/ 2) - 12;
	 
  
	    ClientProperties props = GWT.create(ClientProperties.class);
	    ListStore<Cliente> clientesCombo = new ListStore<Cliente>(props.idCliente());
	    clientesCombo.add(new Cliente(1, "teste"));

	    ListViewDefaultResources resources = GWT.create(CustomListViewResources.class);
	    ListViewDefaultAppearance<Cliente> appearance = new ListViewDefaultAppearance<Cliente>(resources);
	    ListView<Cliente, String> view = new ListView<Cliente, String>(clientesCombo, props.nomeValueProvider(),
	    		appearance);

	    comboTitular = new ComboBox<Cliente>(clientesCombo, props.nome(), view);

	    comboTitular.setEmptyText("Escolha um titular...");
	    comboTitular.setWidth(150);
	    comboTitular.setTypeAhead(true);
	    comboTitular.setTriggerAction(TriggerAction.ALL);
	    comboTitular.setWidth(cw);
	    con.add(new FieldLabel(comboTitular, "Titular"), new HtmlData(".titular"));


	   /* ComboBox<Cliente> comboCliente = new ComboBox<Cliente>(clientes, props.nome(), view);

	    comboCliente.setEmptyText("Escolha um cliente...");
	    comboCliente.setWidth(150);
	    comboCliente.setTypeAhead(true);
	    comboCliente.setTriggerAction(TriggerAction.ALL);
	    comboCliente.setWidth(cw);
	    con.add(new FieldLabel(comboCliente, "Outros Clientes"), new HtmlData(".cliente"));*/
	

	    ListStore<Cliente> clientes = new ListStore<Cliente>(props.idCliente());
	    clientes.add(new Cliente(1, "teste1"));
	    clientes.add(new Cliente(2, "teste2"));
	    Grid<Cliente> grid1 = new Grid<Cliente>(clientes, createColumnList(props, true));
	    grid1.setBorders(true);
	    grid1.getView().setForceFit(true);


	    clientes = new ListStore<Cliente>(props.idCliente());
	    clientes.addSortInfo(new StoreSortInfo<Cliente>(props.nomeValueProvider(), SortDir.ASC));

	    grid2 = new Grid<Cliente>(clientes, createColumnList(props, true));
	    grid2.setBorders(true);
	    grid2.getView().setForceFit(true);
	    grid1.setWidth(cw);
	    grid2.setWidth(cw);

	    new GridDragSource<Cliente>(grid1).setGroup("top");
	    new GridDragSource<Cliente>(grid2).setGroup("top");

	    new GridDropTarget<Cliente>(grid1).setGroup("top");
	    new GridDropTarget<Cliente>(grid2).setGroup("top");
	   
	    con.add(new FieldLabel(grid1, "Outros Clientes"), new HtmlData(".cliente"));
	    con.add(new FieldLabel(grid2, "Clientes Escolhidos"), new HtmlData(".cliente2"));
	    

	    numero = new TextField();
	    numero.setWidth(cw);
	    con.add(new FieldLabel(numero, "Numero Conta"), new HtmlData(".numero"));
	 
	    saldo = new TextField();
	    saldo.setWidth(cw);
	    con.add(new FieldLabel(saldo, "Saldo"), new HtmlData(".saldo"));
	 
	    dataAbertura = new DateField();
	    dataAbertura.setWidth(cw);
	    con.add(new FieldLabel(dataAbertura, "Data Abertura"), new HtmlData(".dataAbertura"));
	 
	    radio1 = new Radio();
	    radio1.setBoxLabel("Poupanca");
	 
	    radio2 = new Radio();
	    radio2.setBoxLabel("Corrente");
	 
	    HorizontalPanel hp = new HorizontalPanel();
	    hp.add(radio1);
	    hp.add(radio2);
	 
	    con.add(new FieldLabel(hp, "Tipo Conta"), new HtmlData(".tipoCOnta"));
	 
	    ToggleGroup group = new ToggleGroup();
	    group.add(radio1);
	    group.add(radio2);
	 
	    HtmlEditor a = new HtmlEditor();
	    a.setWidth(COLUMN_FORM_WIDTH - 25 - 30);
	    con.add(new FieldLabel(a, "Comentario"), new HtmlData(".editor"));
	 
	    incluir = new TextButton("Incluir");
	    panel.addButton(incluir);
	    voltar = new TextButton("Voltar");
	    panel.addButton(voltar);
	 
	    // need to call after everything is constructed
	    java.util.List<FieldLabel> labels = FormPanelHelper.getFieldLabels(panel);
	    for (FieldLabel lbl : labels) {
	      lbl.setLabelAlign(LabelAlign.TOP);
	    }
	 
	    vp.add(panel);
	  }
	 
	  private void createTabForm() {
	    FormPanel panel = new FormPanel();
	    panel.setWidth(300);
	 
	    TabPanel tabs = new TabPanel();
	    panel.setWidget(tabs);
	 
	    VerticalLayoutContainer p = new VerticalLayoutContainer();
	    p.setLayoutData(new MarginData(8));
	 
	    tabs.add(p, "Person Details");
	 
	    TextField firstName = new TextField();
	    firstName.setAllowBlank(false);
	    firstName.setValue("Darrell");
	    p.add(new FieldLabel(firstName, "First Name"), new VerticalLayoutData(1, -1));
	 
	    TextField lastName = new TextField();
	    lastName.setAllowBlank(false);
	    lastName.setValue("Meyer");
	    p.add(new FieldLabel(lastName, "Last Name"), new VerticalLayoutData(1, -1));
	 
	    TextField email = new TextField();
	    email.setAllowBlank(false);
	    p.add(new FieldLabel(email, "Email"), new VerticalLayoutData(1, -1));
	 
	    p = new VerticalLayoutContainer();
	    p.setLayoutData(new MarginData(8));
	 
	    tabs.add(p, "Phone Numbers");
	 
	    TextField home = new TextField();
	    home.setValue("888-888-8888");
	    p.add(new FieldLabel(home, "Home"), new VerticalLayoutData(1, -1));
	 
	    TextField business = new TextField();
	    business.setValue("888-888-8888");
	    p.add(new FieldLabel(business, "Business"), new VerticalLayoutData(1, -1));
	 
	    vp.add(panel);
	  }

	@Override
	public HasSelectHandlers getIncluirButton() {
		return incluir;
	}

	@Override
	public HasSelectHandlers getVoltarButton() {
		return voltar;
	}

	@Override
	public FramedPanel getPanel() {
		return panel;
	}

	@Override
	public TextField getNumero() {
		return numero;
	}

	@Override
	public TextField getSaldo() {
		return saldo;
	}

	@Override
	public DateField getDataAbertura() {
		return dataAbertura;
	}

	@Override
	public Radio getRadio1() {
		return radio1;
	}

	@Override
	public Radio getRadio2() {
		return radio2;
	}

	@Override
	public ComboBox<Cliente> getComboTitular() {
		return comboTitular;
	}

	@Override
	public Grid<Cliente> getGrid2() {
		return grid2;
	}


}
