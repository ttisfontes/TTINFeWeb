package br.com.tti.ttidesktopgui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class EmptyView extends HorizontalLayout implements View {
	public EmptyView() {
		Label label = new Label();
		label.setValue("TELA DE BEM-VINDA");
		label.setStyleName(ValoTheme.LABEL_H1);

		this.addComponent(label);

		setMargin(true);
		setSpacing(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}
}
