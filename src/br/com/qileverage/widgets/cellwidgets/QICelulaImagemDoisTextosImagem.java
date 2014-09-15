package br.com.qileverage.widgets.cellwidgets;

import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public abstract class QICelulaImagemDoisTextosImagem<ENTIDADE> extends QIAbstractCell<ENTIDADE>
{

	@Override
	public String montarCelula(ENTIDADE entidade)
	{
		QIPilhaHorizontal pilhaHorizontalCelula = new QIPilhaHorizontal();
		QIPilhaVertical pilhaVerticalLabels = new QIPilhaVertical();

		pilhaVerticalLabels.adicionarWidget(getLabelSuperior(entidade));
		pilhaVerticalLabels.adicionarWidget(getLabelInferior(entidade));

		pilhaHorizontalCelula.adicionarWidget(getImagemApresentacao(entidade));
		pilhaHorizontalCelula.adicionarWidget(pilhaVerticalLabels);
		pilhaHorizontalCelula.adicionarWidget(getImagemLado(entidade));

		pilhaVerticalLabels.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popupselecao_listacelulasselecao_labelsverticais());
		pilhaHorizontalCelula.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popupselecao_listacelulasselecao_estilocelula());

		return DOM.toString(pilhaHorizontalCelula.getElement());
	}

	public abstract Widget getImagemApresentacao(ENTIDADE entidade);

	public abstract Widget getLabelSuperior(ENTIDADE entidade);

	public abstract Widget getLabelInferior(ENTIDADE entidade);

	public abstract Widget getImagemLado(ENTIDADE entidade);
}
