package br.com.qileverage.widgets.painelselecaoitens;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.funcoes.CControleFuncoesClient;
import br.com.qileverage.widgets.listapersonalizada.QIUnorderedList;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class QIPainelSelecaoItens extends QIPilhaVertical implements QITela
{
	private Label lblTitulo;

	private QIPilhaHorizontal pilhaHorizontalConteudo;

	private SimplePanel spConteudo;
	private QIUnorderedList listaBotoesFuncoes;

	private Image imgAdd;

	public QIPainelSelecaoItens()
	{

	}

	// ACOES

	public void setTitulo(String strTitulo)
	{
		lblTitulo.setText(CControleFuncoesClient.getNomeCampoComSeparador(strTitulo));
	}

	public void setWidgetConteudo(Widget wid)
	{
		spConteudo.setWidget(wid);
	}

	public void setTitleImagemAdd(String title)
	{
		imgAdd.setTitle(title);
	}

	// METODOS SOBRECARREGADOS

	public void montarItens()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	@Override
	public void instanciarItens()
	{

		spConteudo = new SimplePanel();
		listaBotoesFuncoes = new QIUnorderedList();

		pilhaHorizontalConteudo = new QIPilhaHorizontal();
		lblTitulo = new Label();

		imgAdd = new Image(Resources.INSTANCE.imgAdd());

		imgAdd.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				adicionarNovo();
			}
		});
	}

	@Override
	public void setarInformacoes()
	{
		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_painelselecaoitens());
		listaBotoesFuncoes.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_painelselecaoitens_listabotoesfuncionalidades());

		lblTitulo.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_painelselecaoitens_labelTitulo());
		imgAdd.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_painelselecaoitens_botaoacao());
		spConteudo.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_painelselecaoitens_painelconteudo());
	}

	@Override
	public void montarTela()
	{
		listaBotoesFuncoes.add(imgAdd);

		pilhaHorizontalConteudo.adicionarWidget(spConteudo);
		pilhaHorizontalConteudo.adicionarWidget(listaBotoesFuncoes);

		this.adicionarWidget(lblTitulo);
		this.adicionarWidget(pilhaHorizontalConteudo);
	}

	public abstract void adicionarNovo();
}
