package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditBancoEvent extends GwtEvent<EditBancoEventHandler>{
  public static Type<EditBancoEventHandler> TYPE = new Type<EditBancoEventHandler>();
  private final String id;
  
  public EditBancoEvent(String id) {
    this.id = id;
  }
  
  public String getId() { return id; }
  
  @Override
  public Type<EditBancoEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditBancoEventHandler handler) {
    handler.onEditContact(this);
  }
}
