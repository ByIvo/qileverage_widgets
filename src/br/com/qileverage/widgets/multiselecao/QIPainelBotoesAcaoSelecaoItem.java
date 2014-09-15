package br.com.qileverage.widgets.multiselecao;

import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public abstract class QIPainelBotoesAcaoSelecaoItem extends QIPilhaVertical
{

	public QIPainelBotoesAcaoSelecaoItem()
	{
		montarTela();
	}

	private void montarTela()
	{
		ClickHandler doubleRightHandler = new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				passarTodos();
			}
		};
		ClickHandler rightHandler = new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				passarSelecionados();
			}
		};
		ClickHandler leftHandler = new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				removerSelecionados();
			}
		};
		ClickHandler doubleLeftHandler = new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				removerTodos();
			}
		};

		adicionarBotao(Resources.INSTANCE.imgDoubleRight(), doubleRightHandler);
		adicionarBotao(Resources.INSTANCE.imgRight(), rightHandler);
		adicionarBotao(Resources.INSTANCE.imgLeft(), leftHandler);
		adicionarBotao(Resources.INSTANCE.imgDoubleLeft(), doubleLeftHandler);
	}

	public void adicionarBotao(ImageResource imgResource, ClickHandler handler, String... estilos)
	{
		PushButton btn = new PushButton(new Image(imgResource));

		btn.addClickHandler(handler);
		btn.setSize("20px", "20px");
		btn.getElement().getStyle().setProperty("margin", "100% auto");

		for (String estilo : estilos)
		{
			btn.addStyleName(estilo);
		}

		this.adicionarWidget(btn);
	}
	
	abstract void passarTodos();

	abstract void passarSelecionados();

	abstract void removerSelecionados();

	abstract void removerTodos();
}
