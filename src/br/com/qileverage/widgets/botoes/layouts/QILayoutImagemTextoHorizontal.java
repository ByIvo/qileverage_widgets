package br.com.qileverage.widgets.botoes.layouts;

import br.com.qileverage.widgets.QITela;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class QILayoutImagemTextoHorizontal extends QILayoutBotaoHorizontal implements QITela
{
	private DIRECAO_LAYOUT direcao;

	private Label lblTextoBotao;
	private Image imgBotao;

	public QILayoutImagemTextoHorizontal(DIRECAO_LAYOUT direcao)
	{
		this.direcao = direcao;
		montarEstrutura();
	}

	private void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	// FUNCOES

	public void setTextoBotao(String strTexto)
	{
		lblTextoBotao.setText(strTexto);
	}

	public void setImagem(ImageResource imgResource)
	{
		String classes = imgBotao.getStyleName();
		imgBotao.setResource(imgResource);

		imgBotao.addStyleName(classes);
	}

	// METODOS SOBRECARREGADOS

	@Override
	public void instanciarItens()
	{
		imgBotao = new Image();
		lblTextoBotao = new Label();
	}

	@Override
	public void setarInformacoes()
	{
	}

	@Override
	public void montarTela()
	{
		switch (direcao)
		{
			case LTR:
				montarLtr();
				break;

			default:
				montarRtl();
				break;
		}
	}

	public void montarRtl()
	{
		this.adicionarWidget(imgBotao);
		this.adicionarWidget(lblTextoBotao);
	}

	public void montarLtr()
	{
		this.adicionarWidget(lblTextoBotao);
		this.adicionarWidget(imgBotao);
	}

	// GETTERS AND SETTERS

	public Label getLblTextoBotao()
	{
		return lblTextoBotao;
	}

	public void setLblTextoBotao(Label lblTextoBotao)
	{
		this.lblTextoBotao = lblTextoBotao;
	}

	public Image getImgBotao()
	{
		return imgBotao;
	}

	public void setImgBotao(Image imgBotao)
	{
		this.imgBotao = imgBotao;
	}

	// CLASS DIRECAO

	public enum DIRECAO_LAYOUT
	{
		LTR, RTL;
	}

}
