package br.com.qileverage.widgets.listapersonalizada;

import com.google.gwt.dom.client.TagName;
import com.google.gwt.user.client.Element;

@TagName(QIListItemElement.LIST_ITEM_TAG)
public class QIListItemElement extends Element
{
	protected QIListItemElement()
	{
		// TODO Auto-generated constructor stub
	}
	
	public static final String LIST_ITEM_TAG = "li";

	public static final QIListItemElement as(Element el)
	{
		assert el.getTagName().equalsIgnoreCase(LIST_ITEM_TAG);
		return (QIListItemElement) el;
	}
}
