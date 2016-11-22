package br.com.tti.ttidesktopgui.views.layouts;

import java.util.Iterator;
import java.util.logging.Logger;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class TaxesLayout extends VerticalLayout implements View {

	private Logger logger = Logger.getLogger(TaxesLayout.class.getCanonicalName());

	private ComboBox tpTaxesCombo;

	public TaxesLayout() {
		this.tpTaxesCombo = new ComboBox();
	}

	public void build() {
		{
			this.tpTaxesCombo.addItems("Selecione", "ICMS", "ICMSNS");

			this.tpTaxesCombo.setTextInputAllowed(false);
			this.tpTaxesCombo.setNewItemsAllowed(false);
			this.tpTaxesCombo.setNullSelectionAllowed(false);

			this.tpTaxesCombo.select("Selecione");

		}

		{
			this.tpTaxesCombo.addValueChangeListener(new ValueChangeListener() {

				public void valueChange(ValueChangeEvent event) {

					logger.info("event: " + event.getProperty().getValue());

					TaxesLayout.this.removeAllLayouts();

					if (event.getProperty().getValue() != null
							&& "ICMS".equals(event.getProperty().getValue().toString())) {
						{
							ICMSLayout icmslayout = new ICMSLayout();
							icmslayout.build();

							TaxesLayout.this.addComponent(icmslayout);
						}
					} else {
						{

						}
					}
				}
			});
		}

		this.addComponent(this.tpTaxesCombo);
	}

	private void removeAllLayouts() {
		Iterator<Component> iter = TaxesLayout.this.getComponentIterator();
		while (iter.hasNext()) {
			Component component = iter.next();

			if (!component.equals(TaxesLayout.this.tpTaxesCombo)) {
				TaxesLayout.this.removeComponent(component);
			}
		}

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
