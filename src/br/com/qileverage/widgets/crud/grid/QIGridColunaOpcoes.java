package br.com.qileverage.widgets.crud.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.user.cellview.client.Column;

public abstract class QIGridColunaOpcoes<ENTIDADE> extends QIGrid<ENTIDADE>
{

	private List<HasCell<ENTIDADE, ?>> listaColunas;
	private CompositeCell<ENTIDADE> compositeOpcoes;
	
	Column<ENTIDADE, ENTIDADE> colComposta;

	public QIGridColunaOpcoes()
	{
	}

	@Override
	public void instanciarItens()
	{
		super.instanciarItens();

		listaColunas = new ArrayList<HasCell<ENTIDADE, ?>>();
	}

	// METODOS SOBRECARREGADOS

	public void adicionarColunaOpcao(Column<ENTIDADE, ?> colunaOpcao)
	{
		listaColunas.add(colunaOpcao);
	}

	public void finalizarAdicaoColunaOpcoes()
	{
		compositeOpcoes = new CompositeCell<ENTIDADE>(listaColunas);

		colComposta = new Column<ENTIDADE, ENTIDADE>(compositeOpcoes)
		{

			@Override
			public ENTIDADE getValue(ENTIDADE object)
			{
				return object;
			}

		};

		adicionarColuna(colComposta, "Opções");
		getCellTable().setColumnWidth(colComposta, "auto");
	}
	
	public void setWidthColunaOpcoes(String width)
	{
		this.getCellTable().setColumnWidth(colComposta, width);
	}

}
