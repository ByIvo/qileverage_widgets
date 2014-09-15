package br.com.qileverage.widgets.elementspersonalizados;

import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class QIPilhaHorizontal extends ComplexPanel
{

	public QIPilhaHorizontal()
	{
		setElement(DOM.createDiv());
		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_pilhahorizontal());
	}

	public void adicionarWidget(Widget w)
	{
		w.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_pilhahorizontal_itemadicionado());
		super.add(w, getElement());
	}

}
