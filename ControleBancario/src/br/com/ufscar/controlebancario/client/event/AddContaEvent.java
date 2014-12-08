package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddContaEvent extends GwtEvent<AddContaEventHandler> {
  public static Type<AddContaEventHandler> TYPE = new Type<AddContaEventHandler>();
  
  @Override
  public Type<AddContaEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddContaEventHandler handler) {
    handler.onAddConta(this);
  }
}
