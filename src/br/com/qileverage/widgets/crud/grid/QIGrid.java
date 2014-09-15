package br.com.qileverage.widgets.crud.grid;

import java.util.List;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.popup.ShowMorePagerPanel;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

public abstract class QIGrid<ENTIDADE> extends QIPilhaVertical implements QITela
{

	private CellTable<ENTIDADE> cellTable;
	private ListDataProvider<ENTIDADE> dataProvider;
	private SingleSelectionModel<ENTIDADE> singleSelectionModel;
	private ShowMorePagerPanel pagerPanel;

	public QIGrid()
	{
		montarEstrutura();
	}

	public void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	// METODOS ACOES

	public void adicionarEntidade(ENTIDADE entidade)
	{
		dataProvider.getList().add(entidade);
	}

	public void adicionarListaEntidades(List<ENTIDADE> listaEntidade)
	{
		dataProvider.getList().addAll(listaEntidade);
	}

	public void setListaEntidades(List<ENTIDADE> listaEntidade)
	{
		dataProvider.getList().clear();
		adicionarListaEntidades(listaEntidade);
	}

	public boolean removerEntidade(ENTIDADE entidade)
	{
		return dataProvider.getList().remove(entidade);
	}

	public void setEntidade(ENTIDADE entidade)
	{
		int entidadeVelhaIndex = dataProvider.getList().indexOf(entidade);
		dataProvider.getList().set(entidadeVelhaIndex, entidade);

		this.refreshEntidades();
	}

	public void limparListaEntidades()
	{
		dataProvider.getList().clear();
	}

	public void refreshEntidades()
	{
		dataProvider.refresh();
	}

	public ENTIDADE getEntidadeSelecionada()
	{
		return singleSelectionModel.getSelectedObject();
	}

	public List<ENTIDADE> getListaEntidades()
	{
		return dataProvider.getList();
	}

	public void selecionarEntidade(ENTIDADE entidade, boolean selecionar)
	{
		singleSelectionModel.setSelected(entidade, selecionar);
	}

	public void adicionarColuna(Column<ENTIDADE, ?> coluna, final String nomeColuna)
	{
		cellTable.addColumn(coluna, new SafeHtml()
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString()
			{
				return "<div style=\"text-align: center;width: 100%\">" + nomeColuna + "</div>";
			}
		});
	}

	public boolean contemEntidade(ENTIDADE entidade)
	{
		return dataProvider.getList().contains(entidade);
	}

	// METODOS SOBRECARREGADOS
	@Override
	public void instanciarItens()
	{
		cellTable = new CellTable<ENTIDADE>();
		dataProvider = new ListDataProvider<ENTIDADE>();
		singleSelectionModel = new SingleSelectionModel<ENTIDADE>();
		pagerPanel = new ShowMorePagerPanel();
	}

	@Override
	public void setarInformacoes()
	{
		setarEmptyWidgetPadrao();

		cellTable.setSelectionModel(singleSelectionModel);
		dataProvider.addDataDisplay(cellTable);
		pagerPanel.setDisplay(cellTable);

		cellTable.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_grid());
		pagerPanel.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_grid_pagerpanel());

		singleSelectionModel.addSelectionChangeHandler(new Handler()
		{
			@Override
			public void onSelectionChange(SelectionChangeEvent event)
			{
				if (singleSelectionModel.getSelectedObject() != null)
				{
					QIGrid.this.selecionou(singleSelectionModel.getSelectedObject());
				}
			}
		});

	}

	private void setarEmptyWidgetPadrao()
	{
		Label lblWidgetVazio = new Label();
		lblWidgetVazio.setText("Não há nenhum item para ser exibido!");

		lblWidgetVazio.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_griddados_emptywidget());

		cellTable.setEmptyTableWidget(lblWidgetVazio);
	}

	@Override
	public void montarTela()
	{
		this.adicionarWidget(pagerPanel);
	}

	// GETTERS AND SETTERS

	public CellTable<ENTIDADE> getCellTable()
	{
		return cellTable;
	}

	public void setCellTable(CellTable<ENTIDADE> cellTable)
	{
		this.cellTable = cellTable;
	}

	public void definirAlturaPadraoGrid()
	{
		if (Window.getClientHeight() > 0)
		{
			pagerPanel.setHeight((Window.getClientHeight() - 106) * .35 + "px");
		}
	}

	// METODOS ABSTRATOS

	public abstract void selecionou(ENTIDADE entidade);

}
