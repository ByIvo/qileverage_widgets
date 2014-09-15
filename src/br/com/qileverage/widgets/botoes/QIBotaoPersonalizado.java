package br.com.qileverage.widgets.botoes;

import com.google.gwt.user.client.ui.PushButton;

public class QIBotaoPersonalizado extends PushButton
{

	public QIBotaoPersonalizado()
	{
	}

	public void setLayout(QILayoutBotaoAbstrato layoutBotao)
	{
		this.setHTML(layoutBotao.getHtmlLayoutButton());
	}

}
