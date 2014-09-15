package br.com.qileverage.widgets;

import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class QIControlePopupBloqueio extends DialogBox implements QITela
{
	public static final QIControlePopupBloqueio INSTANCE;

	static
	{
		INSTANCE = new QIControlePopupBloqueio();
		INSTANCE.hide();
	}

	private QIPilhaVertical pilha;
	private Image imgLoading;
	private Label lblLoading;
	private SimplePanel spWidgetLoading;

	private QIControlePopupBloqueio()
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
		pilha = new QIPilhaVertical();

		lblLoading = new Label();
		imgLoading = new Image();
		spWidgetLoading = new SimplePanel();

	}

	@Override
	public void setarInformacoes()
	{
		lblLoading.setText("Carregando...");
		imgLoading.setResource(Resources.INSTANCE.imgLoading());

		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popuptravarsistema());
		pilha.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popuptravarsistema_painelconteudo());
		lblLoading.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popuptravarsistema_labelloading());
		imgLoading.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popuptravarsistema_widgetloading());
		spWidgetLoading.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popuptravarsistema_painelwidgetloading());
		this.setGlassStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_popuptravarsistema_espelho());

		this.setModal(true);
		this.setAnimationEnabled(false);
		this.setAutoHideEnabled(false);
		this.setAutoHideOnHistoryEventsEnabled(false);
		this.setGlassEnabled(true);
	}

	@Override
	public void montarTela()
	{
		spWidgetLoading.add(imgLoading);

		pilha.adicionarWidget(lblLoading);
		pilha.adicionarWidget(spWidgetLoading);

		this.setWidget(pilha);
	}

	@Override
	protected void onLoad()
	{
		super.onLoad();
		this.center();
	}

	public static void mostrarBloqueio(boolean mostrar)
	{
		if (mostrar)
		{
			INSTANCE.show();
		}
		else
		{
			INSTANCE.hide();
		}
	}
}
