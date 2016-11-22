package br.com.tti.ttidesktopgui.windows;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class FindProdWindow extends Window {

	private TextField filter;

	public FindProdWindow() {
		this.filter = new TextField();

		addStyleName("profile-window");

	}

	public void build() {
		this.filter.setInputPrompt("Filtro");
		this.filter.setIcon(FontAwesome.SEARCH);
		this.filter.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

		VerticalLayout findlayout = new VerticalLayout();

		findlayout.addComponent(this.filter);

		setContent(findlayout);

	}

}
