package br.com.qileverage.widgets.menusliderlateral;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIHrWidget;
import br.com.qileverage.widgets.listapersonalizada.QIUnorderedList;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class QIMenuPainelAcessos extends ComplexPanel implements QITela
{
	public static final String PAINELACESSOS_TAG_ITEM = "acessos";

	private QIHrWidget qiHr;
	private Label lblTitulo;
	private QIUnorderedList qiListaAcessos;
	private Image imgOcultar;

	private Element elPainelAcessos;

	public QIMenuPainelAcessos()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	// METODOS

	public final void exibirAcessos(QIMenuSecao secaoMenu)
	{
		assert secaoMenu.getListaAcessos() != null;

		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().menu_acessos_ativaracessos());

		lblTitulo.setText(secaoMenu.getNomeMenu());

		for (QIMenuLinkPainelAcesso acesso : secaoMenu.getListaAcessos())
		{
			qiListaAcessos.add(acesso);
		}
	}

	public final void ocultar()
	{
		this.removeStyleName(Resources.INSTANCE.cssQiLeverageWidgets().menu_acessos_ativaracessos());
	}

	public final void limparAcessos()
	{
		this.getQiListaAcessos().clear();
	}

	// METODOS SOBRECARREGADOS
	@Override
	public final void instanciarItens()
	{
		elPainelAcessos = DOM.createElement(PAINELACESSOS_TAG_ITEM);

		qiHr = new QIHrWidget();
		lblTitulo = new Label();
		qiListaAcessos = new QIUnorderedList();
		imgOcultar = new Image("img/ocultar.png");
	}

	@Override
	public final void setarInformacoes()
	{
		setElement(elPainelAcessos);

		this.getLblTitulo().addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().menu_acessos_labeldescricaosecao());
		this.getQiHr().addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().menu_acessos_linhahorizontal());
		this.getQiListaAcessos().addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().menu_acessos_painelacessos());
		this.getImgOcultar().addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().menu_acessos_imagemocultar());
	}

	@Override
	public final void montarTela()
	{
		this.add(imgOcultar);
		this.add(lblTitulo);
		this.add(qiHr);
		this.add(qiListaAcessos);
	}

	public void setarAcaoBotaoOlho(ClickHandler clickhandler)
	{
		imgOcultar.addClickHandler(clickhandler);
	}

	@Override
	public void add(Widget w)
	{
		super.add(w, this.getElement());
	}

	// GETTERS AND SETTERS

	public QIHrWidget getQiHr()
	{
		return qiHr;
	}

	public void setQiHr(QIHrWidget qiHr)
	{
		this.qiHr = qiHr;
	}

	public Label getLblTitulo()
	{
		return lblTitulo;
	}

	public void setLblTitulo(Label lblTitulo)
	{
		this.lblTitulo = lblTitulo;
	}

	public QIUnorderedList getQiListaAcessos()
	{
		return qiListaAcessos;
	}

	public void setQiListaAcessos(QIUnorderedList qiListaAcessos)
	{
		this.qiListaAcessos = qiListaAcessos;
	}

	public Image getImgOcultar()
	{
		return imgOcultar;
	}

	public void setImgOcultar(Image imgOcultar)
	{
		this.imgOcultar = imgOcultar;
	}

}
