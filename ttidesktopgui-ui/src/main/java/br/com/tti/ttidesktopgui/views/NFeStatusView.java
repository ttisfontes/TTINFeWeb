package br.com.tti.ttidesktopgui.views;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.naming.InitialContext;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Container.Filterable;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Table.TableDragMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import br.com.tti.ttidesktop.core.ejb.TTIDesktopClient;
import br.com.tti.ttidesktop.core.persistence.NFeId;
import br.com.tti.ttidesktop.core.persistence.NFeStatusInfo;
import br.com.tti.ttidesktop.core.persistence.Usuario;
import br.com.tti.ttidesktop.core.persistence.NFeStatusInfo.NFE_STATUS;

@SuppressWarnings({ "serial", "unchecked" })
public final class NFeStatusView extends VerticalLayout implements View {

	private final Table table;
	private Button createReport;
	private static final DateFormat DATEFORMAT = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
	private static final DecimalFormat DECIMALFORMAT = new DecimalFormat("#.##");
	private static final String[] DEFAULT_COLLAPSIBLE = { "country", "city", "theater", "room", "title", "seats" };

	public NFeStatusView() {
		setSizeFull();
		addStyleName("transactions");

		addComponent(buildToolbar());

		table = buildTable();

		Panel panel = new Panel();
		panel.setContent(table);
		// panel.sets
		addComponent(panel);
		setExpandRatio(panel, 1);
	}

	@Override
	public void detach() {
		super.detach();

	}

	private Component buildToolbar() {
		HorizontalLayout header = new HorizontalLayout();
		header.addStyleName("viewheader");
		header.setSpacing(true);
		Responsive.makeResponsive(header);

		Label title = new Label("Status NF-e");
		title.setSizeUndefined();
		header.addComponent(title);

		createReport = buildCreateReport();
		HorizontalLayout tools = new HorizontalLayout(buildFilter(), createReport);
		tools.setSpacing(true);
		tools.addStyleName("toolbar");
		header.addComponent(tools);

		return header;
	}

	private Button buildCreateReport() {
		final Button createReport = new Button("Create Report");
		createReport.setDescription("Create a new report from the selected transactions");
		createReport.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				NFeStatusInfo info = new NFeStatusInfo();
				NFeId nfeId = new NFeId();
				nfeId.setNfeId(UUID.randomUUID().toString());
				nfeId.setUiId("fixe");
				info.setNfeId(nfeId);
				info.setDataEnviada(Calendar.getInstance().getTime());
				info.setStatusNFe(NFE_STATUS.AUTORIZADO);

				TTIDesktopClient client = (TTIDesktopClient) VaadinSession.getCurrent().getSession()
						.getAttribute("client");
				try {
					client.persistEntity(info);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				BeanItemContainer<NFeStatusInfo> beans = new BeanItemContainer<NFeStatusInfo>(NFeStatusInfo.class);
				{
					try {

						beans.addAll(client.findEntities(NFeStatusInfo.class));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				table.setContainerDataSource(beans);
			}
		});
		// createReport.setEnabled(true);
		return createReport;
	}

	private Component buildFilter() {
		final TextField filter = new TextField();
		filter.addTextChangeListener(new TextChangeListener() {
			@Override
			public void textChange(final TextChangeEvent event) {
				Filterable data = (Filterable) table.getContainerDataSource();
				data.removeAllContainerFilters();
				data.addContainerFilter(new Filter() {
					@Override
					public boolean passesFilter(final Object itemId, final Item item) {

						if (event.getText() == null || event.getText().equals("")) {
							return true;
						}

						return filterByProperty("country", item, event.getText())
								|| filterByProperty("city", item, event.getText())
								|| filterByProperty("title", item, event.getText());

					}

					@Override
					public boolean appliesToProperty(final Object propertyId) {
						if (propertyId.equals("country") || propertyId.equals("city") || propertyId.equals("title")) {
							return true;
						}
						return false;
					}
				});
			}
		});

		filter.setInputPrompt("Filter");
		filter.setIcon(FontAwesome.SEARCH);
		filter.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		filter.addShortcutListener(new ShortcutListener("Clear", KeyCode.ESCAPE, null) {
			@Override
			public void handleAction(final Object sender, final Object target) {
				filter.setValue("");
				((Filterable) table.getContainerDataSource()).removeAllContainerFilters();
			}
		});
		return filter;
	}

	private Table buildTable() {
		final Table table = new Table() {
			@Override
			protected String formatPropertyValue(final Object rowId, final Object colId, final Property<?> property) {
				String result = super.formatPropertyValue(rowId, colId, property);

				return result;
			}
		};
		table.addStyleName(ValoTheme.TABLE_BORDERLESS);
		table.addStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
		table.addStyleName(ValoTheme.TABLE_COMPACT);
		table.setSelectable(true);

		table.setColumnCollapsingAllowed(true);
		table.setColumnCollapsible("time", false);
		table.setColumnCollapsible("price", false);

		table.setColumnReorderingAllowed(true);
		BeanItemContainer<NFeStatusInfo> beans = new BeanItemContainer<NFeStatusInfo>(NFeStatusInfo.class);
		{
			try {
				TTIDesktopClient client = (TTIDesktopClient) VaadinSession.getCurrent().getSession()
						.getAttribute("client");
				beans.addAll(client.findEntities(NFeStatusInfo.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		table.setContainerDataSource(beans);

		/*
		 * for (String string : columnss) {
		 * container.addContainerProperty(string, String.class, "none"); }
		 */

		table.setSortContainerPropertyId("time");
		table.setSortAscending(false);

		table.setSizeFull();
		// table.setVisibleColumns("nfeId", "dataEditada");

		table.setFooterVisible(true);
		// table.setColumnFooter("time", "Total");

		// Allow dragging items to the reports menu
		table.setDragMode(TableDragMode.MULTIROW);
		table.setMultiSelect(true);

		table.addActionHandler(new TransactionsActionHandler());

		table.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(final ValueChangeEvent event) {
				if (table.getValue() instanceof Set) {
					Set<Object> val = (Set<Object>) table.getValue();
					// createReport.setEnabled(val.size() > 0);
				}
			}
		});
		table.setImmediate(true);

		return table;
	}

	private boolean defaultColumnsVisible() {
		boolean result = true;
		for (String propertyId : DEFAULT_COLLAPSIBLE) {
			if (table.isColumnCollapsed(propertyId) == Page.getCurrent().getBrowserWindowWidth() < 800) {
				result = false;
			}
		}
		return result;
	}

	/*
	 * @Subscribe public void browserResized(final BrowserResizeEvent event) {
	 * // Some columns are collapsed when browser window width gets small //
	 * enough to make the table fit better. if (defaultColumnsVisible()) { for
	 * (String propertyId : DEFAULT_COLLAPSIBLE) {
	 * table.setColumnCollapsed(propertyId, Page.getCurrent()
	 * .getBrowserWindowWidth() < 800); } } }
	 */

	private boolean filterByProperty(final String prop, final Item item, final String text) {
		if (item == null || item.getItemProperty(prop) == null || item.getItemProperty(prop).getValue() == null) {
			return false;
		}
		String val = item.getItemProperty(prop).getValue().toString().trim().toLowerCase();
		if (val.contains(text.toLowerCase().trim())) {
			return true;
		}
		return false;
	}

	/*
	 * void createNewReportFromSelection() { UI.getCurrent().getNavigator()
	 * .navigateTo(DashboardViewType.REPORTS.getViewName());
	 * DashboardEventBus.post(new TransactionReportEvent(
	 * (Collection<Transaction>) table.getValue())); }
	 */

	@Override
	public void enter(final ViewChangeEvent event) {
	}

	public void createForm() {

	}

	private class TransactionsActionHandler implements Handler {
		private final Action report = new Action("Create Report");

		private final Action discard = new Action("Discard");

		private final Action details = new Action("Movie details");

		@Override
		public void handleAction(final Action action, final Object sender, final Object target) {
			System.out.println("sender: " + sender + " target: " + target);
			if (action == report) {
				if (sender instanceof Table) {
					Table table = (Table) sender;
					Object values = table.getValue();

					if (values instanceof Collection) {
						Window window = new Window();
						window.center();
						window.setModal(true);

						VerticalLayout layout = new VerticalLayout();
						FieldGroup fieldGroup = new BeanFieldGroup<Usuario>(Usuario.class);

						Collection<Usuario> usuarios = (Collection<Usuario>) values;
						fieldGroup.setItemDataSource(new BeanItem<Usuario>(usuarios.iterator().next()));
						for (Object propertyId : fieldGroup.getUnboundPropertyIds()) {
							layout.addComponent(fieldGroup.buildAndBind(propertyId));
						}

						window.setContent(layout);
						UI.getCurrent().addWindow(window);
					}

				}
			} else if (action == discard) {
				Notification.show("Not implemented in this demo");
			} else if (action == details) {
				Item item = ((Table) sender).getItem(target);
				if (item != null) {
					Long movieId = (Long) item.getItemProperty("movieId").getValue();
					/*
					 * MovieDetailsWindow.open(DashboardUI.getDataProvider()
					 * .getMovie(movieId), null, null);
					 */
				}
			}
		}

		@Override
		public Action[] getActions(final Object target, final Object sender) {
			return new Action[] { details, report, discard };
		}
	}

	/*	*/

}
