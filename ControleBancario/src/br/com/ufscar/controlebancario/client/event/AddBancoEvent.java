package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddBancoEvent extends GwtEvent<AddBancoEventHandler> {
  public static Type<AddBancoEventHandler> TYPE = new Type<AddBancoEventHandler>();
  
  @Override
  public Type<AddBancoEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddBancoEventHandler handler) {
    handler.onAddBanco(this);
  }
}
