br_com_tti_ttidesktopgui_widget_test_JsLabel = function() {
	var e = this.getElement();

	this.onStateChange = function() {
		e.innerHTML = this.getState().xhtml;
		alert(Object.keys(this.getState().parent));
	}
}