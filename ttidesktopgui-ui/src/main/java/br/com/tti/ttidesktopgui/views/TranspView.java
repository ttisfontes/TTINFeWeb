package br.com.tti.ttidesktopgui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import br.com.tti.ttidesktopgui.views.layouts.TransportadoraLayout;
import br.com.tti.ttidesktopgui.views.layouts.VolumensCargaLayout;

public class TranspView extends VerticalLayout implements View {

	public TranspView() {
		this.build();
		setSpacing(true);
		setMargin(true);

	}

	public void build() {

		TabSheet tabTransp = new TabSheet();
		tabTransp.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);

		{
			TransportadoraLayout transplayout = new TransportadoraLayout();
			transplayout.build();
			transplayout.setCaption("Transportadora");

			transplayout.setSizeFull();

			tabTransp.addComponent(transplayout);
		}

		{
			VolumensCargaLayout vollayout = new VolumensCargaLayout();
			vollayout.build();
			vollayout.setCaption("Volumens");

			vollayout.setSizeFull();

			tabTransp.addComponent(vollayout);
		}

		tabTransp.setSizeFull();
		tabTransp.setWidth("100%");
		this.addComponent(tabTransp);

		this.setSizeFull();

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
