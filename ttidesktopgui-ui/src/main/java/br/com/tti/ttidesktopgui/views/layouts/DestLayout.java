package br.com.tti.ttidesktopgui.views.layouts;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class DestLayout extends VerticalLayout {

	@PropertyId("cnpj")
	private TextField cnpjField;

	@PropertyId("cpf")
	private TextField cpfField;

	@PropertyId("idEstrangeiro")
	private TextField idEstrangeiroField;

	@PropertyId("xNome")
	private TextField xNomeField;

	@PropertyId("enderDest")
	private TextField enderDestField;

	@PropertyId("indIEDest")
	private TextField indIEDestField;

	@PropertyId("ie")
	private TextField ieField;

	@PropertyId("isuf")
	private TextField isufField;

	@PropertyId("im")
	private TextField imField;

	@PropertyId("email")
	private TextField emailField;

	public DestLayout() {

		this.cnpjField = new TextField("cnpj");
		this.cpfField = new TextField("cpf");
		this.idEstrangeiroField = new TextField("idEstrangeiro");
		this.xNomeField = new TextField("xNome");
		this.enderDestField = new TextField("enderDest");
		this.indIEDestField = new TextField("indIEDest");
		this.ieField = new TextField("ie");
		this.isufField = new TextField("isuf");
		this.imField = new TextField("im");
		this.emailField = new TextField("email");
	}

	public void build() {

		GridLayout gridLayout = new GridLayout(4, 3);

		gridLayout.addComponent(this.cnpjField);
		gridLayout.addComponent(this.cpfField);
		gridLayout.addComponent(this.idEstrangeiroField);
		gridLayout.addComponent(this.xNomeField);
		gridLayout.addComponent(this.enderDestField);
		gridLayout.addComponent(this.indIEDestField);
		gridLayout.addComponent(this.ieField);
		gridLayout.addComponent(this.isufField);
		gridLayout.addComponent(this.imField);
		gridLayout.addComponent(this.emailField);
		
		this.addComponent(gridLayout);

	}

}
