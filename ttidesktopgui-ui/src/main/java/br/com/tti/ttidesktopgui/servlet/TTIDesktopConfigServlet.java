package br.com.tti.ttidesktopgui.servlet;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import br.com.tti.ttidesktopgui.TTIConfigUI;

@WebServlet(urlPatterns = { "/config/*", "/VAADIN/*" }, name = "TTIDesktopConfigServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = TTIConfigUI.class, productionMode = true)
public class TTIDesktopConfigServlet extends VaadinServlet {

	private static final long serialVersionUID = 1L;

}
