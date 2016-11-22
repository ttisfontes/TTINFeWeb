package br.com.tti.ttidesktopgui.session;

import com.vaadin.server.Page;
import com.vaadin.ui.UI;

public class SessionManager {

	public static void killSession(UI ui) {

		try {
			Page.getCurrent().setUriFragment("");
		} catch (Exception e) {

		}

		ui.access(() -> {
			try {
				Thread.sleep(500);
				Page.getCurrent().reload();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		ui.getSession().getSession().invalidate();
	}
}
