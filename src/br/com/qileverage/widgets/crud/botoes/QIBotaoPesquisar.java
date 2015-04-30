package br.com.qileverage.widgets.crud.botoes;

import br.com.qileverage.widgets.botoes.QIBotaoPadrao;
import br.com.qileverage.widgets.botoes.layouts.QILayoutImagemTextoHorizontal.DIRECAO_LAYOUT;
import br.com.qileverage.widgets.resources.Resources;

public class QIBotaoPesquisar extends QIBotaoPadrao
{

	public QIBotaoPesquisar()
	{
		super(DIRECAO_LAYOUT.LTR);
		this.setImagemBotao(Resources.INSTANCE.imgSearch());
		this.setTextoDescricao("");
		
		this.getElement().setAttribute("botao", "pesquisa");
		
		this.aplicarLayout();
	}
}
