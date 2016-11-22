package br.com.tti.ttidesktopgui;

import java.util.logging.Logger;

import com.google.common.eventbus.EventBus;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import br.com.tti.ttidesktopgui.views.LoginScreen;
import br.com.tti.ttidesktopgui.views.MainView;

@Theme("mytheme")
@Widgetset("br.com.tti.ttidesktopgui.MyAppWidgetset")
@Title("TTIDesktop - Emissão de NF-e")
@PreserveOnRefresh
@Push(transport = Transport.WEBSOCKET)
public class TTIDesktopUI extends UI {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(TTIDesktopUI.class.getCanonicalName());

	public EventBus eventBus;

	public TTIDesktopUI() {
		this.eventBus = new EventBus();
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		logger.info("navigator:" + getNavigator() + " session: " + VaadinSession.getCurrent());
		logger.info("url: " + Page.getCurrent().getUriFragment());
		logger.info("ui: " + this);
		logger.info("vaadin session: " + VaadinSession.getCurrent());
		logger.info("session: " + VaadinSession.getCurrent().getSession());
		logger.info("heat beat: " + VaadinSession.getCurrent().getConfiguration().getHeartbeatInterval());
		logger.info("idle: " + VaadinSession.getCurrent().getConfiguration().isCloseIdleSessions());
		logger.info("request params: " + vaadinRequest.getParameterMap());

		super.addDetachListener(new DetachListener() {

			@Override
			public void detach(DetachEvent event) {
				logger.info("%%%%%%%%%%%%% was detached %%%%%%%%%%%%%%");
			}
		});

		Responsive.makeResponsive(this);

		// setContent(new LoginScreen());
		try {
			if (VaadinSession.getCurrent().getSession().getAttribute("client") == null) {
				logger.info("show loginview 2");
				// LoginView loginview = new LoginView(this);
				LoginScreen loginview = new LoginScreen();
				setContent(loginview);
				loginview.focus();
			} else {
				logger.info("show mainview");
				setContent(new MainView(this));

				Notification success = new Notification("Usuario já logado");
				success.setDelayMsec(2000);
				success.setStyleName("bar success small");
				success.setPosition(Position.BOTTOM_CENTER);
				success.show(Page.getCurrent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public EventBus getEventBus() {
		return eventBus;
	}

}
