package br.com.qileverage.widgets.menusliderlateral;

import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;

public class QIMenuLinkPainelAcesso extends Label
{
	public QIMenuLinkPainelAcesso(String nome, ClickHandler clickHandler)
	{
		super(nome);
		this.setTitle(nome);

		this.addClickHandler(clickHandler);

		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().menu_acessos_painelacessos_labelacesso());
	}

}
