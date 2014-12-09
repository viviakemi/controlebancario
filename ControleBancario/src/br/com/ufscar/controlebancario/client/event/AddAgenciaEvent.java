package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddAgenciaEvent extends GwtEvent<AddAgenciaEventHandler> {
  public static Type<AddAgenciaEventHandler> TYPE = new Type<AddAgenciaEventHandler>();
  
  @Override
  public Type<AddAgenciaEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddAgenciaEventHandler handler) {
    handler.onAddAgencia(this);
  }
}
