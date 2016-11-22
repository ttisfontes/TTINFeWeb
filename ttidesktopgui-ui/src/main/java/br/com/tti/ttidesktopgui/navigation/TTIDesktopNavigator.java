package br.com.tti.ttidesktopgui.navigation;

import java.util.Hashtable;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.navigator.Navigator.ClassBasedViewProvider;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

import br.com.tti.ttidesktopgui.views.CreateNFeView;
import br.com.tti.ttidesktopgui.views.EmptyView;
import br.com.tti.ttidesktopgui.views.EnumViewList;

public class TTIDesktopNavigator {
	private Navigator navigator;

	private Hashtable<Class, View> instances = new Hashtable<>();

	public TTIDesktopNavigator(UI ui, ComponentContainer content) {
		this.navigator = new Navigator(ui, content);

		EnumViewList[] enumViews = EnumViewList.values();
		for (EnumViewList viewType : enumViews) {

			View instance = this.instances.get(viewType.getViewClass());

			if (instance == null) {
				try {
					instance = viewType.getViewClass().newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.instances.put(viewType.getViewClass(), instance);
			this.navigator.addView(viewType.getFragment(), instance);
		}

		ui.setNavigator(this.navigator);
	}

	public void navigateTo(String navigationState) {
		this.navigator.navigateTo(navigationState);
	}
}
