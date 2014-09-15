package br.com.qileverage.widgets.popup;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIHrWidget;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class QIPopup extends DialogBox implements QITela
{
	private Button btnOk;
	private Button btnCancelar;

	private Label lblTituloPopup;
	private QIHrWidget qiLinhaHorizontal;

	private SimplePanel spConteudo;

	private QIPilhaVertical qiEstruraVertical;
	private QIPilhaHorizontal qiEstruturaHorizontalBotoes;

	public QIPopup()
	{
		montarEstrutura();
	}

	public void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	@Override
	public void instanciarItens()
	{
		btnCancelar = new Button();
		btnOk = new Button();

		lblTituloPopup = new Label();
		qiLinhaHorizontal = new QIHrWidget();
		spConteudo = new SimplePanel();

		qiEstruraVertical = new QIPilhaVertical();
		qiEstruturaHorizontalBotoes = new QIPilhaHorizontal();
	}

	@Override
	public void setarInformacoes()
	{
		this.setModal(true);

		qiEstruraVertical.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popup_pilhavertical());
		lblTituloPopup.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popup_labeltitulo());
		qiLinhaHorizontal.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popup_linhahorizontal());
		spConteudo.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popup_conteudo());
		btnOk.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popup_botaoOk());
		btnCancelar.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popup_botaoCancelar());
		qiEstruturaHorizontalBotoes.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popup_pilhahorizontalbotoes());

		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popup());

		this.setarNomeBotaoCancelar(Resources.CONSTANTES.widget_qipopup_botaocancelar());
		this.setarNomeBotaoOK(Resources.CONSTANTES.widget_qipopup_botaook());

		btnCancelar.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				QIPopup.this.eventoBotaoCancelar();
			}
		});

		btnOk.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				QIPopup.this.eventoBotaoOk();
			}
		});
	}

	@Override
	public void montarTela()
	{
		qiEstruturaHorizontalBotoes.adicionarWidget(btnOk);
		qiEstruturaHorizontalBotoes.adicionarWidget(btnCancelar);

		qiEstruraVertical.adicionarWidget(lblTituloPopup);
		qiEstruraVertical.adicionarWidget(qiLinhaHorizontal);
		qiEstruraVertical.adicionarWidget(spConteudo);
		qiEstruraVertical.adicionarWidget(qiEstruturaHorizontalBotoes);

		this.add(qiEstruraVertical);
	}

	public void setarNomeBotaoOK(String nomeBotao)
	{
		btnOk.setText(nomeBotao);
	}

	public void setarNomeBotaoCancelar(String nomeBotao)
	{
		btnCancelar.setText(nomeBotao);
	}

	public void ocultarBotaoCancelar(boolean ocultar)
	{
		btnCancelar.setVisible(ocultar);
	}

	public void ocultarBotaoOk(boolean ocultar)
	{
		btnOk.setVisible(ocultar);
	}

	public void setarTituloPopup(String titulo)
	{
		lblTituloPopup.setText(titulo);
	}

	public void setConteudo(Widget w)
	{
		spConteudo.clear();
		spConteudo.add(w);
	}

	@Override
	public void setWidth(String width)
	{
		qiEstruraVertical.setWidth(width);
	}

	@Override
	protected void onLoad()
	{
		super.onLoad();
		this.center();
	}

	public abstract void eventoBotaoOk();

	public abstract void eventoBotaoCancelar();
}
