package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class BancoDeletedEvent extends GwtEvent<BancoDeletedEventHandler>{
  public static Type<BancoDeletedEventHandler> TYPE = new Type<BancoDeletedEventHandler>();
  
  @Override
  public Type<BancoDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(BancoDeletedEventHandler handler) {
    handler.onContactDeleted(this);
  }
}
