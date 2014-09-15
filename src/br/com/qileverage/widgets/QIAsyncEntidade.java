package br.com.qileverage.widgets;

import br.com.qileverage.shared.QIRetornoRPC;
import br.com.qileverage.widgets.mensagens.QIMensagemExibicao.TipoMensagem;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class QIAsyncEntidade<T> implements AsyncCallback<QIRetornoRPC<T>>
{
	private boolean ocultarBloqueio;

	public QIAsyncEntidade()
	{
		ocultarBloqueio = true;
	}

	public QIAsyncEntidade(boolean ocultarBloqueio)
	{
		super();
		this.ocultarBloqueio = ocultarBloqueio;
	}

	@Override
	public void onFailure(Throwable caught)
	{
		if (ocultarBloqueio)
		{
			QIControlePopupBloqueio.mostrarBloqueio(false);
		}

		QIControleMensagens.mostrarMensagem("Ocorreu um erro de comunicação com o servidor!", TipoMensagem.ERRO);
		caught.printStackTrace();
	}

	@Override
	public void onSuccess(QIRetornoRPC<T> result)
	{
		if (ocultarBloqueio)
		{
			QIControlePopupBloqueio.mostrarBloqueio(false);
		}
		
		if (!QIUteisClient.ocorreuErro(result) && result.getObjetoRetorno() != null)
		{
			sucessoComunicacao(result);
		}
	}

	public abstract void sucessoComunicacao(QIRetornoRPC<T> result);

}
