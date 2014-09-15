package br.com.qileverage.widgets.mensagens;

import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.user.client.ui.Label;

public class QIMensagemTextoInstantanea extends QIMensagemInstantanea
{

	private Label lblMensagem;

	public QIMensagemTextoInstantanea()
	{
	}

	@Override
	public void instanciarItens()
	{
		super.instanciarItens();

		lblMensagem = new Label();
	}

	@Override
	public void setarInformacoes()
	{
		super.setarInformacoes();

		lblMensagem.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea_labelmensagem());
	}

	@Override
	public void montarTela()
	{
		super.montarTela();

		this.adicionarWidget(lblMensagem);
	}

	public void setTextoExibido(String newTexto)
	{
		lblMensagem.setText(newTexto);
	}
}
