package br.com.tti.ttidesktopgui.listeners;

import java.util.logging.Logger;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

import br.com.tti.ttidesktop.core.events.TTIEvent;
import br.com.tti.ttidesktop.core.util.TTIDesktopListener;
import br.com.tti.ttidesktopgui.session.SessionManager;

public class TTIDektopListenerSession implements TTIDesktopListener {

	private Logger logger = Logger.getLogger(TTIDektopListenerSession.class.getCanonicalName());

	private UI ui;

	public TTIDektopListenerSession(UI ui) {
		super();
		this.ui = ui;
	}

	@Override
	public void process(TTIEvent event) throws Exception {
		logger.info("listener event: " + event);
		ui.access(() -> {
			ui.showNotification("An unchecked exception occured!", "arquivo apagdo", Notification.TYPE_ERROR_MESSAGE);

		});
	}

	@Override
	public void killSession(String message) {
		Notification n = new Notification(message, Type.ERROR_MESSAGE);
		n.show(ui.getPage());
		ui.getSession().getSession().invalidate();

	}
}
