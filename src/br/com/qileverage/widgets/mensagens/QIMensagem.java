package br.com.qileverage.widgets.mensagens;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.listapersonalizada.QIUnorderedList;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class QIMensagem extends DialogBox implements QITela
{
	private Label lblTextoMensagem;
	private Image imgIconeMensagem;
	private SimplePanel spBotoes;

	private QIPilhaVertical pilhaVerticalEstrutura;
	private QIUnorderedList listaHorizontalMensagem;

	public QIMensagem()
	{

	}

	protected void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	public void setMensagem(String mensagem)
	{
		lblTextoMensagem.setText(mensagem);
	}

	public void setIcone(ImageResource resource)
	{
		imgIconeMensagem.setResource(resource);
		imgIconeMensagem.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_imagem());
	}

	public void adicionarBotoes(Widget painelContemBotoes)
	{
		spBotoes.clear();
		spBotoes.add(painelContemBotoes);
	}

	@Override
	public void instanciarItens()
	{
		listaHorizontalMensagem = new QIUnorderedList();
		pilhaVerticalEstrutura = new QIPilhaVertical();

		lblTextoMensagem = new Label();
		imgIconeMensagem = new Image();
		spBotoes = new SimplePanel();
	}

	@Override
	public void setarInformacoes()
	{
		pilhaVerticalEstrutura.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens());
		listaHorizontalMensagem.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_displaymensagem());
		spBotoes.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_painelbotoes());
		lblTextoMensagem.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_textomensagem());
	}

	@Override
	public void montarTela()
	{
		listaHorizontalMensagem.add(imgIconeMensagem, Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_celulaimagem());
		listaHorizontalMensagem.add(lblTextoMensagem, Resources.INSTANCE.cssQiLeverageWidgets().widget_mensagens_celulatextomensagem());

		pilhaVerticalEstrutura.adicionarWidget(listaHorizontalMensagem);
		pilhaVerticalEstrutura.adicionarWidget(spBotoes);

		this.add(pilhaVerticalEstrutura);
	}

	@Override
	protected void onLoad()
	{
		super.onLoad();
		this.center();
	}
}
