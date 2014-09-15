package br.com.qileverage.widgets.elementspersonalizados;

import com.google.gwt.dom.client.TagName;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

@TagName(QIHr.HR_ITEM_TAG)
public class QIHr extends Element
{
	public static final String HR_ITEM_TAG = "hr";

	protected QIHr()
	{
		// TODO Auto-generated constructor stub
	}

	public static final QIHr as(Element el)
	{
		assert el.getTagName().equalsIgnoreCase(HR_ITEM_TAG);
		return (QIHr) el;
	}

	public static final QIHr getElement(String id)
	{
		QIHr newDiv = (QIHr) DOM.createElement(QIHr.HR_ITEM_TAG);

		if (id != null)
			newDiv.setId(id);

		return newDiv;
	}
}
