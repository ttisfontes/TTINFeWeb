package br.com.tti.ttidesktopgui.servlet;

import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;

import br.com.tti.ttidesktop.core.ejb.TTIDesktopClient;
import br.com.tti.ttidesktopgui.TTIDesktopUI;
import br.com.tti.ttidesktopgui.views.util.EJBLocator;

@WebServlet(urlPatterns = { "/tti/*", "/VAADIN/*" }, name = "TTIDesktopServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = TTIDesktopUI.class, productionMode = true, closeIdleSessions = true, heartbeatInterval = 5) // 3min
public class TTIDesktopAppServlet extends VaadinServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws ServletException, java.io.IOException {
		super.service(request, response);

		Collection<VaadinSession> allsessions = VaadinSession.getAllSessions(request.getSession());
		allsessions.forEach(session -> {
			TTIDesktopClient client = (TTIDesktopClient) request.getSession().getAttribute("client");
			// DEBUG: teste
			/*
			 * System.out.println("vaadin session: " + session +
			 * " request session:" + request.getSession() + " ejb user: " +
			 * (client != null ? client.getUserOwner() : "NAOTEM"));
			 */
			if (client != null) {
				try {
					EJBLocator.getServer().notifyHeartBeat(client);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	};

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();
		getService().addSessionInitListener(new MessageConfigListener());
	}
}