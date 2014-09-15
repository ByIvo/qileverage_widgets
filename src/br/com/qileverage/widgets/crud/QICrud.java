package br.com.qileverage.widgets.crud;

import java.util.ArrayList;
import java.util.List;

import br.com.qileverage.shared.Entidade;
import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.crud.botoes.QIBotaoAdicionar;
import br.com.qileverage.widgets.crud.botoes.QIBotaoAlterar;
import br.com.qileverage.widgets.crud.botoes.QIBotaoCancelar;
import br.com.qileverage.widgets.crud.grid.QIGridColunaOpcaoExcluir;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.form.QIForm;
import br.com.qileverage.widgets.form.eventos.EnvioFormularioEvent;
import br.com.qileverage.widgets.form.eventos.EnvioFormularioHandler;
import br.com.qileverage.widgets.funcoes.CControleFuncoesClient;
import br.com.qileverage.widgets.listapersonalizada.QIUnorderedList;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasVerticalAlignment.VerticalAlignmentConstant;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class QICrud<ENTIDADE extends Entidade> extends QIPilhaVertical implements QITela
{
	private QIPainelCamposCadastro qiPainelCamposCadastro;
	private QIUnorderedList qiListaBotoes;

	private QIPilhaVertical qiPainelWidgetCadastro;

	private QIBotaoAdicionar btnAdicionar;
	private QIBotaoAlterar btnAlterar;
	private QIBotaoCancelar btnCancelar;

	private QIGridColunaOpcaoExcluir<ENTIDADE> gridEntidades;

	private Label lblDescricaoCrud;
	private Label lblDescricaoCamposAuxiliarCrud;

	private ENTIDADE entidadeSelecionada;

	private boolean isModoAlteracao;

	private EnvioFormularioHandler envioFormularioHandler;

	public QICrud()
	{
		montarEstrutura();
	}

	private void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	// ACOES

	public void setTituloInformacaoCampos(String strTitulo)
	{
		lblDescricaoCrud.setText(CControleFuncoesClient.getNomeCampoComSeparador(strTitulo));
	}

	public void setTituloInformacaoCamposAdicionais(String strTitulo)
	{
		lblDescricaoCamposAuxiliarCrud.setText(CControleFuncoesClient.getNomeCampoComSeparador(strTitulo));
	}

	public void adicionarColuna(Column<ENTIDADE, ?> coluna, String nomeColuna)
	{
		gridEntidades.adicionarColuna(coluna, nomeColuna);
	}

	public void adicionarColunaExibicao(FieldUpdater<ENTIDADE, String> fieldUpdater)
	{
		gridEntidades.adicionarColunaExibicao(fieldUpdater);
	}

	public void adicionarFormCadastro(QIForm qiForm)
	{
		qiPainelCamposCadastro.setQiForm(qiForm);
	}

	public void adicionarWidgetCadastro(Widget wCadastro)
	{
		qiPainelCamposCadastro.setWidgetAuxCadastro(wCadastro);
	}

	public void cancelarSelecao()
	{
		gridEntidades.selecionarEntidade(entidadeSelecionada, false);
		entidadeSelecionada = null;
		limparCampos();

		mudarModoAdicao();
	}

	public void selecionar(ENTIDADE entidade)
	{
		if (this.gridEntidades.contemEntidade(entidade))
		{
			entidadeSelecionada = entidade;
			setFormByEntidade(entidade);

			mudarModoAlteracao();
		}
	}

	public void mudarModoAlteracao()
	{
		isModoAlteracao = true;

		qiListaBotoes.clear();
		qiListaBotoes.add(btnAlterar);
		qiListaBotoes.add(btnCancelar);

		btnCancelar.setEnabled(true);
	}

	public void mudarModoAdicao()
	{
		isModoAlteracao = false;

		qiListaBotoes.clear();
		qiListaBotoes.add(btnAdicionar);
		qiListaBotoes.add(btnCancelar);

		btnCancelar.setEnabled(true);

		btnCancelar.setEnabled(false);
	}

	public void adicionarEntidadeNoGrid(ENTIDADE entidade)
	{
		gridEntidades.adicionarEntidade(entidade);
		refreshDisplay();
	}

	public void removerEntidadeNoGrid(ENTIDADE entidade)
	{
		gridEntidades.removerEntidade(entidade);
		limparCampos();
		mudarModoAdicao();
	}

	public void refreshDisplay()
	{
		gridEntidades.refreshEntidades();
	}

	public void eventoAdicao()
	{
		if (qiPainelCamposCadastro.getQiForm().validarCampos() && validacaoExtraAdicao())
		{
			adicionarEntidade();
		}
	}

	public void eventoAlteracao(ENTIDADE entidade)
	{
		if (qiPainelCamposCadastro.getQiForm().validarCampos() && validacaoExtraAdicao())
		{
			alterarEntidade(entidade);

			cancelarSelecao();
			this.refreshDisplay();
		}
	}

	public void eventoExclusao(ENTIDADE entidade)
	{
		excluirEntidade(entidade);
		cancelarSelecao();
	}

	public void substituirEntidade(ENTIDADE entidadeAntiga, ENTIDADE entidadeNova)
	{
		removerEntidadeNoGrid(entidadeAntiga);
		adicionarEntidadeNoGrid(entidadeNova);
	}

	public void setListaEntidades(List<ENTIDADE> listaEntidade)
	{
		gridEntidades.setListaEntidades(listaEntidade);
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

	public void setWidthColuna(Column<ENTIDADE, ?> coluna, String columnWidth)
	{
		this.getGridEntidades().getCellTable().setColumnWidth(coluna, columnWidth);
	}

	public void setHorizotalAlignmentColuna(Column<ENTIDADE, ?> coluna, HorizontalAlignmentConstant horizontalAlignment)
	{
		coluna.setHorizontalAlignment(horizontalAlignment);
	}

	public void setVerticalAlignmentColuna(Column<ENTIDADE, ?> coluna, VerticalAlignmentConstant verticalAlignment)
	{
		coluna.setVerticalAlignment(verticalAlignment);
	}

	public Column<ENTIDADE, String> adicionarColunaPadrao(String nome, String widthColuna, final QIValorPadraoColuna<ENTIDADE> valor)
	{
		Column<ENTIDADE, String> coluna = new Column<ENTIDADE, String>(new TextCell())
		{

			@Override
			public String getValue(ENTIDADE object)
			{
				return valor.getValorColuna(object);
			}
		};

		this.adicionarColuna(coluna, nome);

		if (widthColuna != null)
		{
			if (!widthColuna.isEmpty())
			{
				this.setWidthColuna(coluna, widthColuna);
			}
		}

		return coluna;
	}

	// METODOS SOBRECARREGADOS

	@Override
	public void instanciarItens()
	{
		qiListaBotoes = new QIUnorderedList();
		qiPainelCamposCadastro = new QIPainelCamposCadastro();

		qiPainelWidgetCadastro = new QIPilhaVertical();

		lblDescricaoCrud = new Label();
		lblDescricaoCamposAuxiliarCrud = new Label();

		btnAdicionar = new QIBotaoAdicionar();
		btnAlterar = new QIBotaoAlterar();
		btnCancelar = new QIBotaoCancelar();

		gridEntidades = new QIGridColunaOpcaoExcluir<ENTIDADE>()
		{

			@Override
			public void selecionou(ENTIDADE entidade)
			{
				selecionar(entidade);
				QICrud.this.selecionouEntidade(entidade);
			}

			@Override
			public void excluirEntidade(ENTIDADE entidade)
			{
				QICrud.this.eventoExclusao(entidade);
			}
		};

		envioFormularioHandler = new EnvioFormularioHandler()
		{

			@Override
			public void onEnvioFormulario(EnvioFormularioEvent event)
			{
				if (isModoAlteracao)
				{
					eventoAlteracao(entidadeSelecionada);
				}
				else
				{
					eventoAdicao();
				}
			}
		};
	}

	@Override
	public void setarInformacoes()
	{
		isModoAlteracao = false;
		btnCancelar.setEnabled(false);

		gridEntidades.definirAlturaPadraoGrid();

		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud());
		gridEntidades.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_griddados());
		qiPainelWidgetCadastro.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelhorizontalcampos());
		qiPainelCamposCadastro.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelhorizontalcampos_camposadicao());
		lblDescricaoCrud.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelhorizontalcampos_labeldescricaocrud());
		lblDescricaoCamposAuxiliarCrud.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelhorizontalcampos_labeldescricaocamposauxiliarcrud());
		qiListaBotoes.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelhorizontalcampos_painelbotoes());

		btnAdicionar.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelhorizontalcampos_painelbotoes_botaoadicionaralterar());
		btnAlterar.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelhorizontalcampos_painelbotoes_botaoadicionaralterar());
		btnCancelar.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelhorizontalcampos_painelbotoes_botaocancelar());

		btnAdicionar.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				eventoAdicao();
			}
		});

		btnAlterar.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				eventoAlteracao(entidadeSelecionada);
			}
		});

		btnCancelar.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				cancelarSelecao();
			}
		});

	}

	@Override
	public void montarTela()
	{
		qiListaBotoes.add(btnAdicionar);
		qiListaBotoes.add(btnCancelar);

		QIPilhaHorizontal pilhaHorizontalCaposDescricao = new QIPilhaHorizontal();

		pilhaHorizontalCaposDescricao.adicionarWidget(lblDescricaoCrud);
		pilhaHorizontalCaposDescricao.adicionarWidget(lblDescricaoCamposAuxiliarCrud);

		qiPainelWidgetCadastro.adicionarWidget(pilhaHorizontalCaposDescricao);

		qiPainelWidgetCadastro.adicionarWidget(qiPainelCamposCadastro);
		// qiPainelWidgetCadastro.adicionarWidget(qiListaBotoes);

		this.adicionarWidget(gridEntidades);
		this.adicionarWidget(qiPainelWidgetCadastro);
		this.adicionarWidget(qiListaBotoes);

		qiListaBotoes.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelcamposcadastro_listabotoes_celula());
	}

	// GETTERS AND SETTERS

	public QIGridColunaOpcaoExcluir<ENTIDADE> getGridEntidades()
	{
		return gridEntidades;
	}

	public void setGridEntidades(QIGridColunaOpcaoExcluir<ENTIDADE> gridEntidades)
	{
		this.gridEntidades = gridEntidades;
	}

	public boolean isModoAlteracao()
	{
		return isModoAlteracao;
	}

	public EnvioFormularioHandler getEnvioFormularioHandler()
	{
		return envioFormularioHandler;
	}

	// METODOS ABSTRATOS

	public abstract ENTIDADE getEntidadeByForm(ENTIDADE entidade);

	public abstract void setFormByEntidade(ENTIDADE entidade);

	public abstract void alterarEntidade(ENTIDADE entidade);

	public abstract void adicionarEntidade();

	public abstract void excluirEntidade(ENTIDADE entidade);

	public abstract void selecionouEntidade(ENTIDADE entidade);

	public abstract void limparCampos();

	public abstract boolean validacaoExtraAdicao();

}
