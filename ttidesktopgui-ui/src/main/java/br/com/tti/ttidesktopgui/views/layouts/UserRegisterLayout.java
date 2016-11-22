package br.com.tti.ttidesktopgui.views.layouts;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.DragAndDropWrapper.DragStartMode;
import com.vaadin.ui.DragAndDropWrapper.WrapperTransferable;
import com.vaadin.ui.Table.TableDragMode;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import br.com.tti.ttidesktop.core.persistence.Usuario;

public class UserRegisterLayout extends FormLayout {

	@PropertyId("id")
	private TextField loginField;

	@PropertyId("nome")
	private TextField nameField;

	@PropertyId("senha")
	private PasswordField passwordField;

	@PropertyId("cnpj")
	private ListSelect cnpjList;

	private BeanFieldGroup<Usuario> fieldGroup;

	private Usuario usuario;

	public UserRegisterLayout() {
	}

	public void build() {
		this.loginField = new TextField("Login");
		this.loginField.setNullRepresentation("");

		this.nameField = new TextField("Nome");
		this.nameField.setNullRepresentation("");

		this.passwordField = new PasswordField("Senha");
		this.passwordField.setNullRepresentation("");

		// BeanItemContainer<String> d = new
		// BeanItemContainer<String>(String.class, new ArrayList<String>());
		this.cnpjList = new ListSelect("CNPJs");
		this.cnpjList.setHeight("100px");
		this.cnpjList.setWidth("200px");

		DragAndDropWrapper ww1 = new DragAndDropWrapper(this.loginField);
		// ww1.setDragStartMode(TableDragMode.NONE);
		ww1.setSizeUndefined();

		/*
		 * DragAndDropWrapper ww2 = new DragAndDropWrapper(this.cnpjList);
		 * ww2.setDropHandler(new DropHandler() {
		 * 
		 * @Override public AcceptCriterion getAcceptCriterion() { // TODO
		 * Auto-generated method stub return AcceptAll.get(); }
		 * 
		 * @Override public void drop(DragAndDropEvent event) {
		 * System.out.println(event); } });
		 */
		// this.fieldGroup.bind(this.cnpjList, "cnpj");

		addComponent(ww1);
		addComponent(this.nameField);
		addComponent(this.passwordField);
		addComponent(this.cnpjList);

		Button btnOK = new Button();
		addComponent(btnOK);
		btnOK.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					fieldGroup.commit();
				} catch (CommitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(usuario.getId());
			}
		});
	}

	public void fill(Usuario usuario) {
		this.usuario = usuario;
		this.fieldGroup = new BeanFieldGroup<Usuario>(Usuario.class);
		this.fieldGroup.bindMemberFields(this);
		this.fieldGroup.setItemDataSource(this.usuario);

	}
}
