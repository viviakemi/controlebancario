package br.com.ufscar.controlebancario.client.view;

import br.com.ufscar.controlebancario.client.event.MenuEvent;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class AdvancedToolBar implements IsWidget{

	
	
	class SamplePanel extends ContentPanel{
		private ToolBar toolBar = new ToolBar();
		private VerticalLayoutContainer con = new VerticalLayoutContainer();
		
		public SamplePanel(){
			setPixelSize(500, 250);
			toolBar.setSpacing(2);
			con.add(toolBar, new VerticalLayoutData(1, -1));
			add(con);
		}
		
		public ToolBar getToolBar(){
			return toolBar;
		}
	}
	
	private FlowLayoutContainer con;
	
	@Override
	public Widget asWidget() {
		if (con == null){
			con = new FlowLayoutContainer();
			con.add(createStandard());
		}
		return con;
	}
	
	private ContentPanel createStandard(){
		SamplePanel panel = new SamplePanel();
		TextButton btn = new TextButton("Banco", ExampleImages.INSTANCE.add16());
		Menu menu = new Menu();
		menu.add(new MenuItem("teste"));
		btn.setMenu(menu);
		panel.getToolBar().add(btn);
		
		return panel;
	}

}
