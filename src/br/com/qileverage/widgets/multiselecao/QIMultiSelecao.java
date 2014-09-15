package br.com.qileverage.widgets.multiselecao;

import java.util.List;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.cellwidgets.QIAbstractCell;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.funcoes.CControleFuncoesClient;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.user.client.ui.Label;

public class QIMultiSelecao<ITEM> extends QIPilhaVertical implements QITela
{
	private QIPilhaHorizontal layout;

	private QIPainelItens<ITEM> itensNaoSelecionados;
	private QIPainelItens<ITEM> itensSelecionados;

	private QIPainelBotoesAcaoSelecaoItem painelBotoesAcoes;

	private QIAbstractCell<ITEM> celulaExibicao;

	private Label lblNome;

	public QIMultiSelecao(QIAbstractCell<ITEM> celulaExibicao)
	{
		this.celulaExibicao = celulaExibicao;
		montarItens();
	}

	private void montarItens()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	@Override
	public void instanciarItens()
	{
		itensNaoSelecionados = new QIPainelItens<ITEM>(celulaExibicao);
		itensSelecionados = new QIPainelItens<ITEM>(celulaExibicao);

		layout = new QIPilhaHorizontal();
		lblNome = new Label();

		painelBotoesAcoes = new QIPainelBotoesAcaoSelecaoItem()
		{

			@Override
			void removerTodos()
			{
				List<ITEM> listaItens = itensSelecionados.removerTodos();
				itensSelecionados.limparSelecao();

				itensNaoSelecionados.adicionarListaItens(listaItens);
			}

			@Override
			void removerSelecionados()
			{
				List<ITEM> listaItens = itensSelecionados.getItensSelecionados();
				itensSelecionados.limparSelecao();

				itensSelecionados.removerListaItens(listaItens);
				itensNaoSelecionados.adicionarListaItens(listaItens);
			}

			@Override
			void passarTodos()
			{
				List<ITEM> listaItens = itensNaoSelecionados.removerTodos();
				itensNaoSelecionados.limparSelecao();

				itensSelecionados.adicionarListaItens(listaItens);
			}

			@Override
			void passarSelecionados()
			{
				List<ITEM> listaItens = itensNaoSelecionados.getItensSelecionados();
				itensNaoSelecionados.limparSelecao();

				itensNaoSelecionados.removerListaItens(listaItens);
				itensSelecionados.adicionarListaItens(listaItens);
			}
		};
	}

	@Override
	public void setarInformacoes()
	{
		itensNaoSelecionados.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_multiplaselecao_cellwidgetselecao());
		itensSelecionados.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_multiplaselecao_cellwidgetselecao());

		layout.setSize("100%", "100%");

		painelBotoesAcoes.setWidth("10%");
		painelBotoesAcoes.setHeight("100%");
	}

	@Override
	public void montarTela()
	{

		layout.adicionarWidget(itensNaoSelecionados);
		layout.adicionarWidget(painelBotoesAcoes);
		layout.adicionarWidget(itensSelecionados);

		this.adicionarWidget(lblNome);
		this.adicionarWidget(layout);
	}

	public void setItensNaoSelecionados(List<ITEM> itensNaoSelecionados)
	{
		this.itensNaoSelecionados.adicionarListaItens(itensNaoSelecionados);
	}

	public void setItensSelecionados(List<ITEM> itensSelecionados)
	{
		this.itensSelecionados.adicionarListaItens(itensSelecionados);
	}

	public void setNomeCampo(String nome)
	{
		this.lblNome.setText(CControleFuncoesClient.getNomeCampoComSeparador(nome));
	}

	public void limparTodos()
	{
		this.itensNaoSelecionados.removerTodos();
		this.itensSelecionados.removerTodos();
	}

	public List<ITEM> getAllItensNaoSelecionados()
	{
		return itensNaoSelecionados.getVisibleItems();
	}

	public List<ITEM> getAllItensSelecionados()
	{
		return itensSelecionados.getVisibleItems();
	}

}
