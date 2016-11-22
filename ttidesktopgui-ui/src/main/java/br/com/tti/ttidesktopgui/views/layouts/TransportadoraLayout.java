package br.com.tti.ttidesktopgui.views.layouts;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class TransportadoraLayout extends VerticalLayout {
	@PropertyId("cnpj")
	private TextField cnpjField;

	@PropertyId("cpf")
	private TextField cpfField;

	@PropertyId("xNome")
	private TextField xNomeField;

	@PropertyId("ie")
	private TextField ieField;

	@PropertyId("xEnder")
	private TextField xEnderField;

	@PropertyId("xMun")
	private TextField xMunField;

	@PropertyId("uf")
	private TextField ufField;

	public TransportadoraLayout() {

		this.cnpjField = new TextField("cnpj");
		this.cpfField = new TextField("cpf");
		this.xNomeField = new TextField("xNome");
		this.ieField = new TextField("ie");
		this.xEnderField = new TextField("xEnder");
		this.xMunField = new TextField("xMun");
		this.ufField = new TextField("uf");
	}

	public void build() {

		GridLayout gridLayout = new GridLayout(3, 4);

		gridLayout.addComponent(this.cnpjField);
		gridLayout.addComponent(this.cpfField);
		gridLayout.addComponent(this.xNomeField);
		gridLayout.addComponent(this.ieField);
		gridLayout.addComponent(this.xEnderField);
		gridLayout.addComponent(this.xMunField);
		gridLayout.addComponent(this.ufField);

		this.addComponent(gridLayout);

	}

}
