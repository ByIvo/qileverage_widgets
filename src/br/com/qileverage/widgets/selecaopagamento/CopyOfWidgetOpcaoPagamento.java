package br.com.qileverage.widgets.selecaopagamento;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.funcoes.CControleFuncoesClient;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class CopyOfWidgetOpcaoPagamento extends QIPilhaVertical implements QITela, HasClickHandlers
{
	private QIInterfaceTipoPagamento opcaoPagamento;

	private Label lblNomeOpcaoPagamento;
	private Image imgOpacaoPagamento;

	public CopyOfWidgetOpcaoPagamento(QIInterfaceTipoPagamento opcaoPagamento)
	{
		this.opcaoPagamento = opcaoPagamento;
		montarEstrutura();
	}

	private void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	public void adicionarEstiloNomeOpcaoPagamento(String nomeEstilo)
	{
		lblNomeOpcaoPagamento.addStyleName(nomeEstilo);
	}

	public void adicionarEstiloImagemOpcaoPagamento(String nomeEstilo)
	{
		imgOpacaoPagamento.addStyleName(nomeEstilo);
	}

	public void removerEstiloNomeOpcaoPagamento(String nomeEstilo)
	{
		lblNomeOpcaoPagamento.removeStyleName(nomeEstilo);
	}

	public void removerEstiloImagemOpcaoPagamento(String nomeEstilo)
	{
		imgOpacaoPagamento.removeStyleName(nomeEstilo);
	}

	// METODOS SOBRECARREGADOS

	@Override
	public void instanciarItens()
	{
		lblNomeOpcaoPagamento = new Label();
		imgOpacaoPagamento = new Image();
	}

	@Override
	public void setarInformacoes()
	{
		lblNomeOpcaoPagamento.setText(opcaoPagamento.getNomePagamento());

		imgOpacaoPagamento.setResource(opcaoPagamento.getImagemPagamento());
		imgOpacaoPagamento.setTitle(opcaoPagamento.getNomePagamento());

		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_selecaopagamento_painelselecaopagamento_opcaopagamento());
		lblNomeOpcaoPagamento.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_selecaopagamento_painelselecaopagamento_opcaopagamento_labelnome());
		imgOpacaoPagamento.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_selecaopagamento_painelselecaopagamento_opcaopagamento_imagem());

		CControleFuncoesClient.sinkClickEvents(this);
	}

	@Override
	public void montarTela()
	{
		this.adicionarWidget(imgOpacaoPagamento);
		this.adicionarWidget(lblNomeOpcaoPagamento);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler)
	{
		return this.addHandler(handler, ClickEvent.getType());
	}

	// GETTERS AND SETTERS

	public QIInterfaceTipoPagamento getOpcaoPagamento()
	{
		return opcaoPagamento;
	}

	public void setOpcaoPagamento(QIInterfaceTipoPagamento opcaoPagamento)
	{
		this.opcaoPagamento = opcaoPagamento;
	}

}
