package br.com.tti.ttidesktopgui.views.layouts;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class InfoAdicLayout extends VerticalLayout {

	private TextArea infAdic;
	private TextArea infFisco;

	public InfoAdicLayout() {
		this.infAdic = new TextArea("Informações Adicionais");
		this.infFisco = new TextArea("Informações Fiscais");
	}

	public void build() {
		GridLayout gridLayout = new GridLayout(1, 5);
		gridLayout.setSizeFull();

		this.infAdic.setWidth("100%");
		this.infAdic.setHeight("200px");

		this.infFisco.setWidth("100%");
		this.infFisco.setHeight("200px");

		gridLayout.addComponent(this.infAdic);
		gridLayout.addComponent(this.infFisco);

		this.addComponent(gridLayout);
	}

}
