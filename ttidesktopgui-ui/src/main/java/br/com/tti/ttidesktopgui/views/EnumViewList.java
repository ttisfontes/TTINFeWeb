package br.com.tti.ttidesktopgui.views;

import java.util.Hashtable;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum EnumViewList {
	STATUS_NFE("Status NF-e", "status", NFeStatusView.class, FontAwesome.FILES_O, false),

	// emissao nfe
	EMIT_NFE("Emitir NF-e", "", EmptyView.class, FontAwesome.AMAZON, false),

	INFO_NFE("Informações", "infonfe", CreateNFeView.class, FontAwesome.BAR_CHART_O, false),

	PROD_NFE("Produtos", "produtosnfe", ProductsView.class, FontAwesome.BAR_CHART_O, false),

	TRANSP_NFE("Transportadora", "transpnfe", TranspView.class, FontAwesome.BAR_CHART_O, false),

	// cadastro
	REG("Cadastro", "", EmptyView.class, FontAwesome.AMAZON, false),

	REG_DEST("Cadastro Empresa", "regdestnfe", RegisterDestView.class, FontAwesome.ANDROID, false),

	REG_PROD("Cadastro Produto", "regprodnfe", RegisterProdView.class, FontAwesome.ANDROID, false),

	// operacoes
	//CANCEL_NFE("Cancelamento NF-e", "cancelnfe", TranspView.class, FontAwesome.AMAZON, false),

	//CCE_NFE("Carta de Correção", "ccenfe", TranspView.class, FontAwesome.AMAZON, false),

	// sys config
	REG_USER("Cadastro Usuarios", "regusernfe", UserManagementView.class, FontAwesome.USER, false),

	EMPTY("", "", EmptyView.class, FontAwesome.BAR_CHART_O, false);

	private final String viewName;
	private final String fragment;
	private final Class<? extends View> viewClass;
	private final Resource icon;
	private final boolean stateful;

	private EnumViewList(final String viewName, final String fragment, final Class<? extends View> viewClass,
			final Resource icon, final boolean stateful) {
		this.viewName = viewName;
		this.fragment = fragment;
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

	public String getFragment() {
		return fragment;
	}

	public Class<? extends View> getViewClass() {
		return viewClass;
	}

	public Resource getIcon() {
		return icon;
	}

	public static EnumViewList getByViewName(final String viewName) {
		EnumViewList result = null;
		for (EnumViewList viewType : values()) {
			if (viewType.getViewName().equals(viewName)) {
				result = viewType;
				break;
			}
		}
		return result;
	}

	public static Hashtable<EnumViewList, EnumViewList[]> getStructure() {

		Hashtable<EnumViewList, EnumViewList[]> structure = new Hashtable<>();
		structure.put(EnumViewList.EMIT_NFE,
				new EnumViewList[] { EnumViewList.INFO_NFE, EnumViewList.PROD_NFE, EnumViewList.TRANSP_NFE });

		structure.put(EnumViewList.REG, new EnumViewList[] { EnumViewList.REG_DEST, EnumViewList.REG_PROD });
		return structure;
	}

}
