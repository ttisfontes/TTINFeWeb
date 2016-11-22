package br.com.tti.ttidesktopgui.views;

import java.util.Collection;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import br.com.tti.ttidesktop.core.persistence.Usuario;
import br.com.tti.ttidesktop.schema.xml.nfe.Emit;
import br.com.tti.ttidesktopgui.views.components.designer.MyDesign;
import br.com.tti.ttidesktopgui.views.layouts.UserRegisterLayout;

public class SysConfigView extends HorizontalLayout implements View {

	private static final long serialVersionUID = 1L;

	public SysConfigView() {
		setSizeFull();

		TabSheet tab = new TabSheet();

		UserRegisterLayout userLayout = new UserRegisterLayout();
		userLayout.build();

		Usuario usuario = new Usuario();
		usuario.setId("teste");
		usuario.setCnpj(new String[] { "a", "b", "c" });
		userLayout.fill(usuario);

		tab.addTab(userLayout, "Novo Usuario", FontAwesome.USER_SECRET);
		tab.addTab(createEmit(), "Novo Emitente", FontAwesome.CAB);

		MyDesign dd = new MyDesign();
		tab.addTab(dd, "TTeste", null);

		addComponent(tab);
	}

	public Component createUserTab() {
		VerticalLayout userLayout = new VerticalLayout();
		userLayout.setSizeFull();
		FormLayout formLayout = new FormLayout();
		BeanFieldGroup fieldGroup = new BeanFieldGroup<Usuario>(Usuario.class);
		fieldGroup.setFieldFactory(new DefaultFieldGroupFieldFactory() {
			@Override
			protected <T extends AbstractTextField> T createAbstractTextField(Class<T> fieldType) {
				System.out.println("fieldType: " + fieldType);
				T field = super.createAbstractTextField(fieldType);
				field.setNullRepresentation("");
				System.out.println("field: " + field);

				return field;
			}

			@Override
			public <T extends Field> T createField(Class<?> type, Class<T> fieldType) {
				System.out.println("fielType: " + fieldType + " type: " + type);
				T classsField = super.createField(type, fieldType);
				System.out.println("classField: " + classsField);
				if (type.isArray()) {
					classsField = (T) new ComboBox();
				}
				return classsField;
			}
		});

		Usuario user = new Usuario();
		user.setId("id");
		// user.setCnpj(new String[] { "2", "3" });
		fieldGroup.setItemDataSource(new BeanItem<Usuario>(user));
		Collection<Object> properties = fieldGroup.getUnboundPropertyIds();

		// setup order
		java.lang.reflect.Field[] classFields = Usuario.class.getDeclaredFields();
		for (java.lang.reflect.Field field : classFields) {
			System.out.println(field.getName());
			properties.forEach(propertyId -> {
				if (propertyId.toString().equals(field.getName())
				// && field.getType().equals(String.class)
				) {
					// fieldGroup.bind(field, propertyId);
					formLayout.addComponent(fieldGroup.buildAndBind(propertyId));
					return;
				}
			});
		}

		/*
		 * for (Object propertyId : properties) {
		 * System.out.println(propertyId); }
		 */

		Button btnConfirm = new Button("Confirmar");
		btnConfirm.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					fieldGroup.commit();
					BeanItem<Usuario> data = (BeanItem<Usuario>) fieldGroup.getItemDataSource();
					System.out.println(data.getBean().getId());
					System.out.println(data.getBean().getCnpj()[0]);
				} catch (CommitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		fieldGroup.getField("id").setCaption("Login");
		fieldGroup.getField("nome").setWidth("300px");
		fieldGroup.getField("senha");

		userLayout.addComponent(formLayout);
		userLayout.addComponent(btnConfirm);

		return userLayout;
	}

	public Component createEmit() {
		VerticalLayout emitLayout = new VerticalLayout();
		emitLayout.setSizeFull();
		FormLayout formLayout = new FormLayout();
		BeanFieldGroup fieldGroup = new BeanFieldGroup<Emit>(Emit.class);
		fieldGroup.setFieldFactory(new DefaultFieldGroupFieldFactory() {
			@Override
			protected <T extends AbstractTextField> T createAbstractTextField(Class<T> fieldType) {
				T field = super.createAbstractTextField(fieldType);
				field.setNullRepresentation("");
				return field;
			}
		});

		fieldGroup.setItemDataSource(new BeanItem<Emit>(new Emit()));
		Collection<Object> properties = fieldGroup.getUnboundPropertyIds();

		for (Object propertyId : properties) {
			System.out.println(propertyId);
			formLayout.addComponent(fieldGroup.buildAndBind(propertyId));
		}

		Button btnConfirm = new Button("Confirmar");
		btnConfirm.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					fieldGroup.commit();
					BeanItem<Emit> data = (BeanItem<Emit>) fieldGroup.getItemDataSource();
					System.out.println(data.getBean().getCNPJ());
				} catch (CommitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		emitLayout.addComponent(formLayout);
		emitLayout.addComponent(btnConfirm);

		return emitLayout;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
