package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ContaDeletedEventHandler extends EventHandler {
  void onContaDeleted(ContaDeletedEvent event);
}
