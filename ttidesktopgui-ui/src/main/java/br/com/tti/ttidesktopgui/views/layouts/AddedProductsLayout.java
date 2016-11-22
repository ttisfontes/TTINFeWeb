package br.com.tti.ttidesktopgui.views.layouts;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;

import br.com.tti.ttidesktop.core.persistence.NFeStatusInfo;
import br.com.tti.ttidesktop.schema.xml.nfe.Prod;

public class AddedProductsLayout extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	private Table prodTable;

	public AddedProductsLayout() {
		// setSpacing(true);
		// setMargin(true);
	}

	public void build() {
		this.prodTable = new Table("Produtos");

		BeanItemContainer<Prod> containerBeans = new BeanItemContainer<Prod>(Prod.class);
		Prod prod = new Prod();
		prod.setCProd("cProd");
		containerBeans.addBean(prod);

		this.prodTable.setContainerDataSource(containerBeans);
		this.prodTable.setSelectable(true);
		this.prodTable.addStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
		this.prodTable.addStyleName(ValoTheme.TABLE_COMPACT);

		// this.prodTable.setColumnHeader(containerBeans.getContainerPropertyIds().iterator().next(),
		// "campo1");

		this.prodTable.setHeight("100%");
		this.prodTable.setWidth("100%");

		this.addComponent(this.prodTable);

	}
}
