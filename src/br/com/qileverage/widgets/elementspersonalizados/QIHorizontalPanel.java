package br.com.qileverage.widgets.elementspersonalizados;

import java.util.HashMap;

import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class QIHorizontalPanel extends QIPilhaHorizontal
{

	private HashMap<Widget, SimplePanel> mapItensAdicionados;

	public QIHorizontalPanel()
	{
		mapItensAdicionados = new HashMap<Widget, SimplePanel>();
		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_horizontalpanel());
	}

	@Override
	public void adicionarWidget(Widget w)
	{
		SimplePanel elNewDiv = new SimplePanel();

		DOM.appendChild(elNewDiv.getElement(), w.getElement());

		mapItensAdicionados.put(w, elNewDiv);
		super.add(elNewDiv, this.getElement());

		elNewDiv.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_horizontalpanel_celulas());
		w.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_horizontalpanel_itemadicionado());
	}

	public void adicionarEstiloCelula(Widget w, String estilo)
	{
		assert w != null;

		if (mapItensAdicionados.containsKey(w))
		{
			mapItensAdicionados.get(w).addStyleName(estilo);
		}
	}

	public void removerWidgetCelula(Widget w)
	{
		assert w != null;

		if (mapItensAdicionados.containsKey(w))
		{
			mapItensAdicionados.get(w).removeFromParent();
			mapItensAdicionados.remove(w);
		}
	}

	public void substituirWidget(Widget wAnterior, Widget novoWidget)
	{
		assert wAnterior != null && novoWidget != null;

		if (mapItensAdicionados.containsKey(wAnterior))
		{
			SimplePanel sp = mapItensAdicionados.get(wAnterior);

			sp.clear();
			sp.add(novoWidget);
			mapItensAdicionados.remove(wAnterior);
			mapItensAdicionados.put(novoWidget, sp);
		}
	}
}
