package br.com.tti.ttidesktopgui.views;

import java.util.Collection;
import java.util.Hashtable;

import com.google.common.eventbus.Subscribe;
import com.google.gwt.event.shared.EventHandler;
import com.google.web.bindery.event.shared.Event.Type;
import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Tree;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.themes.ValoTheme;

import br.com.tti.ttidesktopgui.TTIDesktopUI;
import br.com.tti.ttidesktopgui.events.ChangeMenuHandler;
import br.com.tti.ttidesktopgui.events.MyEvent;
import br.com.tti.ttidesktopgui.session.SessionManager;
import br.com.tti.ttidesktopgui.windows.SelectInvoiceTypeWindow;

/**
 * A responsive menu component providing user information and the controls for
 * primary navigation between the views.
 */
@SuppressWarnings({ "serial", "unchecked" })
public final class DesktopMenu extends CustomComponent {

	public static final String ID = "dashboard-menu";
	public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
	public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
	private static final String STYLE_VISIBLE = "valo-menu-visible";
	private Label notificationsBadge;
	private Label reportsBadge;
	private MenuItem settingsItem;

	public DesktopMenu() {
		setPrimaryStyleName("valo-menu");
		setId(ID);
		// setSizeUndefined();

		// There's only one DashboardMenu per UI so this doesn't need to be
		// unregistered from the UI-scoped DashboardEventBus.
		// DashboardEventBus.register(this);

		setCompositionRoot(buildContent());

		((TTIDesktopUI) UI.getCurrent()).eventBus.register(this);

	}

	@Subscribe
	public void updateMessage(MyEvent event) {
		System.out.println("event: " + event);
	}

	private Component buildContent() {
		// final CssLayout menuContent = new CssLayout();
		// final HorizontalLayout menuContent = new HorizontalLayout();

		// final GridLayout menuContent = new GridLayout(1, 10);
		final VerticalLayout menuContent = new VerticalLayout();
		menuContent.addStyleName(ValoTheme.MENU_PART);
		/*
		 * menuContent.setId("jjjjjj"); menuContent.addStyleName("sidebar");
		 * 
		 * menuContent.addStyleName("no-vertical-drag-hints");
		 * menuContent.addStyleName("no-horizontal-drag-hints");
		 * menuContent.setWidth("100%"); menuContent.setHeight("100%");
		 */

		menuContent.addComponent(buildTitle());
		// menuContent.setExpandRatio(title, 1);
		menuContent.addComponent(buildUserMenu());
		// menuContent.addComponent(buildToggleButton());
		// menuContent.addComponent(buildMenuItems());

		menuContent.addComponent(buildMyMenu());

		menuContent.setSizeFull();
		return menuContent;
	}

	private Component buildTitle() {
		Label logo = new Label("TTI Desktop", ContentMode.HTML);
		logo.setSizeUndefined();

		// logo.setWidth("100%");

		HorizontalLayout logoWrapper = new HorizontalLayout(logo);
		// GridLayout logoWrapper = new GridLayout(1, 1);
		// logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
		logoWrapper.addStyleName("valo-menu-title");
		logoWrapper.setSizeFull();

		// logoWrapper.setExpandRatio(logo, 1);

		return logoWrapper;
	}

	private Component buildUserMenu() {
		final MenuBar settings = new MenuBar();
		settings.addStyleName("user-menu");

		settingsItem = settings.addItem("", new ThemeResource("img/profile-pic-300px.jpg"), null);

		// updateUserName(null);
		settingsItem.addItem("Editar Perdil", new Command() {
			@Override
			public void menuSelected(final MenuItem selectedItem) {

			}
		});
		settingsItem.addItem("Preferencias", new Command() {
			@Override
			public void menuSelected(final MenuItem selectedItem) {
			}
		});
		settingsItem.addSeparator();
		settingsItem.addItem("Sair", new Command() {
			@Override
			public void menuSelected(final MenuItem selectedItem) {
				SessionManager.killSession(UI.getCurrent());
			}
		});

		settingsItem.setText((String) VaadinSession.getCurrent().getSession().getAttribute("user"));
		return settings;
	}

	private Component buildToggleButton() {
		Button valoMenuToggleButton = new Button("Menu", new ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				if (getCompositionRoot().getStyleName().contains(STYLE_VISIBLE)) {
					getCompositionRoot().removeStyleName(STYLE_VISIBLE);
				} else {
					getCompositionRoot().addStyleName(STYLE_VISIBLE);
				}
			}
		});
		valoMenuToggleButton.setIcon(FontAwesome.LIST);
		valoMenuToggleButton.addStyleName("valo-menu-toggle");
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
		return valoMenuToggleButton;
	}

	private Component buildMyMenu() {
		Tree menuTree = new Tree();
		// menu.addStyleName("valo-menu-item");
		menuTree.addContainerProperty("fasdfas", CssLayout.class, "fasdf");

		menuTree.setSizeFull();

		menuTree.addContainerProperty("viewname", String.class, null);
		menuTree.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		menuTree.setItemCaptionPropertyId("viewname");

		HierarchicalContainer container = new HierarchicalContainer();
		container.addContainerProperty("viewname", String.class, "");
		for (final EnumViewList view : EnumViewList.values()) {
			if (view.equals(EnumViewList.EMPTY)) {
				continue;
			}

			container.addItem(view);

			Item item = container.getItem(view);
			if (item != null) {
				item.getItemProperty("viewname").setValue(view.getViewName());
			}
			menuTree.setItemIcon(view, view.getIcon());

		}

		Hashtable<EnumViewList, EnumViewList[]> structure = EnumViewList.getStructure();
		for (final EnumViewList parent : EnumViewList.values()) {
			EnumViewList[] childs = structure.get(parent);
			if (childs != null) {
				for (EnumViewList child : childs) {
					// menuTree.setParent(container.getItem(child),
					// container.getItem(parent));
					System.out.println("child: " + child);
					container.setParent(child, parent);
				}

			} else {
				container.setChildrenAllowed(parent, false);
			}

		}

		menuTree.setContainerDataSource(container);

		menuTree.addItemClickListener(new ItemClickListener() {

			@Override
			public void itemClick(ItemClickEvent event) {
				/*
				 * for (Object itemId : menuTree.getItemIds()) {
				 * menuTree.collapseItem(itemId); }
				 */

				if (event.getItemId() instanceof EnumViewList) {
					EnumViewList view = (EnumViewList) event.getItemId();

					if (EnumViewList.EMIT_NFE.equals(view)) {
						menuTree.expandItem(view);

						SelectInvoiceTypeWindow window = new SelectInvoiceTypeWindow();
						window.build();
						window.setModal(true);

						UI.getCurrent().addWindow(window);
					} else {
						UI.getCurrent().getNavigator().navigateTo(view.getFragment());
					}

				}
			}
		});

		// menuTree.expandItem(EnumViewList.EMIT_NFE);

		/*
		 * // Expand all items that can be for (Object itemId :
		 * menuTree.getItemIds()) { System.out.println("itemId: " + itemId +
		 * " class: " + itemId.getClass());
		 * menuTree.expandItem(EnumViewList.EMIT_NFE); }
		 */

		/*
		 * menu.addStyleName("valo-menuitems"); // Couple of childless root
		 * items Item item = menu.addItem("Mercury"); System.out.println(item);
		 * 
		 * menu.setChildrenAllowed("Mercury", false); menu.addItem("Venus");
		 * menu.setChildrenAllowed("Venus", false);
		 * 
		 * // An item with hierarchy menu.addItem("Earth"); menu.addItem(
		 * "The Moon"); menu.setChildrenAllowed("The Moon", false);
		 * menu.setParent("The Moon", "Earth"); // menu.expandItem("Earth");
		 */
		return menuTree;
	}

	private Component buildMenuItems() {
		CssLayout menuItemsLayout = new CssLayout();
		menuItemsLayout.addStyleName("valo-menuitems");

		for (final EnumViewList view : EnumViewList.values()) {
			if (EnumViewList.EMPTY.equals(view)) {
				continue;
			}
			Component menuItemComponent = new ValoMenuItemButton(view);
			CssLayout menuItemComponent2 = (CssLayout) buildBadgeWrapper(menuItemComponent, new Label());
			menuItemsLayout.addComponent(menuItemComponent2);

		}
		return menuItemsLayout;

	}

	private Component buildBadgeWrapper(final Component menuItemButton, final Component badgeLabel) {
		CssLayout dashboardWrapper = new CssLayout(menuItemButton);
		dashboardWrapper.addStyleName("badgewrapper");
		dashboardWrapper.addStyleName(ValoTheme.MENU_ITEM);
		badgeLabel.addStyleName(ValoTheme.MENU_BADGE);
		badgeLabel.setWidthUndefined();
		badgeLabel.setVisible(false);
		dashboardWrapper.addComponent(badgeLabel);
		return dashboardWrapper;
	}

	@Override
	public void attach() {
		super.attach();
		// updateNotificationsCount(null);
	}

	public final class ValoMenuItemButton extends Button {

		private static final String STYLE_SELECTED = "selected";

		private final EnumViewList view;

		public ValoMenuItemButton(final EnumViewList view) {
			this.view = view;
			setPrimaryStyleName("valo-menu-item");
			setIcon(view.getIcon());
			setCaption(view.getViewName());
			// DashboardEventBus.register(this);
			addClickListener(new ClickListener() {
				@Override
				public void buttonClick(final ClickEvent event) {
					UI.getCurrent().getNavigator().navigateTo(view.getFragment());
				}
			});

		}

		/*	*/
	}
}
