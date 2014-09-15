package br.com.qileverage.widgets.elementspersonalizados;

import com.google.gwt.dom.client.TagName;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

@TagName(QIDiv.DIV_ITEM_TAG)
public class QIDiv extends Element
{
	public static final String DIV_ITEM_TAG = "div";

	protected QIDiv()
	{
		// TODO Auto-generated constructor stub
	}

	public static final QIDiv as(Element el)
	{
		assert el.getTagName().equalsIgnoreCase(DIV_ITEM_TAG);
		return (QIDiv) el;
	}

	public static final QIDiv getElement(String id)
	{
		QIDiv newDiv = (QIDiv) DOM.createElement(QIDiv.DIV_ITEM_TAG);

		if (id != null)
			newDiv.setId(id);

		return newDiv;
	}
}
