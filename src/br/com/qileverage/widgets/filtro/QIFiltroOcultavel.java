package br.com.qileverage.widgets.filtro;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.listapersonalizada.QIUnorderedList;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class QIFiltroOcultavel extends SimplePanel implements QITela
{
	private QIPilhaVertical pilhaEstrutura;
	private QIPilhaHorizontal pilhaEstruturaBotoes;

	private DisclosurePanel dspConteudo;
	private QIUnorderedList listaCamposFiltro;

	private Button btnFiltrar;
	private Button btnLimparCampos;

	public QIFiltroOcultavel()
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
		pilhaEstrutura = new QIPilhaVertical();
		pilhaEstruturaBotoes = new QIPilhaHorizontal();

		dspConteudo = new DisclosurePanel("Filtro");

		listaCamposFiltro = new QIUnorderedList();

		btnLimparCampos = new Button("Limpar");
		btnFiltrar = new Button("Filtrar");
	}

	@Override
	public void setarInformacoes()
	{
		dspConteudo.setAnimationEnabled(true);
		dspConteudo.setOpen(true);

		btnFiltrar.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				filtrarConteudo();
			}
		});

		btnLimparCampos.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				limparCamposFiltro();
			}
		});

		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_filtroocultavel());
		pilhaEstrutura.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_filtroocultavel_estrutura());
		dspConteudo.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_filtroocultavel_disclosurepanel());
		listaCamposFiltro.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_filtroocultavel_disclosurepanel_listaitens());
		pilhaEstruturaBotoes.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_filtroocultavel_painelbotoes());
		btnFiltrar.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_filtroocultavel_painelbotoes_botaofiltro());
		btnLimparCampos.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_filtroocultavel_painelbotoes_botaolimparcampos());
	}

	@Override
	public void montarTela()
	{
		pilhaEstruturaBotoes.adicionarWidget(btnFiltrar);
		pilhaEstruturaBotoes.adicionarWidget(btnLimparCampos);

		pilhaEstrutura.adicionarWidget(listaCamposFiltro);
		pilhaEstrutura.adicionarWidget(pilhaEstruturaBotoes);

		dspConteudo.add(pilhaEstrutura);

		this.add(dspConteudo);
	}

	public void adicionarWidgetFiltro(Widget wid)
	{
		listaCamposFiltro.add(wid);
	}

	public void abrirDisclosureFiltro(boolean abrir)
	{
		dspConteudo.setOpen(abrir);
	}

	public abstract void limparCamposFiltro();

	public abstract void filtrarConteudo();
}
