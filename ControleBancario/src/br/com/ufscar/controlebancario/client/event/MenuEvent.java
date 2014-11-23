package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class MenuEvent extends GwtEvent<MenuEventHandler> {
	
	private String menuItem;
	
	public MenuEvent (String menuItem){
		this.menuItem = menuItem;
	}
	
	public String getMenuItem(){
		return menuItem;
	}
	
	public static Type<MenuEventHandler> TYPE = new Type<MenuEventHandler>();

	@Override
	public Type<MenuEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(MenuEventHandler handler) {
		handler.onClick(this);
	}

}
