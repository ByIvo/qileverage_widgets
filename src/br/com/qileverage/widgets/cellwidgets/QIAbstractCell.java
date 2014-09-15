package br.com.qileverage.widgets.cellwidgets;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public abstract class QIAbstractCell<ENTIDADE> extends AbstractCell<ENTIDADE>
{
	public void render(Context context, ENTIDADE value, SafeHtmlBuilder sb)
	{
		sb.appendHtmlConstant(montarCelula(value));
	};

	public abstract String montarCelula(ENTIDADE entidade);
}
