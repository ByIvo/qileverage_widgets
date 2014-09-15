package br.com.qileverage.widgets.multiselecao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.qileverage.widgets.cellwidgets.QIAbstractCell;
import br.com.qileverage.widgets.cellwidgets.QICellList;

import com.google.gwt.view.client.MultiSelectionModel;

public class QIPainelItens<ITEM> extends QICellList<ITEM>
{
	private MultiSelectionModel<ITEM> multiSelectionModel;

	public QIPainelItens(QIAbstractCell<ITEM> celula)
	{
		super(celula);
	}

	@Override
	public void instanciarItens()
	{
		super.instanciarItens();

		multiSelectionModel = new MultiSelectionModel<ITEM>(this.getKeyProvider());
	}

	@Override
	public void setarInformacoes()
	{
		super.setarInformacoes();

		this.setSelectionModel(multiSelectionModel);
	}

	public void adicionarNovoItem(ITEM item)
	{
		List<ITEM> novosItens = new ArrayList<ITEM>();

		novosItens.addAll(this.getVisibleItems());
		novosItens.add(item);
		this.setRowData(novosItens);
	}

	public boolean removerItem(ITEM item)
	{
		return this.removerItem(item);
	}

	public void adicionarListaItens(List<ITEM> listaItens)
	{
		// this.getVisibleItems().addAll(listaItens);
		List<ITEM> novosItens = new ArrayList<ITEM>();

		novosItens.addAll(this.getVisibleItems());
		novosItens.addAll(listaItens);
		this.setRowData(novosItens);
	}

	public void removerListaItens(List<ITEM> listaItens)
	{
		List<ITEM> novosItens = new ArrayList<ITEM>();

		novosItens.addAll(this.getVisibleItems());
		novosItens.removeAll(listaItens);

		this.setRowData(novosItens);
		// this.getVisibleItems().removeAll(listaItens);
	}

	public List<ITEM> getItensSelecionados()
	{
		Set<ITEM> setItem = this.multiSelectionModel.getSelectedSet();

		return new ArrayList<ITEM>(setItem);
	}

	public List<ITEM> removerTodos()
	{
		List<ITEM> listaItensRemovidos = new ArrayList<ITEM>(this.getVisibleItems());
		this.setRowData(new ArrayList<ITEM>());
		return listaItensRemovidos;
	}
	
	public void limparSelecao()
	{
		this.multiSelectionModel.clear();
	}
}
