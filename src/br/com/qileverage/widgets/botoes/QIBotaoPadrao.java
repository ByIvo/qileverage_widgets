package br.com.qileverage.widgets.botoes;

import br.com.qileverage.widgets.botoes.layouts.QILayoutImagemTextoHorizontal;
import br.com.qileverage.widgets.botoes.layouts.QILayoutImagemTextoHorizontal.DIRECAO_LAYOUT;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.resources.client.ImageResource;

public class QIBotaoPadrao extends QIBotaoPersonalizado
{

	private QILayoutImagemTextoHorizontal qiLayout;

	public QIBotaoPadrao(DIRECAO_LAYOUT direcao)
	{
		qiLayout = new QILayoutImagemTextoHorizontal(direcao);

		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_botoes_botaopadrao());
		qiLayout.getImgBotao().addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_botoes_botaopadrao_layouthorizontal_imagembotao());
		qiLayout.getLblTextoBotao().addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_botoes_botaopadrao_layouthorizontal_labeldescricao());
		qiLayout.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_botoes_botaopadrao_layouthorizontal());
	}

	public QIBotaoPadrao(DIRECAO_LAYOUT direcao, ImageResource resource, String strTexto)
	{
		qiLayout = new QILayoutImagemTextoHorizontal(direcao);

		this.setLayout(qiLayout);

		this.setImagemBotao(resource);
		this.setTextoDescricao(strTexto);

		this.aplicarLayout();
	}

	public void aplicarLayout()
	{
		super.setLayout(qiLayout);
	}

	public void setImagemBotao(ImageResource resource)
	{
		qiLayout.setImagem(resource);
	}

	public void setTextoDescricao(String strTexto)
	{
		qiLayout.setTextoBotao(strTexto);
	}

}
