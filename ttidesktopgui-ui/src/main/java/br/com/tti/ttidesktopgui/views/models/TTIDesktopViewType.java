package br.com.tti.ttidesktopgui.views.models;

import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

import br.com.tti.ttidesktopgui.views.CreateNFeView;
import br.com.tti.ttidesktopgui.views.NFeStatusView;
import br.com.tti.ttidesktopgui.views.NFeStatusView;

public enum TTIDesktopViewType {
	CREATE("emitir", CreateNFeView.class, FontAwesome.AMAZON, false), NFE("status", NFeStatusView.class,
			FontAwesome.BAR_CHART_O, false);

	private final String viewName;
	private final Class<? extends View> viewClass;
	private final Resource icon;
	private final boolean stateful;

	private TTIDesktopViewType(final String viewName, final Class<? extends View> viewClass, final Resource icon,
			final boolean stateful) {
		this.viewName = viewName;
		this.viewClass = viewClass;
		this.icon = icon;
		this.stateful = stateful;
	}

	public boolean isStateful() {
		return stateful;
	}

	public String getViewName() {
		return viewName;
	}

	public Class<? extends View> getViewClass() {
		return viewClass;
	}

	public Resource getIcon() {
		return icon;
	}

	public static TTIDesktopViewType getByViewName(final String viewName) {
		TTIDesktopViewType result = null;
		for (TTIDesktopViewType viewType : values()) {
			if (viewType.getViewName().equals(viewName)) {
				result = viewType;
				break;
			}
		}
		return result;
	}

}
