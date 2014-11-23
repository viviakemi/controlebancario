package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditBancoEventHandler extends EventHandler {
  void onEditContact(EditBancoEvent event);
}
