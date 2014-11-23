package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface BancoUpdatedEventHandler extends EventHandler{
  void onContactUpdated(BancoUpdatedEvent event);
}
