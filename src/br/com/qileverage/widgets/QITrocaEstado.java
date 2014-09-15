package br.com.qileverage.widgets;

import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public abstract class QITrocaEstado extends PushButton implements QITela
{

	private Estado estado;
	private String titleAberto;
	private String titleFechado;

	private ClickHandler handlerChangeState;

	public QITrocaEstado()
	{
		montarEstrutura();
	}

	private void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	@Override
	public void instanciarItens()
	{
		titleAberto = "";
		titleFechado = "";

		handlerChangeState = new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				alternarEstado();
			}
		};
	}

	public void alternarEstado()
	{
		if (this.estado == Estado.ABERTO)
		{
			fechar();
		}
		else
		{
			abrir();
		}
	}

	@Override
	public void setarInformacoes()
	{
		abrir();

		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_trocaestado());

		this.addClickHandler(handlerChangeState);
	}

	@Override
	public void montarTela()
	{

	}

	public void abrir()
	{
		estado = Estado.ABERTO;

		alterarImagem(Resources.INSTANCE.imgAberto(), titleAberto);
		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_trocaestado_aberto());
		abriu();
	}

	public void fechar()
	{
		estado = Estado.FECHADO;

		alterarImagem(Resources.INSTANCE.imgFechado(), titleFechado);
		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_trocaestado_fechado());
		fechou();
	}

	@SuppressWarnings("serial")
	private void alterarImagem(ImageResource resource, String title)
	{
		final Image img = new Image(resource);

		img.setTitle(title);

		this.setHTML(new SafeHtml()
		{

			@Override
			public String asString()
			{
				return img.toString();
			}
		});
	}

	public String getTitleAberto()
	{
		return titleAberto;
	}

	public void setTitleAberto(String titleAberto)
	{
		this.titleAberto = titleAberto;
	}

	public String getTitleFechado()
	{
		return titleFechado;
	}

	public void setTitleFechado(String titleFechado)
	{
		this.titleFechado = titleFechado;
	}

	public boolean isAberto()
	{
		return estado == Estado.ABERTO;
	}

	public abstract void abriu();

	public abstract void fechou();

	private enum Estado
	{
		ABERTO, FECHADO;
	}
}
