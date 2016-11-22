package br.com.tti.ttidesktopgui.windows;

import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import br.com.tti.ttidesktop.core.persistence.NFE_TYPE;

public class SelectInvoiceTypeWindow extends Window {

	public SelectInvoiceTypeWindow() {
		addStyleName("profile-window");
		center();

		setModal(true);
		setCloseShortcut(KeyCode.ESCAPE, null);
		setResizable(false);
		setClosable(false);
		setHeight("50%");
		setWidth("50%");

	}
	
	
	
	public void buidlAlternaiver(){
		
	}
	

	public void build() {
		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.setMargin(new MarginInfo(true, false, false, false));
		contentLayout.setMargin(true);
		contentLayout.setSpacing(true);

		TabSheet tabSelect = new TabSheet();

		{
			ComboBox comboBox = new ComboBox("Tipo de Notas");
			comboBox.setNewItemsAllowed(false);

			IndexedContainer container = new IndexedContainer();

			
			// container.
			for (NFE_TYPE type : NFE_TYPE.values()) {
				container.addItem(type);
			}

			comboBox.setContainerDataSource(container);
			tabSelect.addComponent(comboBox);
		}

		contentLayout.addComponent(tabSelect);

		setContent(contentLayout);

	}

}
