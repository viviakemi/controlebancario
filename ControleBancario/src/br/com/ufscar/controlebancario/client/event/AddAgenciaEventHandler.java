package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddAgenciaEventHandler extends EventHandler {
  void onAddAgencia(AddAgenciaEvent event);
}
