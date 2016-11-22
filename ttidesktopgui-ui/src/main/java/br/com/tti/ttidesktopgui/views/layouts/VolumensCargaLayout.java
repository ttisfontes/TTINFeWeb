package br.com.tti.ttidesktopgui.views.layouts;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import br.com.tti.ttidesktop.schema.xml.nfe.Prod;
import br.com.tti.ttidesktop.schema.xml.nfe.Vol;

public class VolumensCargaLayout extends VerticalLayout {

	@PropertyId("qVol")
	private TextField qVolField;

	@PropertyId("esp")
	private TextField espField;

	@PropertyId("marca")
	private TextField marcaField;

	@PropertyId("nVol")
	private TextField nVolField;

	@PropertyId("pesoL")
	private TextField pesoLField;

	@PropertyId("pesoB")
	private TextField pesoBField;

	@PropertyId("lacres")
	private TextField lacresField;

	private Table tableVols;

	public VolumensCargaLayout() {

		this.qVolField = new TextField("qVol");
		this.espField = new TextField("esp");
		this.marcaField = new TextField("marca");
		this.nVolField = new TextField("nVol");
		this.pesoLField = new TextField("pesoL");
		this.pesoBField = new TextField("pesoB");
		this.lacresField = new TextField("lacres");

		

	}

	public void build() {
		{
			GridLayout gridLayout = new GridLayout(4, 4);

			gridLayout.addComponent(this.qVolField);
			gridLayout.addComponent(this.espField);
			gridLayout.addComponent(this.marcaField);
			gridLayout.addComponent(this.nVolField);
			gridLayout.addComponent(this.pesoLField);
			gridLayout.addComponent(this.pesoBField);
			gridLayout.addComponent(this.lacresField);

			this.addComponent(gridLayout);

		}
		{

			this.tableVols = new Table("Volumens");

			BeanItemContainer<Vol> containerBeans = new BeanItemContainer<Vol>(Vol.class);
			Vol vol = new Vol();

			containerBeans.addBean(vol);

			this.tableVols.setContainerDataSource(containerBeans);
			this.tableVols.setSelectable(true);
			this.tableVols.addStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
			this.tableVols.addStyleName(ValoTheme.TABLE_COMPACT);

			// this.prodTable.setColumnHeader(containerBeans.getContainerPropertyIds().iterator().next(),
			// "campo1");

			this.tableVols.setHeight("100%");
			this.tableVols.setWidth("100%");

			this.addComponent(this.tableVols);

		}

	}

}
