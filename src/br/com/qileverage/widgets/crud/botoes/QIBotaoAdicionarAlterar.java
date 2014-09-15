package br.com.qileverage.widgets.crud.botoes;

import br.com.qileverage.widgets.botoes.QIBotaoPadrao;
import br.com.qileverage.widgets.botoes.layouts.QILayoutImagemTextoHorizontal.DIRECAO_LAYOUT;
import br.com.qileverage.widgets.resources.Resources;

public class QIBotaoAdicionarAlterar extends QIBotaoPadrao
{

	protected QIBotaoAdicionarAlterar()
	{
		super(DIRECAO_LAYOUT.RTL);
		this.setImagemBotao(Resources.INSTANCE.imgSalvar());
	}
}
