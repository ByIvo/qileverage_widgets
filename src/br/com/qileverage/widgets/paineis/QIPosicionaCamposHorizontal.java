package br.com.qileverage.widgets.paineis;

import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;

import com.google.gwt.user.client.ui.Widget;

public abstract class QIPosicionaCamposHorizontal extends QIPosicionaCampos
{
	private QIPilhaHorizontal qiPilhaHorizontal;

	public QIPosicionaCamposHorizontal()
	{
	}

	@Override
	protected void adicionarProximoCampo(Widget label, Widget w)
	{
		qiPilhaHorizontal.adicionarWidget(label);
		qiPilhaHorizontal.adicionarWidget(w);
	}

	@Override
	protected void setElementMaster()
	{
		this.setElement(qiPilhaHorizontal.getElement());
	}

	@Override
	public void instanciarItens()
	{
		super.instanciarItens();
		qiPilhaHorizontal = new QIPilhaHorizontal();

	}

	@Override
	public void montarTela()
	{
		super.montarTela();

		setElementMaster();
	}

}
