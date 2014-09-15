package br.com.qileverage.widgets.cellwidgets;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Label;

public abstract class QICelulaTexto<ENTIDADE> extends QIAbstractCell<ENTIDADE>
{

	@Override
	public String montarCelula(ENTIDADE entidade)
	{
		return DOM.toString(getTexto(entidade).getElement());
	}

	public abstract Label getTexto(ENTIDADE entidade);
}
