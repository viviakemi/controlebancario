package br.com.ufscar.controlebancario.client.view;
import java.util.ArrayList;
import java.util.List;

import br.com.ufscar.controlebancario.shared.Banco;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;
	
public class BasicBancoGridView extends Composite {


  public interface GridProperties extends PropertyAccess<Banco> {
    ModelKeyProvider<Banco> codigo();
    ValueProvider<Banco, String> nome();
    ValueProvider<Banco, String> cnpj();
  }
	  

  public static GridProperties gridProperties = GWT.create(GridProperties.class);
  
  private Grid<Banco> grid;
  
  private GridRowEditing<Banco> gridEditing;
  
  private ListStore<Banco> listStore;
  
	
  public BasicBancoGridView() {

    listStore = new ListStore<Banco>(gridProperties.codigo());
	

    IdentityValueProvider<Banco> identity = new IdentityValueProvider<Banco>();
    CheckBoxSelectionModel<Banco> chk = new CheckBoxSelectionModel<Banco>(identity);
    ColumnConfig<Banco, String> nameCol = new ColumnConfig<Banco, String>(gridProperties.nome(), 50, "Nome");
    ColumnConfig<Banco, String> cnpjCol = new ColumnConfig<Banco, String>(gridProperties.cnpj(), 14, "CNPJ");
    List<ColumnConfig<Banco, ?>> columns = new ArrayList<ColumnConfig<Banco, ?>>();
    nameCol.setWidth(200);
    cnpjCol.setWidth(200);
    columns.add(chk.getColumn());
    columns.add(nameCol);
    columns.add(cnpjCol);
    ColumnModel<Banco> columnModel = new ColumnModel<Banco>(columns);
	

    GridView<Banco> gridView = new GridView<Banco>();
    gridView.setAutoExpandColumn(nameCol);
	    

    grid = new Grid<Banco>(listStore, columnModel, gridView);
    grid.setSelectionModel(chk);
    gridEditing = createGridEditing(grid);
    gridEditing.addEditor(nameCol, new TextField());
    gridEditing.addEditor(cnpjCol, new TextField());
 
    grid.setPixelSize(500, 400);
    
     
    initWidget(grid);
  }

  public GridRowEditing<Banco> getGridEditing(){
	  return gridEditing;
  }
  
  public void setGrid(List<Banco> listBanco){
	  for (int i = 0; i < listStore.size(); i++){
		  listStore.remove(i);
	  }
	  
	  listStore.addAll(listBanco);
  }
  
  public Grid<Banco> getGrid(){
	  return grid;
  }
  
  private GridRowEditing<Banco> createGridEditing(Grid<Banco> editableGrid) {
	  GridRowEditing<Banco> rowEditing = new GridRowEditing<Banco>(editableGrid);
	  return rowEditing;
  }
}