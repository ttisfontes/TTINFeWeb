package br.com.tti.ttidesktopgui.servlet;

import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;
import com.vaadin.server.VaadinService;

public class MessageConfigListener implements SessionInitListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		VaadinService.getCurrent().setSystemMessagesProvider(new SystemMessagesProvider() {

			@Override
			public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
				CustomizedSystemMessages messages = new CustomizedSystemMessages();
				messages.setInternalErrorCaption("internal error");

				messages.setSessionExpiredCaption("Sessão Terminada");
				messages.setSessionExpiredMessage("Por favor loge novamente. " + messages.getSessionExpiredMessage());

				messages.setCommunicationErrorCaption("communication error");

				messages.setCookiesDisabledCaption("cookies disabled");

				messages.setAuthenticationErrorCaption("authentication error");
				return messages;
			}
		});
	}
}