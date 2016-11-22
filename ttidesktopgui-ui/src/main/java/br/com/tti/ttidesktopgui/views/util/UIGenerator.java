package br.com.tti.ttidesktopgui.views.util;

import java.lang.reflect.Field;

import com.vaadin.data.fieldgroup.PropertyId;

import br.com.tti.ttidesktop.schema.xml.nfe.Dest;
import br.com.tti.ttidesktop.schema.xml.nfe.Ide;
import br.com.tti.ttidesktop.schema.xml.nfe.Prod;
import br.com.tti.ttidesktop.schema.xml.nfe.TVeiculo;
import br.com.tti.ttidesktop.schema.xml.nfe.Transporta;
import br.com.tti.ttidesktop.schema.xml.nfe.Vol;
import br.com.tti.ttidesktop.schema.xml.nfe.Imposto.ICMS.ICMS90;

public class UIGenerator {

	public static void main(String[] args) {
		Class<?> classs = TVeiculo.class;

		Field[] fields = classs.getDeclaredFields();
		for (Field field : fields) {
			System.out.println("@PropertyId(\"" + field.getName() + "\")");
			System.out.println("private TextField " + field.getName() + "Field;\n");
		}

		for (Field field : fields) {
			System.out.println("this." + field.getName() + "Field = new TextField(\"" + field.getName() + "\");");
		}
		
		
		for (Field field : fields) {
			System.out.println("gridLayout.addComponent(this." + field.getName() + "Field);");
		}
	}
}
