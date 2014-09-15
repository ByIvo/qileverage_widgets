package br.com.qileverage.widgets.mensagens;

import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.listapersonalizada.QIUnorderedList;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class QiMensagemConfirmacaoInstantanea extends QIMensagemInstantanea
{
	private SimplePanel spWidgetMensagem;

	private QIPilhaVertical pilhaVerticalEstrutura;
	private QIUnorderedList listaPilha;

	private Button btnSim;
	private Button btnNao;

	private QIConfirmacao confirmacao;

	public QiMensagemConfirmacaoInstantanea(QIConfirmacao confirmacao)
	{
		this.confirmacao = confirmacao;
	}

	@Override
	public void adicionarWidget(Widget w)
	{
		spWidgetMensagem.clear();
		spWidgetMensagem.add(w);

		w.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea_labelmensagem());
	}

	@Override
	public void instanciarItens()
	{
		super.instanciarItens();

		spWidgetMensagem = new SimplePanel();

		listaPilha = new QIUnorderedList();
		pilhaVerticalEstrutura = new QIPilhaVertical();

		listaPilha.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea_painelbotoes());
		pilhaVerticalEstrutura.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea_painelestrutura());

		btnNao = new Button();
		btnSim = new Button();
	}

	@Override
	public void setarInformacoes()
	{
		super.setarInformacoes();

		btnNao.setText("Não");
		btnSim.setText("Sim");

		btnNao.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea_botaonao());
		btnSim.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea_botaosim());

		btnNao.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea_botoesconfirmacao());
		btnSim.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea_botoesconfirmacao());

		btnNao.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				QiMensagemConfirmacaoInstantanea.this.confirmacao.nao();
				QiMensagemConfirmacaoInstantanea.this.fecharImediatamente();
			}
		});

		btnSim.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				QiMensagemConfirmacaoInstantanea.this.confirmacao.sim();
				QiMensagemConfirmacaoInstantanea.this.fecharImediatamente();
			}
		});
	}

	@Override
	public void montarTela()
	{
		super.montarTela();

		listaPilha.add(btnSim, Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea_painelbotoes_celulabotaosim());
		listaPilha.add(btnNao, Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea_painelbotoes_celulabotaonao());

		pilhaVerticalEstrutura.adicionarWidget(spWidgetMensagem);
		pilhaVerticalEstrutura.adicionarWidget(listaPilha);

		super.adicionarWidget(pilhaVerticalEstrutura);
	}

}
