package br.com.qileverage.widgets.cellwidgets;

import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public abstract class QICelulaWidgetSuperiorEInferior<ENTIDADE> extends QIAbstractCell<ENTIDADE>
{

	@Override
	public String montarCelula(ENTIDADE entidade)
	{
		QIPilhaVertical pilhaVerticalWidgets = new QIPilhaVertical();

		pilhaVerticalWidgets.adicionarWidget(getWidgetSuperior(entidade));
		pilhaVerticalWidgets.adicionarWidget(getWidgetInferior(entidade));

		pilhaVerticalWidgets.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popupselecao_listacelulasselecao_estilocelula());

		return DOM.toString(pilhaVerticalWidgets.getElement());
	}

	public abstract Widget getWidgetSuperior(ENTIDADE entidade);

	public abstract Widget getWidgetInferior(ENTIDADE entidade);

}
