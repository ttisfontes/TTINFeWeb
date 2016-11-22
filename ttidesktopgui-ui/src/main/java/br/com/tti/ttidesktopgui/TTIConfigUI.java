package br.com.tti.ttidesktopgui;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.UI;

import br.com.tti.ttidesktopgui.views.SysConfigView;

@Theme("mytheme")
@Widgetset("br.com.tti.ttidesktopgui.MyAppWidgetset")
@Title("TTIDesktop - Configuração")
@Push(transport = Transport.WEBSOCKET)
public class TTIConfigUI extends UI {

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		setContent(new SysConfigView());
	}

}
