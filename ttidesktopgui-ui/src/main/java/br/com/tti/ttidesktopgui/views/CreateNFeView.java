package br.com.tti.ttidesktopgui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import br.com.tti.ttidesktopgui.session.SessionManager;
import br.com.tti.ttidesktopgui.views.layouts.InfoAdicLayout;
import br.com.tti.ttidesktopgui.views.layouts.DestLayout;
import br.com.tti.ttidesktopgui.views.layouts.IdeLayout;

public class CreateNFeView extends VerticalLayout implements View {

	public CreateNFeView() {
		Label title = new Label();
		title.setCaption("Emitir NF-e");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H4);
		title.addStyleName("title-view");

		// addComponent(title);

		HorizontalLayout toolbar = new HorizontalLayout();

		// toolbar.setSizeFull();
		addComponent(toolbar);

		MenuBar menubar = new MenuBar();
		// menubar.setHeight("100%");
		Command r = new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub

			}
		};
		MenuItem sendItem = menubar.addItem("Enviar", null, null);
		sendItem.setStyleName("space-after");
		sendItem.setIcon(new ThemeResource("img/send.png"));

		// sendItem.addSeparatorBefore(item);

		MenuItem itembar2 = menubar.addItem("Cancelar", null, null);
		itembar2.setIcon(FontAwesome.SEARCH);
		itembar2.setCommand(new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
			}
		});

		toolbar.addComponent(menubar);

		MenuBar menubar2 = new MenuBar();
		MenuItem printItem = menubar2.addItem("Imprimir", null);
		printItem.setIcon(FontAwesome.PRINT);

		MenuItem danfeItem = menubar2.addItem("Visualizar DANFE", null);
		danfeItem.setIcon(FontAwesome.VIDEO_CAMERA);

		Label ll = new Label();
		ll.setWidth("10px");
		toolbar.addComponent(ll);
		toolbar.addComponent(menubar2);

		// menubar.setWidth("300px");
		// toolbar.setExpandRatio(menubar, 1);
		// toolbar.setComponentAlignment(menubar, Alignment.BOTTOM_LEFT);
		// toolbar.setComponentAlignment(menubar2, Alignment.BOTTOM_LEFT);
		//
		setWidth(100.0f, Unit.PERCENTAGE);
		setSpacing(true);
		setMargin(true);
		addStyleName("profile-form");

		TabSheet detailsWrapper = new TabSheet();
		detailsWrapper.setSizeFull();
		// detailsWrapper.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		// detailsWrapper.addStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
		detailsWrapper.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
		addComponent(detailsWrapper);

		// detailsWrapper.addComponent(buildPreferencesTab());

		{
			IdeLayout ideNFe = new IdeLayout();
			ideNFe.buid();
			ideNFe.setSizeFull();
			ideNFe.setCaption("Dados NF-e");

			detailsWrapper.addComponent(ideNFe);

		}

		{
			DestLayout destlayout = new DestLayout();
			destlayout.build();
			destlayout.setCaption("Destinatario");

			detailsWrapper.addComponent(destlayout);

		}

		{
			InfoAdicLayout adiclayout = new InfoAdicLayout();
			adiclayout.build();
			adiclayout.setCaption("Informações Adicionais");

			detailsWrapper.addComponent(adiclayout);

		}
		// addComponent(form);

		// tt.focus();

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
