package br.com.qileverage.widgets.listapersonalizada;

import br.com.qileverage.widgets.QITela;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class QIUnorderedList extends ComplexPanel implements QITela
{
	private QIUL qiUl;

	public QIUnorderedList()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	@Override
	public void instanciarItens()
	{
		qiUl = (QIUL) DOM.createElement(QIUL.LIST_ITEM_TAG);

	}

	@Override
	public void setarInformacoes()
	{
		setElement(qiUl);
	}

	@Override
	public void montarTela()
	{

	}

	@Override
	public void add(Widget w)
	{
		QIListItemElement qiLiELement = (QIListItemElement) DOM.createElement(QIListItemElement.LIST_ITEM_TAG);
		
		DOM.appendChild(qiUl, qiLiELement);
		super.add(w, qiLiELement);
	}
	
	public void addList(QIUnorderedList w)
	{
//		QIListItemElement qiLiELement = (QIListItemElement) DOM.createElement(QIListItemElement.LIST_ITEM_TAG);

//		DOM.appendChild(qiUl, qiLiELement);
		super.add(w, qiUl);
	}

	public void add(Widget w, String cellStyleName)
	{
		QIListItemElement qiLiELement = (QIListItemElement) DOM.createElement(QIListItemElement.LIST_ITEM_TAG);
		qiLiELement.addClassName(cellStyleName);

		DOM.appendChild(qiUl, qiLiELement);
		super.add(w, qiLiELement);
	}

	public void insert(Widget w, int beforeIndex)
	{
		checkIndexBoundsForInsertion(beforeIndex);

		QIListItemElement qiLiELement = (QIListItemElement) DOM.createElement(QIListItemElement.LIST_ITEM_TAG);

		DOM.appendChild(qiUl, qiLiELement);

		DOM.insertChild(qiUl, qiLiELement, beforeIndex);
		super.insert(w, qiLiELement, beforeIndex, false);
	}

	@Override
	public boolean remove(Widget w)
	{
		QIListItemElement qiLiParent = QIListItemElement.as(DOM.getParent(w.getElement()));

		boolean removed = super.remove(w);
		if (removed)
		{
			DOM.removeChild(qiUl, qiLiParent);
		}
		return removed;
	}

}
