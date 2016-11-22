package br.com.tti.ttidesktopgui.views.layouts;

import java.util.logging.Logger;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class IdeLayout extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(IdeLayout.class.getCanonicalName());

	@PropertyId("cuf")
	private TextField cufField;

	@PropertyId("cnf")
	private TextField cnfField;

	@PropertyId("natOp")
	private TextField natOpField;

	@PropertyId("indPag")
	private TextField indPagField;

	@PropertyId("mod")
	private TextField modField;

	@PropertyId("serie")
	private TextField serieField;

	@PropertyId("nnf")
	private TextField nnfField;

	@PropertyId("dhEmi")
	private TextField dhEmiField;

	@PropertyId("dhSaiEnt")
	private TextField dhSaiEntField;

	@PropertyId("tpNF")
	private TextField tpNFField;

	@PropertyId("idDest")
	private TextField idDestField;

	@PropertyId("cMunFG")
	private TextField cMunFGField;

	@PropertyId("tpImp")
	private TextField tpImpField;

	@PropertyId("tpEmis")
	private TextField tpEmisField;

	@PropertyId("cdv")
	private TextField cdvField;

	@PropertyId("tpAmb")
	private TextField tpAmbField;

	@PropertyId("finNFe")
	private TextField finNFeField;

	@PropertyId("indFinal")
	private TextField indFinalField;

	@PropertyId("indPres")
	private TextField indPresField;

	@PropertyId("procEmi")
	private TextField procEmiField;

	@PropertyId("verProc")
	private TextField verProcField;

	@PropertyId("dhCont")
	private TextField dhContField;

	@PropertyId("xJust")
	private TextField xJustField;

	@PropertyId("nFref")
	private TextField nFrefField;

	public IdeLayout() {

		this.cufField = new TextField("cuf");
		this.cnfField = new TextField("cnf");
		this.natOpField = new TextField("natOp");
		this.indPagField = new TextField("indPag");
		this.modField = new TextField("mod");
		this.serieField = new TextField("serie");
		this.nnfField = new TextField("nnf");
		this.dhEmiField = new TextField("dhEmi");
		this.dhSaiEntField = new TextField("dhSaiEnt");
		this.tpNFField = new TextField("tpNF");
		this.idDestField = new TextField("idDest");
		this.cMunFGField = new TextField("cMunFG");
		this.tpImpField = new TextField("tpImp");
		this.tpEmisField = new TextField("tpEmis");
		this.cdvField = new TextField("cdv");
		this.tpAmbField = new TextField("tpAmb");
		this.finNFeField = new TextField("finNFe");
		this.indFinalField = new TextField("indFinal");
		this.indPresField = new TextField("indPres");
		this.procEmiField = new TextField("procEmi");
		this.verProcField = new TextField("verProc");
		this.dhContField = new TextField("dhCont");
		this.xJustField = new TextField("xJust");
		this.nFrefField = new TextField("nFref");

	}

	public void buid() {

		GridLayout gridLayout = new GridLayout(3, 5);

		this.natOpField.setSizeFull();

		// gridLayout.addComponent(new Label());
		{
			this.serieField.setColumns(3);

			this.nnfField.setColumns(10);
			this.nnfField.addValidator(new StringLengthValidator("error", 1, 9, false));
			this.nnfField.setNullRepresentation("");
			this.nnfField.setImmediate(true);

			this.modField.setValue("55");
			this.modField.setColumns(4);

		}
		{
			gridLayout.addComponent(this.natOpField, 0, 0, 2, 0);

			gridLayout.addComponent(this.cufField);
			gridLayout.addComponent(this.cnfField);

			gridLayout.addComponent(this.indPagField);
			gridLayout.addComponent(this.modField);
			gridLayout.addComponent(this.serieField);
			gridLayout.addComponent(this.nnfField);
			gridLayout.addComponent(this.dhEmiField);
			gridLayout.addComponent(this.dhSaiEntField);
			gridLayout.addComponent(this.tpNFField);
			gridLayout.addComponent(this.idDestField);
			gridLayout.addComponent(this.cMunFGField);
			gridLayout.addComponent(this.tpImpField);
			gridLayout.addComponent(this.tpEmisField);
			gridLayout.addComponent(this.cdvField);
			gridLayout.addComponent(this.tpAmbField);
			gridLayout.addComponent(this.finNFeField);
			gridLayout.addComponent(this.indFinalField);
			gridLayout.addComponent(this.indPresField);
			gridLayout.addComponent(this.procEmiField);
			gridLayout.addComponent(this.verProcField);
			gridLayout.addComponent(this.dhContField);
			gridLayout.addComponent(this.xJustField);
			gridLayout.addComponent(this.nFrefField);
		}
		{
			gridLayout.setComponentAlignment(this.nnfField, Alignment.MIDDLE_LEFT);

		}
		this.addComponent(gridLayout);

		gridLayout.setSizeFull();

	}

	@Override
	public void enter(ViewChangeEvent event) {
		logger.info("enter view: " + this);
	}

}
