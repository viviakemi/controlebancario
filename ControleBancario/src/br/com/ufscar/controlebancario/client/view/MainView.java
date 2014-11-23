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

import br.com.ufscar.controlebancario.client.presenter.MainPresenter;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This class represents the main view of the application and includes all
 * elements that are displayed in the main view.
 * 
 * @author Tina Steiger
 */
public class MainView extends Composite implements MainPresenter.Display{

	/**The main panel of the main view*/
	private VerticalPanel mainPanel = new VerticalPanel();
	
	private Image defaultImage;

	public MainView() {
		
		defaultImage = new Image("resources/images/logo.jpg");
		defaultImage.setHeight("80px");
		defaultImage.setWidth("100px");
		mainPanel.add(defaultImage);
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
	    initWidget(contentTableDecorator);
	    contentTableDecorator.setWidth("100%");
	    contentTableDecorator.setWidth("18em");
	    contentTableDecorator.add(mainPanel);
	}

	
	/**Returns the main panel of the main view
	 * 
	 * @return The main panel of the main view
	 * */
	public VerticalPanel getMainPanel() {
		return mainPanel;
	}

	
	
	
}

