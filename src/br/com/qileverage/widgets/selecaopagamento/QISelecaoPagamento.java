package br.com.qileverage.widgets.selecaopagamento;

import java.util.ArrayList;
import java.util.List;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.funcoes.CControleFuncoesClient;
import br.com.qileverage.widgets.listapersonalizada.QIUnorderedList;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;

public abstract class QISelecaoPagamento extends QIPilhaVertical implements QITela
{
	private Label lblDescricao;
	private QIUnorderedList qiListaOpcoesPagamentos;

	private WidgetOpcaoPagamento opcaoSelecionada;

	private List<WidgetOpcaoPagamento> listOpcoesPagamentoAdicionadas;

	private OpcaoPagamentoClickHandler clickHandlerOpPag;

	public QISelecaoPagamento()
	{
		montarEstrutura();
	}

	private void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	// METODOS

	public void adicionarNovoMetodoPagamento(QIInterfaceTipoPagamento opcaoPagamento)
	{
		WidgetOpcaoPagamento wid = new WidgetOpcaoPagamento(opcaoPagamento);

		wid.addClickHandler(clickHandlerOpPag);

		qiListaOpcoesPagamentos.add(wid);
		listOpcoesPagamentoAdicionadas.add(wid);

	}

	private void selecionarNovaOpcaoPagamento(WidgetOpcaoPagamento novaOpcaoSelecionada)
	{
		if (!novaOpcaoSelecionada.equals(opcaoSelecionada))
		{
			if (opcaoSelecionada != null)
			{
				opcaoSelecionada.removerEstiloSelecao();
			}

			novaOpcaoSelecionada.adicionarEstiloSelecao();
			opcaoSelecionada = novaOpcaoSelecionada;
			selecionouNovaOpcao(novaOpcaoSelecionada.getOpcaoPagamento());
		}
	}

	// METODOS SOBRECARREGADOS

	@Override
	public void instanciarItens()
	{
		lblDescricao = new Label();
		qiListaOpcoesPagamentos = new QIUnorderedList();

		opcaoSelecionada = null;

		listOpcoesPagamentoAdicionadas = new ArrayList<WidgetOpcaoPagamento>();

		clickHandlerOpPag = new OpcaoPagamentoClickHandler();
	}

	@Override
	public void setarInformacoes()
	{
		lblDescricao.setText(CControleFuncoesClient.getNomeCampoComSeparador(Resources.CONSTANTES.widget_selecaopagamento_labeldescricao()));
		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_selecaopagamento());
		lblDescricao.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_selecaopagamento_labeldescricao());
		qiListaOpcoesPagamentos.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_selecaopagamento());
	}

	@Override
	public void montarTela()
	{
		this.adicionarWidget(lblDescricao);
		this.adicionarWidget(qiListaOpcoesPagamentos);
	}

	// GETTERS AND SETTERS

	public List<WidgetOpcaoPagamento> getListOpcoesPagamentoAdicionadas()
	{
		return listOpcoesPagamentoAdicionadas;
	}

	public void setListOpcoesPagamentoAdicionadas(List<WidgetOpcaoPagamento> listOpcoesPagamentoAdicionadas)
	{
		this.listOpcoesPagamentoAdicionadas = listOpcoesPagamentoAdicionadas;
	}

	public WidgetOpcaoPagamento getOpcaoSelecionada()
	{
		return opcaoSelecionada;
	}

	public void setOpcaoSelecionada(WidgetOpcaoPagamento opcaoSelecionada)
	{
		this.opcaoSelecionada = opcaoSelecionada;
	}

	// INNER CLASSES

	public class OpcaoPagamentoClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event)
		{
			assert event.getSource() instanceof WidgetOpcaoPagamento;

			WidgetOpcaoPagamento novaOpcaoSelecionada = (WidgetOpcaoPagamento) event.getSource();

			selecionarNovaOpcaoPagamento(novaOpcaoSelecionada);
		}
	}

	// METOS ABSTRATOS

	public abstract void selecionouNovaOpcao(QIInterfaceTipoPagamento opcaoSelecionada);
}
