package br.com.qileverage.widgets.crud.grid;

import java.util.ArrayList;
import java.util.List;

import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Image;

public abstract class QIGridColunaOpcaoExcluir<ENTIDADE> extends QIGrid<ENTIDADE>
{

	private Column<ENTIDADE, String> colunaExcluir;

	private List<HasCell<ENTIDADE, ?>> listaColunas;
	private CompositeCell<ENTIDADE> colunaComposta;

	public QIGridColunaOpcaoExcluir()
	{
	}
	
	// METODOS SOBRECARREGADOS

	@Override
	public void instanciarItens()
	{
		super.instanciarItens();
		prepararColunas();
	}

	@Override
	public void setarInformacoes()
	{
		super.setarInformacoes();
		listaColunas.add(colunaExcluir);
	}

	@Override
	public void montarTela()
	{
		super.montarTela();

		adicionarColunaComposite(true);
	}

	@Override
	public void adicionarColuna(Column<ENTIDADE, ?> coluna, String nomeColuna)
	{
		// super.adicionarColuna(coluna, nomeColuna);
		this.getCellTable().insertColumn(this.getCellTable().getColumnCount() == 0 ? 0 : this.getCellTable().getColumnCount() - 1, coluna, nomeColuna);
	}
	

	public void adicionarColuna(Column<ENTIDADE, ?> coluna, String nomeColuna, int posicao, boolean removerColunaNessaPosicao)
	{
		// super.adicionarColuna(colurna, nomeColuna);
		if (removerColunaNessaPosicao)
		{
			this.getCellTable().removeColumn(posicao);
		}

		this.getCellTable().insertColumn(posicao, coluna, nomeColuna);
	}

	private void adicionarColunaComposite(boolean firstAdd)
	{
		colunaComposta = new CompositeCell<ENTIDADE>(listaColunas);

		Column<ENTIDADE, ENTIDADE> colComposta = new Column<ENTIDADE, ENTIDADE>(colunaComposta)
		{

			@Override
			public ENTIDADE getValue(ENTIDADE object)
			{
				return object;
			}

		};

		adicionarColuna(colComposta, "Opções", this.getCellTable().getColumnCount() == 0 ? 0 : this.getCellTable().getColumnCount() - 1, !firstAdd);

		getCellTable().setColumnWidth(colComposta, "100px");
	}

	private void prepararColunas()
	{
		listaColunas = new ArrayList<HasCell<ENTIDADE, ?>>();

		Image imgColunaExcluir = new Image(Resources.INSTANCE.imgDeletarEntidade());
		imgColunaExcluir.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_grid_colunaopcoes_colunaexcluir_imagemexcluir());

		colunaExcluir = prepararColunaImagemClicavel(imgColunaExcluir, new FieldUpdater<ENTIDADE, String>()
		{
			@Override
			public void update(int index, ENTIDADE object, String value)
			{
				QIGridColunaOpcaoExcluir.this.excluirEntidade(object);
			}
		});

	}

	private Column<ENTIDADE, String> prepararColunaImagemClicavel(final Image img, FieldUpdater<ENTIDADE, String> fieldUpdater)
	{
		ClickableTextCell textCellClicavelEditarEntidade = new ClickableTextCell()
		{
			@Override
			public void render(Context context, SafeHtml data, SafeHtmlBuilder sb)
			{
				if (data != null)
				{
					sb.append(new SafeHtml()
					{

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public String asString()
						{
							return img.toString();
						}
					});
				}
			}
		};

		Column<ENTIDADE, String> colunaRetorno = new Column<ENTIDADE, String>(textCellClicavelEditarEntidade)
		{

			@Override
			public String getValue(ENTIDADE object)
			{
				return "";
			}
		};

		colunaRetorno.setFieldUpdater(fieldUpdater);

		return colunaRetorno;
	}

	public void adicionarColunaExibicao(FieldUpdater<ENTIDADE, String> fieldUpdater)
	{
		Image imaColunaVisualizacao = new Image(Resources.INSTANCE.imgVisualizacaoImagem());
		imaColunaVisualizacao.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_grid_colunaopcoes_colunaeditar_imagemexibicao());

		Column<ENTIDADE, String> colunaVisualizarImagem = prepararColunaImagemClicavel(imaColunaVisualizacao, fieldUpdater);

		listaColunas.add(0, colunaVisualizarImagem);

		adicionarColunaComposite(false);
	}
	

	public abstract void excluirEntidade(ENTIDADE entidade);

	// public abstract void alterarEntidade(ENTIDADE entidade);
}
