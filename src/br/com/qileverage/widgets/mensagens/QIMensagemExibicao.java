package br.com.qileverage.widgets.mensagens;

import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class QIMensagemExibicao extends QIMensagem
{
	private Button btnOk;
	private final TipoMensagem tipoMensagem;

	public QIMensagemExibicao(TipoMensagem tipoMensagem)
	{
		this.tipoMensagem = tipoMensagem;
		montarEstrutura();
	}

	@Override
	public void instanciarItens()
	{
		super.instanciarItens();

		btnOk = new Button();
	}

	@Override
	public void setarInformacoes()
	{
		super.setarInformacoes();
		btnOk.setText("OK");

		switch (tipoMensagem)
		{
			case ALERTA:
				this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensagemalerta());
				btnOk.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensagemalerta_botaook());
				this.setIcone(Resources.INSTANCE.imgAlerta());
				break;

			case SUCESSO:
				this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensagemsucesso());
				btnOk.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensagemsucesso_botaook());
				this.setIcone(Resources.INSTANCE.imgSucess());

			case ERRO:
				this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensagemerro());
				btnOk.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensagemerro_botaook());
				this.setIcone(Resources.INSTANCE.imgErro());

				break;
		}

		btnOk.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				QIMensagemExibicao.this.hide();
			}
		});
	}

	@Override
	public void montarTela()
	{
		super.montarTela();

		this.adicionarBotoes(btnOk);
	}
	
	@Override
	public void show()
	{
		super.show();
		btnOk.setFocus(true);
	}

	public enum TipoMensagem
	{
		ALERTA, SUCESSO, ERRO;
	}
}
