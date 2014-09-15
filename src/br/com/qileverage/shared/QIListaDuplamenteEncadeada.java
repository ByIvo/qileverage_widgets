package br.com.qileverage.shared;

import java.io.Serializable;

public class QIListaDuplamenteEncadeada<ITEM> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private QIItemListaDuplamenteEncadeada<ITEM> first;
	private QIItemListaDuplamenteEncadeada<ITEM> last;

	private QIItemListaDuplamenteEncadeada<ITEM> actual;

	private int size;

	public QIListaDuplamenteEncadeada()
	{
		first = null;
		last = null;
	}

	public void addItem(ITEM novo)
	{
		QIItemListaDuplamenteEncadeada<ITEM> novoItemList = new QIItemListaDuplamenteEncadeada<ITEM>(novo);

		if (first == null)
		{
			insertFirst(novoItemList);
		}
		else
		{
			QIItemListaDuplamenteEncadeada<ITEM> lastLastItem = last;
			last = novoItemList;

			lastLastItem.next = last;
			last.previous = lastLastItem;
			last.next = first;

			first.previous = last;
		}

		size++;
	}

	private void insertFirst(QIItemListaDuplamenteEncadeada<ITEM> novoItemList)
	{
		first = novoItemList;
		last = novoItemList;
		actual = novoItemList;

		novoItemList.next = novoItemList;
		novoItemList.previous = novoItemList;
	}

	public ITEM next()
	{
		actual = actual.next;

		return actual.item;
	}

	public ITEM previous()
	{
		actual = actual.previous;

		return actual.item;
	}

	private class QIItemListaDuplamenteEncadeada<ITEM_LISTA> implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private ITEM_LISTA item;

		private QIItemListaDuplamenteEncadeada<ITEM_LISTA> next;
		private QIItemListaDuplamenteEncadeada<ITEM_LISTA> previous;

		private QIItemListaDuplamenteEncadeada(ITEM_LISTA item)
		{
			super();
			this.item = item;
		}

	}

}
