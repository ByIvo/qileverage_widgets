package br.com.qileverage.widgets.crud.botoes;

import br.com.qileverage.widgets.botoes.QIBotaoPadrao;
import br.com.qileverage.widgets.botoes.layouts.QILayoutImagemTextoHorizontal.DIRECAO_LAYOUT;
import br.com.qileverage.widgets.resources.Resources;

public class QIBotaoCancelar extends QIBotaoPadrao
{

	public QIBotaoCancelar()
	{
		super(DIRECAO_LAYOUT.LTR);
		this.setImagemBotao(Resources.INSTANCE.imgCancelar());
		this.setTextoDescricao(Resources.CONSTANTES.widget_crud_campoadicao_botaocancelar());

		this.aplicarLayout();
	}
}
