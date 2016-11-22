package br.com.tti.ttidesktopgui.views.layouts;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import br.com.tti.ttidesktop.core.ejb.TTIDesktopClient;
import br.com.tti.ttidesktop.core.util.TTIDesktopListener;
import br.com.tti.ttidesktopgui.listeners.TTIDektopListenerSession;
import br.com.tti.ttidesktopgui.views.MainView;
import br.com.tti.ttidesktopgui.views.util.EJBLocator;
import br.com.tti.ttidesktopgui.widget.test.JsLabel;

@SuppressWarnings("serial")
public class LoginLayout extends VerticalLayout {
	private UI ui;
	private TextField username;

	public LoginLayout(UI ui) {
		this.ui = ui;
		setSizeFull();

		// addComponent(new JsLabel("Hello!!", this));
		Component loginForm = buildLoginForm();
		addComponent(loginForm);
		setComponentAlignment(loginForm, Alignment.TOP_CENTER);

		/*
		 * Notification notification = new Notification(
		 * "Welcome to Dashboard Demo"); notification.setDescription(
		 * "<span>This application is not real, it only demonstrates an application built with the <a href=\"https://vaadin.com\">Vaadin framework</a>.</span> <span>No username or password is required, just click the <b>Sign In</b> button to continue.</span>"
		 * ); notification.setHtmlContentAllowed(true);
		 * notification.setStyleName("tray dark small closable login-help");
		 * notification.setPosition(Position.BOTTOM_CENTER);
		 * notification.setDelayMsec(20000);
		 * notification.show(Page.getCurrent());
		 */
	}

	private Component buildLoginForm() {
		final VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setSizeUndefined();
		loginPanel.setSpacing(true);
		Responsive.makeResponsive(loginPanel);
		loginPanel.addStyleName("login-panel");

		Label label = new Label();
		label.setHeight("100px");
		loginPanel.addComponent(label);

		ThemeResource resource = new ThemeResource("img/novologo.jpg");
		Image image = new Image(null, resource);
		loginPanel.addComponent(image);

		loginPanel.addComponent(buildLabels());
		loginPanel.addComponent(buildFields());
		// loginPanel.addComponent(new CheckBox("Remember me", true));
		return loginPanel;
	}

	private Component buildFields() {
		// HorizontalLayout fields = new HorizontalLayout();
		VerticalLayout fields = new VerticalLayout();
		fields.setSpacing(true);
		fields.addStyleName("fields");

		this.username = new TextField("Usuario");
		username.setIcon(FontAwesome.USER);
		username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

		final PasswordField password = new PasswordField("Senha");
		password.setIcon(FontAwesome.LOCK);
		password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

		final Button signin = new Button("Confirmar");
		signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
		signin.setClickShortcut(KeyCode.ENTER);
		signin.focus();

		fields.addComponents(username, password, signin);
		fields.setComponentAlignment(signin, Alignment.BOTTOM_CENTER);

		signin.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				String userId = username.getValue();
				try {
					TTIDesktopListener listener = new TTIDektopListenerSession(ui);
					TTIDesktopClient client = EJBLocator.getClient(userId);
					client.resgisterListener(listener);
					VaadinSession.getCurrent().getSession().setAttribute("client", client);
				} catch (Exception e) {
					e.printStackTrace();
					Window window = new Window("error logado: " + e.getLocalizedMessage());
					window.setWidth("300px");
					window.center();

				}

				VaadinSession.getCurrent().getSession().setAttribute("user", username.getValue());
				ui.setContent(new MainView(ui));
			}
		});

		username.focus();
		return fields;
	}

	private Component buildLabels() {
		// CssLayout labels = new CssLayout();
		VerticalLayout labels = new VerticalLayout();
		labels.addStyleName("labels");

		Label appName = new Label("TTI Desktop");
		appName.setSizeUndefined();
		appName.addStyleName(ValoTheme.LABEL_H1);
		appName.addStyleName(ValoTheme.LABEL_COLORED);
		labels.addComponent(appName);

		Label title = new Label("Emissão de NF-e");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H4);
		title.addStyleName(ValoTheme.LABEL_LIGHT);

		// labels.addComponent(title);

		// labels.setExpandRatio(appName, 2);
		// labels.setExpandRatio(title, 1);

		return labels;
	}

	public TextField getUsername() {
		return username;
	}

	public void setUsername(TextField username) {
		this.username = username;
	}

}
