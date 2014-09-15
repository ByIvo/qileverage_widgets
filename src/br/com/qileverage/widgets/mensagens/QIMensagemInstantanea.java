package br.com.qileverage.widgets.mensagens;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;

public class QIMensagemInstantanea extends QIPilhaHorizontal implements QITela
{
	private boolean podeFechar;
	private Timer timer;

	public QIMensagemInstantanea()
	{
		montarEstrutura();
	}

	protected void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	@Override
	public void instanciarItens()
	{
		podeFechar = true;

		timer = new Timer()
		{

			@Override
			public void run()
			{
				if (podeFechar)
				{
					QIMensagemInstantanea.this.removeFromParent();
				}
			}
		};
	}

	@Override
	public void setarInformacoes()
	{
		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea());

		prepararEventos();
	}

	@Override
	public void montarTela()
	{
		RootPanel.get().add(this);
		fecharUsingFade();
	}

	public void fecharImediatamente()
	{
		this.removeFromParent();
	}

	public void fecharUsingFade()
	{
		podeFechar = true;
		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea_animacao());
		timer.schedule(3800);
	}

	public void cancelarFechamentoFade()
	{
		podeFechar = false;
		this.removeStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_mensageminstantanea_animacao());
		timer.cancel();
	}

	private void prepararEventos()
	{
		DOM.sinkEvents(this.getElement(), Event.ONMOUSEOUT | Event.ONMOUSEOVER);

		this.addHandler(new MouseOverHandler()
		{

			@Override
			public void onMouseOver(MouseOverEvent event)
			{
				QIMensagemInstantanea.this.cancelarFechamentoFade();
			}
		}, MouseOverEvent.getType());

		this.addHandler(new MouseOutHandler()
		{

			@Override
			public void onMouseOut(MouseOutEvent event)
			{
				QIMensagemInstantanea.this.fecharUsingFade();
			}
		}, MouseOutEvent.getType());

	}
}
