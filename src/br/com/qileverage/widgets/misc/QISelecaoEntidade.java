package br.com.qileverage.widgets.misc;

import java.util.ArrayList;
import java.util.List;

import br.com.qileverage.shared.Entidade;
import br.com.qileverage.widgets.QIControleCookies;
import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.funcoes.CControleFuncoesClient;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public abstract class QISelecaoEntidade<ENTIDADE> extends QIPilhaHorizontal implements QITela
{

	private final String strMensagemSemSelecao = "Nenhum item selecionado.";
	private final String strMensagemUmaSelecao = "Um item selecionado.";
	private final String strMensagemVariasSelecoes = " itens selecionados.";

	private Label lblDescricao;
	private QIPilhaVertical pilhaItensSelecao;
	private QIPilhaVertical pilhaInformacoesSelecao;
	private Label lblItensSelecionados;

	private List<CampoSelecao> listaCampoSelecao;
	private ClickHandler clickHandler;

	public QISelecaoEntidade()
	{
		montarInformacoes();
	}

	private void montarInformacoes()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	@Override
	public void instanciarItens()
	{
		pilhaItensSelecao = new QIPilhaVertical();
		lblDescricao = new Label();

		pilhaInformacoesSelecao = new QIPilhaVertical();
		lblItensSelecionados = new Label();

		listaCampoSelecao = new ArrayList<CampoSelecao>();

		lblItensSelecionados = new Label();

		clickHandler = new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				if (event.getSource() instanceof QISelecaoEntidade.CampoSelecao)
				{
					@SuppressWarnings("unchecked")
					CampoSelecao campoSelecao = (CampoSelecao) event.getSource();

					if (campoSelecao.isVisivel())
					{
						campoSelecao.ocultarEntidade();
					}
					else
					{
						campoSelecao.exibirEntidade();
					}

					setLabelSelecaoItens();
				}
			}
		};
	}

	@Override
	public void setarInformacoes()
	{
		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_misc_selecaoentidade());
		pilhaItensSelecao.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_misc_selecaoentidade_pilhaelementos());
		lblDescricao.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_misc_selecaoentidade_labeldescricao());
		lblItensSelecionados.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_misc_selecaoentidade_labelitenselecionados());
	}

	@Override
	public void montarTela()
	{
		pilhaInformacoesSelecao.adicionarWidget(pilhaItensSelecao);
		pilhaInformacoesSelecao.adicionarWidget(lblItensSelecionados);

		this.adicionarWidget(lblDescricao);
		this.adicionarWidget(pilhaInformacoesSelecao);
	}

	private void setLabelSelecaoItens()
	{
		int itensSelecionados = getEntidadesSelecionadas().size();

		if (itensSelecionados == 0)
		{
			lblItensSelecionados.setText(strMensagemSemSelecao);
		}
		else if (itensSelecionados == 1)
		{
			lblItensSelecionados.setText(strMensagemUmaSelecao);
		}
		else
		{
			lblItensSelecionados.setText(itensSelecionados + strMensagemVariasSelecoes);
		}
	}

	public List<ENTIDADE> getEntidadesSelecionadas()
	{
		List<ENTIDADE> entidadesSelecionadas = new ArrayList<ENTIDADE>();

		for (CampoSelecao campoSelecaoAtual : listaCampoSelecao)
		{
			if (campoSelecaoAtual.isVisivel())
			{
				entidadesSelecionadas.add(campoSelecaoAtual.getEntidade());
			}
		}

		return entidadesSelecionadas;
	}

	public void setNomeLabelDescricao(String texto)
	{
		lblDescricao.setText(CControleFuncoesClient.getNomeCampoComSeparador(texto));
	}

	public List<ENTIDADE> getEntidadesNaoSelecionadas()
	{
		List<ENTIDADE> entidadesSelecionadas = new ArrayList<ENTIDADE>();

		for (CampoSelecao campoSelecaoAtual : listaCampoSelecao)
		{
			if (!campoSelecaoAtual.isVisivel())
			{
				entidadesSelecionadas.add(campoSelecaoAtual.getEntidade());
			}
		}

		return entidadesSelecionadas;
	}

	public List<ENTIDADE> getEntidadesAdicionadas()
	{
		List<ENTIDADE> entidadesSelecionadas = new ArrayList<ENTIDADE>();

		for (CampoSelecao campoSelecaoAtual : listaCampoSelecao)
		{
			entidadesSelecionadas.add(campoSelecaoAtual.getEntidade());
		}

		return entidadesSelecionadas;
	}

	public void adicionarNovaEntidade(List<ENTIDADE> listaEntidade, boolean removerOutras)
	{
		if (removerOutras)
		{
			limparItensAdicionados();
		}

		for (ENTIDADE entidade : listaEntidade)
		{
			adicionarNovaEntidade(entidade);
		}
	}

	public void limparItensAdicionados()
	{
		listaCampoSelecao.clear();
		pilhaItensSelecao.clear();
	}

	public void adicionarNovaEntidade(ENTIDADE entidade)
	{
		CampoSelecao campoSelecao = new CampoSelecao(entidade);

		campoSelecao.addDomHandler(clickHandler, ClickEvent.getType());

		this.listaCampoSelecao.add(campoSelecao);
		pilhaItensSelecao.adicionarWidget(campoSelecao);

		setLabelSelecaoItens();
	}

	public List<ENTIDADE> transformarListaEntidade(List<Entidade> listaEntidade)
	{
		List<ENTIDADE> listaRetorno = new ArrayList<ENTIDADE>();

		for (Entidade entidade : listaEntidade)
		{
			@SuppressWarnings("unchecked")
			ENTIDADE entidadeCovertida = (ENTIDADE) entidade;

			listaRetorno.add(entidadeCovertida);
		}

		return listaRetorno;
	}

	public abstract String getNomeEntidade(ENTIDADE entidade);

	public class CampoSelecao extends QIPilhaHorizontal
	{
		private static final String preCookieName = "pagamentoprofessores_despesaprofessor_";
		private static final String cookieValueSelecionado = "1";
		private static final String cookieValueNaoSelecionado = "0";

		private Image imgClosure;
		private Label lblNome;

		private ENTIDADE entidade;

		private boolean visivel;

		public CampoSelecao(ENTIDADE entidade)
		{
			this.entidade = entidade;

			instanciar();
			setInfoGeral();
			montar();
		}

		private void instanciar()
		{
			imgClosure = new Image();
			lblNome = new Label();
		}

		private void setInfoGeral()
		{
			lblNome.setText(getNomeEntidade(entidade));

			this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_misc_selecaoentidade_camposelecao());

			verificarSelecaoByCookie();
		}

		private void verificarSelecaoByCookie()
		{
			String valor = QIControleCookies.getCookie(getCookieName());

			if (valor != null)
			{
				if (valor.equals(cookieValueNaoSelecionado))
				{
					ocultarEntidade();
					return;
				}
			}

			exibirEntidade();
		}

		private void montar()
		{
			this.adicionarWidget(lblNome);
			this.adicionarWidget(imgClosure);
		}

		public void ocultarEntidade()
		{
			imgClosure.setResource(Resources.INSTANCE.imgMostrarEntidade());
			imgClosure.setTitle("Exibir opção");

			lblNome.removeStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_misc_selecaoentidade_camposelecao_label_selecionado());
			lblNome.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_misc_selecaoentidade_camposelecao_label_deselecionado());

			visivel = false;

			QIControleCookies.adicionarCookie(getCookieName(), cookieValueNaoSelecionado);
		}

		public void exibirEntidade()
		{
			imgClosure.setResource(Resources.INSTANCE.imgOcultarEntidade());
			imgClosure.setTitle("Ocultar opção");

			lblNome.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_misc_selecaoentidade_camposelecao_label_selecionado());
			lblNome.removeStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_misc_selecaoentidade_camposelecao_label_deselecionado());

			visivel = true;

			QIControleCookies.adicionarCookie(getCookieName(), cookieValueSelecionado);
		}

		public String getCookieName()
		{
			return preCookieName + getNomeEntidade(entidade);
		}

		public ENTIDADE getEntidade()
		{
			return entidade;
		}

		public void setEntidade(ENTIDADE entidade)
		{
			this.entidade = entidade;
		}

		public boolean isVisivel()
		{
			return visivel;
		}

		public void setVisivel(boolean visivel)
		{
			this.visivel = visivel;
		}

	}
}
