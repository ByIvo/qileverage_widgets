package br.com.qileverage.widgets.crud.botoes;

import br.com.qileverage.widgets.resources.Resources;

public class QIBotaoAdicionar extends QIBotaoAdicionarAlterar
{
	public QIBotaoAdicionar()
	{
		this.setTextoDescricao(Resources.CONSTANTES.widget_crud_campoadicao_botaoadicionar());

		this.aplicarLayout();
	}
}
