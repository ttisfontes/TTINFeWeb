package br.com.tti.ttidesktopgui.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.navigator.Navigator.ClassBasedViewProvider;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

import br.com.tti.ttidesktopgui.navigation.TTIDesktopNavigator;

/*
 * Dashboard MainView is a simple HorizontalLayout that wraps the menu on the
 * left and creates a simple container for the navigator on the right.
 */
@SuppressWarnings("serial")
public class MainView extends HorizontalLayout implements View {

	public MainView() {

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	public MainView(UI ui) {
		setSizeFull();
		// addStyleName("mainview");

		DesktopMenu menu = new DesktopMenu();
		addComponent(menu);

		menu.setWidth("100%");
		ComponentContainer content = new CssLayout();
		content.addStyleName("view-content");
		content.setSizeFull();
		addComponent(content);

		setExpandRatio(menu, 1);
		setExpandRatio(content, 4);

		System.out.println("creating navigator...");

		new TTIDesktopNavigator(ui, content);
		System.out.println("navigator created");

		UI.getCurrent().getNavigator().navigateTo(EnumViewList.EMPTY.getFragment());

	}

}
