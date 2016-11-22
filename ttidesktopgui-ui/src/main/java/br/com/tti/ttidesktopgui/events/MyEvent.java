package br.com.tti.ttidesktopgui.events;

import com.google.gwt.event.shared.GwtEvent;

public class MyEvent extends GwtEvent<ChangeMenuHandler> {

	public static Type<ChangeMenuHandler> TYPE = new Type<ChangeMenuHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ChangeMenuHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(ChangeMenuHandler handler) {
		handler.onEvent(handler);

	}

}
