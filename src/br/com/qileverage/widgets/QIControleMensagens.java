package br.com.qileverage.widgets;

import br.com.qileverage.widgets.mensagens.QIConfirmacao;
import br.com.qileverage.widgets.mensagens.QIMensagemConfirmacao;
import br.com.qileverage.widgets.mensagens.QIMensagemExibicao;
import br.com.qileverage.widgets.mensagens.QIMensagemExibicao.TipoMensagem;
import br.com.qileverage.widgets.mensagens.QIMensagemTextoInstantanea;
import br.com.qileverage.widgets.mensagens.QiMensagemConfirmacaoInstantanea;

import com.google.gwt.user.client.ui.Label;

public class QIControleMensagens
{

	public static void mostrarMensagem(String mensagem, TipoMensagem tipoMensagem)
	{
		QIMensagemExibicao msgExibicao = new QIMensagemExibicao(tipoMensagem);

		msgExibicao.setMensagem(mensagem);
		msgExibicao.show();
	}

	public static void mostrarMensagemConfirmacao(String mensagem, QIConfirmacao confirmacao)
	{
		QIMensagemConfirmacao msgConfirmacao = new QIMensagemConfirmacao(confirmacao);

		msgConfirmacao.setMensagem(mensagem);
		msgConfirmacao.show();
	}

	public static void mostrarMensagemInstantanea(String mensagem, QIConfirmacao confirmacao)
	{
		QiMensagemConfirmacaoInstantanea msg = new QiMensagemConfirmacaoInstantanea(confirmacao);

		msg.adicionarWidget(new Label(mensagem));
	}

	public static void mostrarMensagemInstantanea(String mensagem)
	{
		QIMensagemTextoInstantanea msg = new QIMensagemTextoInstantanea();
		msg.setTextoExibido(mensagem);
	}

}
