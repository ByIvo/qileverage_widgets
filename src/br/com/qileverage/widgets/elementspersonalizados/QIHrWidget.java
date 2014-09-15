package br.com.qileverage.widgets.elementspersonalizados;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class QIHrWidget extends Widget
{
	private QIHr hrElement;

	public QIHrWidget()
	{
		hrElement = (QIHr) DOM.createElement(QIHr.HR_ITEM_TAG);
		setElement(hrElement);
	}
}
