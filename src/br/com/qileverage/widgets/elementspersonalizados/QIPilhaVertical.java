package br.com.qileverage.widgets.elementspersonalizados;

import br.com.qileverage.widgets.funcoes.CControleFuncoesClient;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class QIPilhaVertical extends ComplexPanel
{

	public QIPilhaVertical()
	{
		setElement(DOM.createDiv());

		CControleFuncoesClient.sinkClickEvents(this);
	}

	public void adicionarWidget(Widget w)
	{
		super.add(w, getElement());
	}

}
