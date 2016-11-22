package br.com.tti.ttidesktopgui.views.layouts;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ICMSLayout extends VerticalLayout {

	@PropertyId("orig")
	private TextField origField;

	@PropertyId("cst")
	private TextField cstField;

	@PropertyId("modBC")
	private TextField modBCField;

	@PropertyId("vbc")
	private TextField vbcField;

	@PropertyId("pRedBC")
	private TextField pRedBCField;

	@PropertyId("picms")
	private TextField picmsField;

	@PropertyId("vicms")
	private TextField vicmsField;

	@PropertyId("modBCST")
	private TextField modBCSTField;

	@PropertyId("pmvast")
	private TextField pmvastField;

	@PropertyId("pRedBCST")
	private TextField pRedBCSTField;

	@PropertyId("vbcst")
	private TextField vbcstField;

	@PropertyId("picmsst")
	private TextField picmsstField;

	@PropertyId("vicmsst")
	private TextField vicmsstField;

	@PropertyId("vicmsDeson")
	private TextField vicmsDesonField;

	@PropertyId("motDesICMS")
	private TextField motDesICMSField;

	public ICMSLayout() {

		this.origField = new TextField("orig");
		this.cstField = new TextField("cst");
		this.modBCField = new TextField("modBC");
		this.vbcField = new TextField("vbc");
		this.pRedBCField = new TextField("pRedBC");
		this.picmsField = new TextField("picms");
		this.vicmsField = new TextField("vicms");
		this.modBCSTField = new TextField("modBCST");
		this.pmvastField = new TextField("pmvast");
		this.pRedBCSTField = new TextField("pRedBCST");
		this.vbcstField = new TextField("vbcst");
		this.picmsstField = new TextField("picmsst");
		this.vicmsstField = new TextField("vicmsst");
		this.vicmsDesonField = new TextField("vicmsDeson");
		this.motDesICMSField = new TextField("motDesICMS");
	}

	public void build() {
		GridLayout gridLayout = new GridLayout(4, 10);

		gridLayout.addComponent(this.origField);
		gridLayout.addComponent(this.cstField);
		gridLayout.addComponent(this.modBCField);
		gridLayout.addComponent(this.vbcField);
		gridLayout.addComponent(this.pRedBCField);
		gridLayout.addComponent(this.picmsField);
		gridLayout.addComponent(this.vicmsField);
		gridLayout.addComponent(this.modBCSTField);
		gridLayout.addComponent(this.pmvastField);
		gridLayout.addComponent(this.pRedBCSTField);
		gridLayout.addComponent(this.vbcstField);
		gridLayout.addComponent(this.picmsstField);
		gridLayout.addComponent(this.vicmsstField);
		gridLayout.addComponent(this.vicmsDesonField);
		gridLayout.addComponent(this.motDesICMSField);

		this.addComponent(gridLayout);
	}

}
