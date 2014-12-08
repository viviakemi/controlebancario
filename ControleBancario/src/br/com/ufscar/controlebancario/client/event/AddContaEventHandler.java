package br.com.ufscar.controlebancario.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddContaEventHandler extends EventHandler {
  void onAddConta(AddContaEvent event);
}
