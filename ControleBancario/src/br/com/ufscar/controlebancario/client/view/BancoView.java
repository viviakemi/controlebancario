// Copyright 2014 Tina Steiger

// This file is part of GWT_AppNavigation.
// GWT_AppNavigation is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// GWT_AppNavigation is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with GWT_AppNavigation.  If not, see <http://www.gnu.org/licenses/>.

package br.com.ufscar.controlebancario.client.view;

import java.util.List;

import br.com.ufscar.controlebancario.client.presenter.BancoPresenter;
import br.com.ufscar.controlebancario.shared.Banco;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;


public class BancoView extends Composite implements BancoPresenter.Display {
	
    
    private ContentPanel panel;
	
    private TextButton incluirButton;
    private TextButton salvarButton;
	private TextButton voltarButton;
	private Button listarButton;
	private BasicBancoGridView grid;
	private TextButton removeButton;
	ToolBar toolBar;
	
	public BancoView(){
		
		incluirButton = new TextButton("Incluir Banco");
		voltarButton = new TextButton("Voltar");
		salvarButton = new TextButton("Salvar");
		grid = new BasicBancoGridView();


		panel = new ContentPanel();
		panel.setHeadingText("Cadastro de Banco");
		panel.getHeader().setIcon(ExampleImages.INSTANCE.table());
		panel.setPixelSize(600, 320);
		panel.addStyleName("margin-10");
		
		toolBar = new ToolBar();
		//contentPanel.add(bancoGrid);
		panel.add(grid);

		panel.setButtonAlign(BoxLayoutPack.END);
		removeButton = new TextButton("Remover as linhas selecionada(s)");
		removeButton.setEnabled(false);

		panel.addButton(removeButton);
		panel.addButton(salvarButton);
		panel.addButton(voltarButton);
		toolBar.add(incluirButton);
		panel.addTool(toolBar);
		
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
	    contentTableDecorator.setWidth("100%");
	    contentTableDecorator.setWidth("18em");
	    contentTableDecorator.add(panel);
	}


	@Override
	public HasSelectHandlers getAddButton() {
		return incluirButton;
	}

	@Override
	public HasSelectHandlers getDeleteButton() {
		return removeButton;
	}

	@Override
	public HasClickHandlers getList() {
		return listarButton;
	}

	@Override
	public void setData(List<Banco> data) {
		grid.setGrid(data);	
	}


	public Widget asWidget() {
		return this;
	}

	@Override
	public Grid<Banco> getGrid() {
		return this.grid.getGrid();
	}

	@Override
	public void setEnabledRemoveButton(boolean enabled) {
		removeButton.setEnabled(enabled);
	}


	@Override
	public GridRowEditing<Banco> getGridEditing() {
		return this.grid.getGridEditing();
	}


	@Override
	public HasSelectHandlers getVoltarButton() {
		return this.voltarButton;
	}


	@Override
	public HasSelectHandlers getSalvarButton() {
		return this.salvarButton;
	}
}
