package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddBancoEventHandler extends EventHandler {
  void onAddBanco(AddBancoEvent event);
}
