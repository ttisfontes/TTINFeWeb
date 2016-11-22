package br.com.tti.ttidesktopgui.widget.test;

import com.vaadin.ui.Component;

@SuppressWarnings("serial")
@com.vaadin.annotations.JavaScript({ "br_com_tti_ttidesktopgui_widgets_JsLabel.js" })
public class JsLabel extends com.vaadin.ui.AbstractJavaScriptComponent {

	public JsLabel(final String xhtml, Component parent) {
		getState().xhtml = xhtml;
		getState().parent = parent;
	}

	@Override
	protected JsLabelState getState() {
		return (JsLabelState) super.getState();
	}
}
