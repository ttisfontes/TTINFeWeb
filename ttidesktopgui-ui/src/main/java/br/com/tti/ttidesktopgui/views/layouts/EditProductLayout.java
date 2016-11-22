package br.com.tti.ttidesktopgui.views.layouts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

import br.com.tti.ttidesktop.schema.xml.nfe.Emit;
import br.com.tti.ttidesktop.schema.xml.nfe.Prod;
import br.com.tti.ttidesktopgui.windows.ProfilePreferencesWindow;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class EditProductLayout extends VerticalLayout {

	@PropertyId("cProd")
	private TextField cProdField;

	@PropertyId("cean")
	private TextField ceanField;

	@PropertyId("xProd")
	private TextField xProdField;

	@PropertyId("ncm")
	private TextField ncmField;

	@PropertyId("nve")
	private TextField nveField;

	@PropertyId("cest")
	private TextField cestField;

	@PropertyId("extipi")
	private TextField extipiField;

	@PropertyId("cfop")
	private TextField cfopField;

	@PropertyId("uCom")
	private TextField uComField;

	@PropertyId("qCom")
	private TextField qComField;

	@PropertyId("vUnCom")
	private TextField vUnComField;

	@PropertyId("vProd")
	private TextField vProdField;

	@PropertyId("ceanTrib")
	private TextField ceanTribField;

	@PropertyId("uTrib")
	private TextField uTribField;

	@PropertyId("qTrib")
	private TextField qTribField;

	@PropertyId("vUnTrib")
	private TextField vUnTribField;

	@PropertyId("vFrete")
	private TextField vFreteField;

	@PropertyId("vSeg")
	private TextField vSegField;

	@PropertyId("vDesc")
	private TextField vDescField;

	@PropertyId("vOutro")
	private TextField vOutroField;

	@PropertyId("indTot")
	private TextField indTotField;

	@PropertyId("di")
	private TextField diField;

	@PropertyId("detExport")
	private TextField detExportField;

	@PropertyId("xPed")
	private TextField xPedField;

	@PropertyId("nItemPed")
	private TextField nItemPedField;

	@PropertyId("nfci")
	private TextField nfciField;

	@PropertyId("veicProd")
	private TextField veicProdField;

	@PropertyId("med")
	private TextField medField;

	@PropertyId("arma")
	private TextField armaField;

	@PropertyId("comb")
	private TextField combField;

	@PropertyId("nrecopi")
	private TextField nrecopiField;

	public EditProductLayout() {

		this.cProdField = new TextField("cProd");
		this.ceanField = new TextField("cean");
		this.xProdField = new TextField("xProd");
		this.ncmField = new TextField("ncm");
		this.nveField = new TextField("nve");
		this.cestField = new TextField("cest");
		this.extipiField = new TextField("extipi");
		this.cfopField = new TextField("cfop");
		this.uComField = new TextField("uCom");
		this.qComField = new TextField("qCom");
		this.vUnComField = new TextField("vUnCom");
		this.vProdField = new TextField("vProd");
		this.ceanTribField = new TextField("ceanTrib");
		this.uTribField = new TextField("uTrib");
		this.qTribField = new TextField("qTrib");
		this.vUnTribField = new TextField("vUnTrib");
		this.vFreteField = new TextField("vFrete");
		this.vSegField = new TextField("vSeg");
		this.vDescField = new TextField("vDesc");
		this.vOutroField = new TextField("vOutro");
		this.indTotField = new TextField("indTot");
		this.diField = new TextField("di");
		this.detExportField = new TextField("detExport");
		this.xPedField = new TextField("xPed");
		this.nItemPedField = new TextField("nItemPed");
		this.nfciField = new TextField("nfci");
		this.veicProdField = new TextField("veicProd");
		this.medField = new TextField("med");
		this.armaField = new TextField("arma");
		this.combField = new TextField("comb");
		this.nrecopiField = new TextField("nrecopi");

		// setWidth(100.0f, Unit.PERCENTAGE);
		// setSpacing(true);
		// setMargin(true);
		// addStyleName("profile-form");
	}

	public void build() {

		{

			GridLayout prodGrid = new GridLayout(5, 5);
			prodGrid.addComponent(this.cProdField);

			{
				BeanFieldGroup<Prod> fieldGroup = new BeanFieldGroup<Prod>(Prod.class);
				fieldGroup.setItemDataSource(new BeanItem<Prod>(new Prod()));
				fieldGroup.setFieldFactory(new DefaultFieldGroupFieldFactory() {
					@Override
					protected <T extends AbstractTextField> T createAbstractTextField(Class<T> fieldType) {
						T field = super.createAbstractTextField(fieldType);
						field.setConverter(new Converter<String, ArrayList>() {

							@Override
							public ArrayList<?> convertToModel(String value, Class<? extends ArrayList> targetType,
									Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {
								// System.out.println("convertToModel: " +
								// value);
								return new ArrayList<>();
							}

							@Override
							public String convertToPresentation(ArrayList value, Class<? extends String> targetType,
									Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {
								// System.out.println("convertToPresentation: "
								// + value);
								return value.toString();
							}

							@Override
							public Class<ArrayList> getModelType() {
								// TODO Auto-generated method stub
								return ArrayList.class;
							}

							@Override
							public Class<String> getPresentationType() {
								// TODO Auto-generated method stub
								return String.class;
							}

						});
						return field;
					}
				});
				Collection<Object> properties = fieldGroup.getUnboundPropertyIds();

				for (Object propertyId : properties) {
					System.out.println(propertyId);
					prodGrid.addComponent(fieldGroup.buildAndBind(propertyId));
				}
				this.addComponent(prodGrid);
			}

		}
	}

}
