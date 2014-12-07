package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditBancoCancelledEvent extends GwtEvent<EditBancoCancelledEventHandler>{
  public static Type<EditBancoCancelledEventHandler> TYPE = new Type<EditBancoCancelledEventHandler>();
  
  @Override
  public Type<EditBancoCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditBancoCancelledEventHandler handler) {
    handler.onEditBancoCancelled(this);
  }
}
