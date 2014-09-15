package br.com.qileverage.widgets.popup;

import java.util.List;

import br.com.qileverage.widgets.cellwidgets.QIAbstractCell;
import br.com.qileverage.widgets.cellwidgets.QICellList;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.funcoes.CControleFuncoesClient;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public abstract class QIPopupPesquisa<ENTIDADE> extends QIPopup
{

	private QIPilhaHorizontal qiPilhaHorizontal;
	private QIPilhaVertical qiPilhaVerticalEstrutura;

	private Image imgBuscar;
	private TextBox txbFiltro;
	private Label lblTituloFiltro;

	private boolean bPermitirSelecaoNulla;

	private QICellList<ENTIDADE> cellList;
	private QIAbstractCell<ENTIDADE> celulaCellList;

	private boolean exibiuPrimeiraVez;

	public QIPopupPesquisa(QIAbstractCell<ENTIDADE> celulaCellList)
	{
		exibiuPrimeiraVez = false;
		this.celulaCellList = celulaCellList;

		montarEstruturaSeguranca();
		this.hide();
	}

	// METODOS

	private void montarEstruturaSeguranca()
	{
		instanciarItensSeguranca();
		setarInformacoesSeguranca();
		montarTelaSeguranca();
	}

	public void verificarSelecaoUsuario()
	{
		ENTIDADE objetoSelecionado = cellList.getEntidadeSelecionada();

		if (objetoSelecionado == null && !bPermitirSelecaoNulla)
		{
			acaoErroSelecaoNulla();
			return;
		}

		if (!validarSelecaoUsuario(objetoSelecionado))
		{
			acaoErroSelecaoInvalida();
			return;
		}

		selecionarNovaEntidade(objetoSelecionado);
		this.hide();
	}

	private void filtrarResultados()
	{
		String strFiltro = txbFiltro.getText().trim();

		realizarPesquisaEntidades(strFiltro);
	}

	public void setLabelFiltro(String novoTexto)
	{
		lblTituloFiltro.setText(CControleFuncoesClient.getNomeCampoComSeparador(novoTexto));
	}

	public void setPermitirSelecaoNulla(boolean permitirSelecaoNulla)
	{
		this.bPermitirSelecaoNulla = permitirSelecaoNulla;
	}

	public void setListaEntidade(List<ENTIDADE> listaEntidade)
	{
		cellList.setRowData(listaEntidade);
	}

	// METODOS SOBRECARREGADOS

	public void instanciarItensSeguranca()
	{

		qiPilhaVerticalEstrutura = new QIPilhaVertical();
		qiPilhaHorizontal = new QIPilhaHorizontal();

		lblTituloFiltro = new Label();
		txbFiltro = new TextBox();
		imgBuscar = new Image(Resources.INSTANCE.imgSearch());

		bPermitirSelecaoNulla = false;

		cellList = new QICellList<ENTIDADE>(celulaCellList);
	}

	public void setarInformacoesSeguranca()
	{

		setLabelFiltro(Resources.CONSTANTES.widget_qipopuppesquisa_labelfiltro());
		imgBuscar.setTitle(Resources.CONSTANTES.widget_qipopuppesquisa_imagempesquisa_title());
		txbFiltro.setTitle(Resources.CONSTANTES.widget_qipopuppesquisa_inputfiltro_title());

		qiPilhaVerticalEstrutura.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popupselecao());
		lblTituloFiltro.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popupselecao_pilhahorizotalcampos_labelfiltro());
		txbFiltro.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popupselecao_pilhahorizotalcampos_textboxfiltro());
		imgBuscar.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popupselecao_pilhahorizotalcampos_imagembusca());
		qiPilhaHorizontal.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popupselecao_pilhahorizotalcampos());
		cellList.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popupselecao_listacelulasselecao());
		cellList.getPagerPanel().addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popupselecao_pagerpanelcelulaselecao());

		imgBuscar.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				filtrarResultados();
			}
		});

		txbFiltro.addKeyUpHandler(new KeyUpHandler()
		{

			@Override
			public void onKeyUp(KeyUpEvent event)
			{
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				{
					filtrarResultados();
				}
			}
		});

		cellList.addDomHandler(new DoubleClickHandler()
		{

			@Override
			public void onDoubleClick(DoubleClickEvent event)
			{
				verificarSelecaoUsuario();
			}
		}, DoubleClickEvent.getType());

	}

	public void montarTelaSeguranca()
	{

		qiPilhaHorizontal.adicionarWidget(lblTituloFiltro);
		qiPilhaHorizontal.adicionarWidget(txbFiltro);
		qiPilhaHorizontal.adicionarWidget(imgBuscar);

		qiPilhaVerticalEstrutura.adicionarWidget(qiPilhaHorizontal);
		qiPilhaVerticalEstrutura.adicionarWidget(cellList.getPagerPanel());

		this.setConteudo(qiPilhaVerticalEstrutura);
	}

	@Override
	public void eventoBotaoOk()
	{
		verificarSelecaoUsuario();
	}

	@Override
	public void eventoBotaoCancelar()
	{
		this.hide();
	}

	// METODOS ABSTRATOS

	public abstract void selecionarNovaEntidade(ENTIDADE novaEntidade);

	public abstract void realizarPesquisaEntidades(String strFiltro);

	// public abstract void renderizarConteudoCelulaExibicao(ENTIDADE entidade,
	// SafeHtmlBuilder builder);

	public abstract boolean validarSelecaoUsuario(ENTIDADE entidadeSelecionada);

	public abstract void acaoErroSelecaoNulla();

	public abstract void acaoErroSelecaoInvalida();

	@Override
	public void show()
	{
		super.show();
		if (!exibiuPrimeiraVez && cellList.getVisibleItems().isEmpty())
		{
			filtrarResultados();
			exibiuPrimeiraVez = true;
		}
	}
}
