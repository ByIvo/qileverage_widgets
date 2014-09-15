package br.com.qileverage.widgets.crud.botoes;

import br.com.qileverage.widgets.resources.Resources;

public class QIBotaoAlterar extends QIBotaoAdicionarAlterar
{

	public QIBotaoAlterar()
	{
		this.setTextoDescricao(Resources.CONSTANTES.widget_crud_campoadicao_botaoalterar());

		this.aplicarLayout();
	}
}
