package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface BancoDeletedEventHandler extends EventHandler {
  void onContactDeleted(BancoDeletedEvent event);
}
