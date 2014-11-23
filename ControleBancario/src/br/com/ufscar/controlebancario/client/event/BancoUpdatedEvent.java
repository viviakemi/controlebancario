package br.com.ufscar.controlebancario.client.event;

import br.com.ufscar.controlebancario.shared.Banco;

import com.google.gwt.event.shared.GwtEvent;

public class BancoUpdatedEvent extends GwtEvent<BancoUpdatedEventHandler>{
  public static Type<BancoUpdatedEventHandler> TYPE = new Type<BancoUpdatedEventHandler>();
  private final Banco updatedBanco;
  
  public BancoUpdatedEvent(Banco updatedBanco) {
    this.updatedBanco = updatedBanco;
  }
  
  public Banco getUpdatedBanco() { return updatedBanco; }
  

  @Override
  public Type<BancoUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(BancoUpdatedEventHandler handler) {
    handler.onContactUpdated(this);
  }
}
