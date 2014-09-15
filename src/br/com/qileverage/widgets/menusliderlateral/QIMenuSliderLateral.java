package br.com.qileverage.widgets.menusliderlateral;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.listapersonalizada.QIUnorderedList;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public abstract class QIMenuSliderLateral extends ComplexPanel implements QITela
{
	public static final String MENUMAIN_TAG_ITEM = "menu";
	public static final String MENUSECOES_TAG_ITEM = "secoes";

	private Element elMenuMain;
	private Element elMenuSecoes;

	private QIMenuPainelAcessos qiMenuPainelAcessos;
	private QIUnorderedList qiListaItensMenu;

	private QIMenuSecao menuSecaoSelecionada;

	private QIMenuEstado menuEstado;
	private ClickHandler clickHandlerOcultarMenu;

	private Image btnSair;

	public QIMenuSliderLateral()
	{
		super();

		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	// METODOS

	public void adicionarSecao(QIMenuSecao secaoMenu)
	{
		qiListaItensMenu.add(secaoMenu);
	}

	public void selecionarSecao(QIMenuSecao menuSecao)
	{
		assert menuSecao != null;

		if (!menuSecao.equals(menuSecaoSelecionada))
		{
			qiMenuPainelAcessos.limparAcessos();

			if (menuSecaoSelecionada != null)
			{
				menuSecaoSelecionada.removeStyleName(Resources.INSTANCE.cssQiLeverageWidgets().menu_secaomenuselecionado());
			}

			menuSecao.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().menu_secaomenuselecionado());
			qiMenuPainelAcessos.exibirAcessos(menuSecao);

			menuSecaoSelecionada = menuSecao;
			menuEstado = QIMenuEstado.ABERTO;
		}
		else
		{
			ocultarMenu();
		}
	}

	public void ocultarMenu()
	{
		// assert menuEstado == QIMenuEstado.ABERTO;

		if (menuSecaoSelecionada != null)
		{
			menuSecaoSelecionada.removeStyleName(Resources.INSTANCE.cssQiLeverageWidgets().menu_secaomenuselecionado());
		}
		qiMenuPainelAcessos.ocultar();

		menuSecaoSelecionada = null;
		menuEstado = QIMenuEstado.OCULTO;
	}

	// METODOS SOBRECARREGADOS

	@Override
	public void instanciarItens()
	{
		elMenuMain = DOM.createElement(MENUMAIN_TAG_ITEM);
		elMenuSecoes = DOM.createElement(MENUSECOES_TAG_ITEM);

		qiListaItensMenu = new QIUnorderedList();

		btnSair = new Image("img/exit.png");

		qiMenuPainelAcessos = new QIMenuPainelAcessos();

		menuEstado = QIMenuEstado.OCULTO;

		clickHandlerOcultarMenu = new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				QIMenuSliderLateral.this.ocultarMenu();
			}
		};

	}

	@Override
	public void setarInformacoes()
	{
		setElement(elMenuMain);

		qiMenuPainelAcessos.setarAcaoBotaoOlho(clickHandlerOcultarMenu);

		btnSair.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().botaosair());

		btnSair.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				QIMenuSliderLateral.this.sair();
			}
		});
	}

	@Override
	public void montarTela()
	{
		add(qiListaItensMenu, elMenuSecoes);
		add(qiMenuPainelAcessos);
		add(btnSair);
	}

	@Override
	public void add(Widget w)
	{
		super.add(w, elMenuMain);
	}

	@Override
	public void add(Widget w, Element elContainer)
	{
		DOM.appendChild(elMenuMain, elContainer);

		super.add(w, elContainer);
	}

	// GETTERS AND SETTERS

	public QIMenuSecao getMenuSecaoSelecionada()
	{
		return menuSecaoSelecionada;
	}

	protected void setMenuSecaoSelecionada(QIMenuSecao menuSecaoSelecionada)
	{
		this.menuSecaoSelecionada = menuSecaoSelecionada;
	}

	public QIMenuEstado getMenuEstado()
	{
		return menuEstado;
	}

	public void setMenuEstado(QIMenuEstado menuEstado)
	{
		this.menuEstado = menuEstado;
	}

	public abstract void sair();
}
