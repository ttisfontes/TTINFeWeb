package br.com.tti.ttidesktopgui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

import br.com.tti.ttidesktop.schema.xml.nfe.Prod;
import br.com.tti.ttidesktopgui.TTIDesktopUI;
import br.com.tti.ttidesktopgui.events.MyEvent;
import br.com.tti.ttidesktopgui.views.layouts.AddedProductsLayout;
import br.com.tti.ttidesktopgui.views.layouts.EditProductLayout;
import br.com.tti.ttidesktopgui.views.layouts.TaxesLayout;
import br.com.tti.ttidesktopgui.windows.FindProdWindow;
import br.com.tti.ttidesktopgui.windows.ProfilePreferencesWindow;

public class ProductsView extends VerticalLayout implements View {

	private VerticalSplitPanel prodPanel;
	private HorizontalLayout toolbar;

	public ProductsView() {
		this.build();
		setSpacing(true);
		setMargin(true);
	}

	public void build() {
		this.toolbar = new HorizontalLayout();
		this.prodPanel = new VerticalSplitPanel();
		// this.prodPanel.setSizeFull();
		{
			MenuBar menubar = new MenuBar();

			MenuItem sendItem = menubar.addItem("Adicionar Produto", FontAwesome.AMBULANCE, new Command() {

				@Override
				public void menuSelected(MenuItem selectedItem) {
					{
						ProfilePreferencesWindow w = new ProfilePreferencesWindow(new Prod(), false);
						w.setModal(true);
						UI.getCurrent().addWindow(w);

					}
					{
						FindProdWindow window = new FindProdWindow();
						window.build();

						window.setModal(true);
						window.center();

						UI.getCurrent().addWindow(window);

					}
					((TTIDesktopUI) UI.getCurrent()).getEventBus().post(new MyEvent());

				}
			});

			this.toolbar.addComponent(menubar);

		}
		{
			TabSheet prodTabSheet = new TabSheet();
			prodTabSheet.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);

			{
				// form edit prod
				EditProductLayout prodEdit = new EditProductLayout();
				prodEdit.build();
				prodEdit.setSpacing(true);
				prodEdit.setMargin(true);
				prodEdit.setCaption("Dados do Produto");

				prodTabSheet.addComponent(prodEdit);

				// form edit taxes
				TaxesLayout taxesEdit = new TaxesLayout();
				taxesEdit.build();
				taxesEdit.setCaption("Impostos do Produto");

				prodTabSheet.addComponent(taxesEdit);
			}

			this.prodPanel.setFirstComponent(prodTabSheet);

		}

		{
			AddedProductsLayout addedPRod = new AddedProductsLayout();
			addedPRod.build();
			addedPRod.setSizeFull();
			this.prodPanel.setSecondComponent(addedPRod);
		}

		{
			this.addComponent(this.toolbar);
			this.addComponent(this.prodPanel);

			this.prodPanel.setSizeFull();

			this.setExpandRatio(this.toolbar, 1);
			this.setExpandRatio(this.prodPanel, 30);
		}
		this.setSizeFull();

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
