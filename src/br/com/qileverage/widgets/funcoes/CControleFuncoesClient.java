package br.com.qileverage.widgets.funcoes;

import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;

public class CControleFuncoesClient
{

	public static String getNomeCampoComSeparador(String texto)
	{
		StringBuilder stbTitulo = new StringBuilder();
		stbTitulo.append(texto);
		stbTitulo.append(Resources.CONSTANTES.separadorcampo());

		return stbTitulo.toString();
	}

	public static void sinkClickEvents(Widget w)
	{
		w.sinkEvents(Event.ONMOUSEOVER | Event.ONCLICK | Event.ONDBLCLICK | Event.MOUSEEVENTS | Event.ONLOAD | Event.ONERROR | Event.ONMOUSEWHEEL | Event.TOUCHEVENTS | Event.GESTUREEVENTS);
	}

	public static String getLimitedLabel(String label, int maxCaracters)
	{
		if (label.length() > maxCaracters)
		{
			label = label.substring(0, maxCaracters - 2);
			label = label.concat("...");
		}

		return label;
	}

}
