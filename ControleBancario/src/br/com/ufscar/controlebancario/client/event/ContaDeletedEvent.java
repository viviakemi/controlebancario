package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ContaDeletedEvent extends GwtEvent<ContaDeletedEventHandler>{
  public static Type<ContaDeletedEventHandler> TYPE = new Type<ContaDeletedEventHandler>();
  
  @Override
  public Type<ContaDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ContaDeletedEventHandler handler) {
    handler.onContaDeleted(this);
  }
}
