package br.com.qileverage.widgets.painelselecaoitens;

import java.util.ArrayList;
import java.util.List;

import br.com.qileverage.shared.Entidade;
import br.com.qileverage.widgets.QIControleMensagens;
import br.com.qileverage.widgets.cellwidgets.QIAbstractCell;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.listapersonalizada.QIUnorderedList;
import br.com.qileverage.widgets.mensagens.QIConfirmacao;
import br.com.qileverage.widgets.mensagens.QIMensagemExibicao.TipoMensagem;
import br.com.qileverage.widgets.popup.QIPopupPesquisa;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public abstract class QIPainelSelecaoEntidadeSimples<ENTIDADE extends Entidade> extends QIPainelSelecaoItens
{
	private QIPopupPesquisa<ENTIDADE> popupPesquisaEntidade;
	private QIAbstractCell<ENTIDADE> celulaAbstrata;

	private QIUnorderedList listaItensAdicionados;

	private List<QIItemAdicionado> listaEntidadesSelecionados;

	private String strTituloPadraoImagemExcluirEntidadeSelecionada;

	public QIPainelSelecaoEntidadeSimples(QIAbstractCell<ENTIDADE> celulaAbstrata)
	{
		this.celulaAbstrata = celulaAbstrata;

		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	public abstract String getTextoRepresentacaoEntidade(ENTIDADE entidade);

	public abstract void pesquisarItens(String strFiltro);

	@Override
	public void montarItens()
	{
	}

	@Override
	public void instanciarItens()
	{
		super.instanciarItens();

		strTituloPadraoImagemExcluirEntidadeSelecionada = "";

		listaEntidadesSelecionados = new ArrayList<QIItemAdicionado>();
		listaItensAdicionados = new QIUnorderedList();

		popupPesquisaEntidade = new QIPopupPesquisa<ENTIDADE>(celulaAbstrata)
		{

			@Override
			public void selecionarNovaEntidade(ENTIDADE novaEntidade)
			{
				adicionarEntidade(novaEntidade);
			}

			@Override
			public void realizarPesquisaEntidades(String strFiltro)
			{
				pesquisarItens(strFiltro);
			}

			@Override
			public boolean validarSelecaoUsuario(ENTIDADE entidadeSelecionada)
			{
				return !listaEntidadesSelecionados.contains(new QIItemAdicionado(entidadeSelecionada));
			}

			@Override
			public void acaoErroSelecaoNulla()
			{
				QIControleMensagens.mostrarMensagem("Você deve selecionar um item para adicioná-lo!", TipoMensagem.ALERTA);
			}

			@Override
			public void acaoErroSelecaoInvalida()
			{
				QIControleMensagens.mostrarMensagem("Você já adicionou esse item!", TipoMensagem.ALERTA);
			}
		};
	}

	@Override
	public void setarInformacoes()
	{
		super.setarInformacoes();

		listaItensAdicionados.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_painelselecaoitenssimples_listaitensselecionados());
	}

	@Override
	public void montarTela()
	{
		super.montarTela();

		this.setWidgetConteudo(listaItensAdicionados);
	}

	public void adicionarEntidade(ENTIDADE entidade)
	{
		QIItemAdicionado item = new QIItemAdicionado(entidade);

		item.setTitleImagemExcluir(strTituloPadraoImagemExcluirEntidadeSelecionada);

		listaEntidadesSelecionados.add(item);
		listaItensAdicionados.add(item);

		popupPesquisaEntidade.hide();

	}

	public void setListaEntidadePesquisa(List<ENTIDADE> listaEntidade)
	{
		popupPesquisaEntidade.setListaEntidade(listaEntidade);
	}

	public void setListaItensSelecionados(List<ENTIDADE> listaEntidade)
	{
		listaEntidadesSelecionados.clear();
		listaItensAdicionados.clear();

		for (ENTIDADE entidadeAtual : listaEntidade)
		{
			adicionarEntidade(entidadeAtual);
		}
	}

	public List<ENTIDADE> getListaEntidadesSelecionados()
	{
		List<ENTIDADE> listaRetorno = new ArrayList<ENTIDADE>();

		for (QIItemAdicionado itemAtual : listaEntidadesSelecionados)
		{
			listaRetorno.add(itemAtual.getEntidade());
		}

		return listaRetorno;
	}

	public void removerEntidade(final QIItemAdicionado painelEntidade)
	{
		QIControleMensagens.mostrarMensagemConfirmacao("Deseja mesmo excluir esse item?", new QIConfirmacao()
		{

			@Override
			public void sim()
			{
				listaEntidadesSelecionados.remove(painelEntidade);
				listaItensAdicionados.remove(painelEntidade);
			}

			@Override
			public void nao()
			{

			}
		});

	}

	@Override
	public void adicionarNovo()
	{
		popupPesquisaEntidade.show();
	}

	public class QIItemAdicionado extends QIPilhaHorizontal
	{
		private ENTIDADE entidade;

		private Label lblEntidade;
		private Image imgExcluirEntidade;

		public QIItemAdicionado(ENTIDADE entidade)
		{
			this.entidade = entidade;

			lblEntidade = new Label();

			imgExcluirEntidade = new Image(Resources.INSTANCE.imgDeletarEntidade());
			imgExcluirEntidade.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_painelselecaoitens_botaoacao());

			imgExcluirEntidade.addClickHandler(new ClickHandler()
			{

				@Override
				public void onClick(ClickEvent event)
				{
					removerEntidade(QIItemAdicionado.this);
				}
			});

			lblEntidade.setText(getTextoRepresentacaoEntidade(entidade));
			lblEntidade.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_painelselecaoitenssimples_itemadicionado());

			this.adicionarWidget(lblEntidade);
			this.adicionarWidget(imgExcluirEntidade);
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((entidade == null) ? 0 : entidade.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;

			@SuppressWarnings("unchecked")
			QIItemAdicionado other = (QIItemAdicionado) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;

			if (entidade == null)
			{
				if (other.entidade != null)
					return false;
			}
			else if (!entidade.equals(other.entidade))
				return false;

			return true;
		}

		private QIPainelSelecaoEntidadeSimples<ENTIDADE> getOuterType()
		{
			return QIPainelSelecaoEntidadeSimples.this;
		}

		public ENTIDADE getEntidade()
		{
			return entidade;
		}

		public void setTitleImagemExcluir(String title)
		{
			imgExcluirEntidade.setTitle(title);
		}

	}

	@SuppressWarnings("unchecked")
	public List<ENTIDADE> transformarLista(List<Entidade> listaConteudoGenerico)
	{
		List<ENTIDADE> listaRetorno = new ArrayList<ENTIDADE>();

		for (Entidade entidadeAtual : listaConteudoGenerico)
		{
			listaRetorno.add((ENTIDADE) entidadeAtual);
		}

		return listaRetorno;
	}

	public void setTituloPopupPesquisa(String titulo)
	{
		popupPesquisaEntidade.setarTituloPopup(titulo);
	}

	public void setTitleImagemExcluir(String title)
	{
		strTituloPadraoImagemExcluirEntidadeSelecionada = title;
	}

}
