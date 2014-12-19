package br.com.qileverage.widgets.form.campospersonalizados;

import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.paineis.QIPainelCor;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class QICampoColorPicker extends QICampoAbstrato
{
	
	private QIPainelCor painelCor;
	private PushButton generateNewColor;
	
	public QICampoColorPicker(String label, QIPainelCor painelCor)
	{
		this.painelCor = painelCor;
		generateNewColor = new PushButton(new Image(Resources.INSTANCE.imgRefresh()));
		
		botaoGenerateNewColor();
		montarCampo(label);
		
		generateNewColor.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_campotrocacor_botaotroca());
		painelCor.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_paineltrocacor());
	}
	
	private void montarCampo(String label)
	{
		QIPilhaHorizontal pilhaEstrutura = new QIPilhaHorizontal();
		
		pilhaEstrutura.adicionarWidget(painelCor);
		pilhaEstrutura.adicionarWidget(generateNewColor);
		
		this.adicionarCampo(label, pilhaEstrutura);
	}
	
	private void botaoGenerateNewColor()
	{
		generateNewColor.addClickHandler(new ClickHandler()
		{
			
			@Override
			public void onClick(ClickEvent event)
			{
				painelCor.gerarNovaCor();
				QICampoColorPicker.this.gravarCookie();
			}
		});
	}
	
	@Override
	public String getValorCampo()
	{
		return painelCor.getColor(false);
	}
	
	@Override
	public void setValorCampoByValorCookie(String value)
	{
		if (value == null)
		{
			return;
		}
		
		if (value.isEmpty())
		{
			return;
		}
		painelCor.setColor(value);
	}
}
