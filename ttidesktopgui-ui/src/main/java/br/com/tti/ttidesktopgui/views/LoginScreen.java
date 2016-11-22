package br.com.tti.ttidesktopgui.views;

import java.io.Serializable;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import br.com.tti.ttidesktopgui.views.layouts.LoginLayout;

/**
 * UI content when the user is not logged in yet.
 */
public class LoginScreen extends CssLayout {

	private LoginLayout loginForm;

	public LoginScreen() {
		buildUI();
	}

	private void buildUI() {
		addStyleName("login-screen");

		// login form, centered in the available part of the screen
		loginForm = new LoginLayout(UI.getCurrent());

		// layout to center login form when there is sufficient screen space
		// - see the theme for how this is made responsive for various screen
		// sizes
		VerticalLayout centeringLayout = new VerticalLayout();
		centeringLayout.setStyleName("centering-layout");
		centeringLayout.addComponent(loginForm);
		centeringLayout.setComponentAlignment(loginForm, Alignment.TOP_CENTER);

		// information text about logging in
		CssLayout loginInformation = buildLoginInformation();

		addComponent(centeringLayout);
		addComponent(loginInformation);
	}

	private CssLayout buildLoginInformation() {
		CssLayout loginInformation = new CssLayout();

		loginInformation.setStyleName("login-information");
		Label loginInfoText = new Label(
				"<h1>TTI Soluções</h1>"
						+ "Rua Vale Formoso 123 <br/> Vila Formosa <br/> São Paulo - SP <br/> Fone: (11) 2093-8000 <br/> <a href=\"http://www.ttisolucoes.com.br\" target=\"_blank\">http://www.ttisolcuoes.com.br</a><br><br>",
				ContentMode.HTML);
		loginInformation.addComponent(loginInfoText);

		ThemeResource resource = new ThemeResource("img/novologo.jpg");
		Image image = new Image(null, resource);
		image.addStyleName("mycenter");

		loginInformation.addComponent(image);

		return loginInformation;
	}

	private void login() {
		/*
		 * if (accessControl.signIn(username.getValue(), password.getValue())) {
		 * loginListener.loginSuccessful(); } else { showNotification(new
		 * Notification("Login failed",
		 * "Please check your username and password and try again.",
		 * Notification.Type.HUMANIZED_MESSAGE)); username.focus(); }
		 */
	}

	private void showNotification(Notification notification) {
		// keep the notification visible a little while after moving the
		// mouse, or until clicked
		notification.setDelayMsec(2000);
		notification.show(Page.getCurrent());
	}

	public interface LoginListener extends Serializable {
		void loginSuccessful();
	}
	
	public void focus(){
		this.loginForm.getUsername().focus();
	}
}
