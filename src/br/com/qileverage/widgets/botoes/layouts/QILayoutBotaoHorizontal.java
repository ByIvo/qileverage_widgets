package br.com.qileverage.widgets.botoes.layouts;

import br.com.qileverage.widgets.botoes.QILayoutBotaoAbstrato;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public abstract class QILayoutBotaoHorizontal extends QIPilhaHorizontal implements QILayoutBotaoAbstrato
{

	public QILayoutBotaoHorizontal()
	{
	}

	@Override
	public SafeHtml getHtmlLayoutButton()
	{
		SafeHtmlBuilder builder = new SafeHtmlBuilder();

		builder.appendHtmlConstant(this.getElement().getInnerHTML());

		return builder.toSafeHtml();
	}

}
