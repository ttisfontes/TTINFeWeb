package br.com.tti.ttidesktopgui.views.util;

import java.util.logging.Logger;

import javax.naming.InitialContext;

import com.vaadin.server.VaadinSession;

import br.com.tti.ttidesktop.core.ejb.TTIDesktopClient;
import br.com.tti.ttidesktop.core.ejb.TTIDesktopServer;

public class EJBLocator {

	private static Logger logger = Logger.getLogger(EJBLocator.class.getCanonicalName());

	private static TTIDesktopClient createEJBClient() throws Exception {
		InitialContext c = new InitialContext();
		TTIDesktopClient client = (TTIDesktopClient) c
				.lookup("java:app/ttidesktopcore/TTIDesktopClientImpl!br.com.tti.ttidesktop.core.ejb.TTIDesktopClient");
		return client;
	}

	public static TTIDesktopServer getServer() throws Exception {
		InitialContext c = new InitialContext();
		TTIDesktopServer server = (TTIDesktopServer) c.lookup(
				"java:global/ttidesktop/ttidesktopcore/TTIDesktopServerImpl!br.com.tti.ttidesktop.core.ejb.TTIDesktopServer");
		return server;
	}

	public static TTIDesktopClient getClient(String userId) throws Exception {
		// try reutilize ejb
		TTIDesktopClient client = VaadinSession.getCurrent().getAttribute(TTIDesktopClient.class);

		if (client != null) {
			try {
				if (!client.isAlive()) {
					client = createEJBClient();
				}
			} catch (Exception e) {
				logger.info("ejb isnt alive:" + e.getLocalizedMessage());
				client = createEJBClient();
			}
		} else {
			client = createEJBClient();
		}

		client.setUserOwner(userId);

		getServer().registerEJB(client);
		return client;
	}
}
