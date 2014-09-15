package br.com.qileverage.widgets.crud.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.user.cellview.client.Column;

public abstract class QIGridColunaOpcoes<ENTIDADE> extends QIGrid<ENTIDADE>
{

	private List<HasCell<ENTIDADE, ?>> listaColunas;
	private CompositeCell<ENTIDADE> colunaComposta;

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
		colunaComposta = new CompositeCell<ENTIDADE>(listaColunas);

		Column<ENTIDADE, ENTIDADE> colComposta = new Column<ENTIDADE, ENTIDADE>(colunaComposta)
		{

			@Override
			public ENTIDADE getValue(ENTIDADE object)
			{
				return object;
			}

		};

		adicionarColuna(colComposta, "Opções");
		getCellTable().setColumnWidth(colComposta, "100px");
	}

}
