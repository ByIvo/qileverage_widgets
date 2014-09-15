package br.com.qileverage.widgets.mensagens;

import br.com.qileverage.widgets.listapersonalizada.QIUnorderedList;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class QIMensagemConfirmacao extends QIMensagem
{
	private QIUnorderedList painelListaBotoes;

	private Button btnSim;
	private Button btnNao;

	private final QIConfirmacao confirmacao;

	public QIMensagemConfirmacao(QIConfirmacao confirmacao)
	{
		this.confirmacao = confirmacao;

		montarEstrutura();
	}

	public void mudarTextoBotaoSim(String strTexto)
	{
		btnSim.setText(strTexto);
	}

	public void mudarTextoBotaoNao(String strTexto)
	{
		btnNao.setText(strTexto);
	}

	@Override
	public void instanciarItens()
	{
		super.instanciarItens();

		btnNao = new Button();
		btnSim = new Button();

		painelListaBotoes = new QIUnorderedList();
	}

	@Override
	public void setarInformacoes()
	{
		super.setarInformacoes();

		painelListaBotoes = new QIUnorderedList();

		btnNao.setText("Não");
		btnSim.setText("Sim");

		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensagemconfirmacao());
		painelListaBotoes.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensagemconfirmacao_painelbotoes());
		btnNao.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensagemconfirmacao_painelbotoes_botaonao());
		btnSim.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensagemconfirmacao_painelbotoes_botaosim());

		this.setIcone(Resources.INSTANCE.imgPergunta());

		btnNao.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				QIMensagemConfirmacao.this.confirmacao.nao();
				QIMensagemConfirmacao.this.hide();
			}
		});

		btnSim.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				QIMensagemConfirmacao.this.confirmacao.sim();
				QIMensagemConfirmacao.this.hide();
			}
		});
	}

	@Override
	public void montarTela()
	{
		super.montarTela();

		painelListaBotoes.add(btnSim, Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensagemconfirmacao_painelbotoes_celulabotaosim());
		painelListaBotoes.add(btnNao, Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensagemconfirmacao_painelbotoes_celulabotaonao());

		this.adicionarBotoes(painelListaBotoes);
	}
	
	
	@Override
	public void show()
	{
		super.show();
		btnSim.setFocus(true);
	}

}
