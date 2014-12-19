package br.com.qileverage.widgets.paineis;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.SimplePanel;

public class QIPainelCor extends SimplePanel
{
	private String color;

	public QIPainelCor(String color)
	{
		this.color = color;
		alterarCorPainel();
		setDefaultSizePainel();
	}

	public QIPainelCor()
	{
		setDefaultSizePainel();
	}

	public String getColor(boolean readyColor)
	{
		if (readyColor)
		{
			return "#" + color;
		}

		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
		alterarCorPainel();
	}

	private void setDefaultSizePainel()
	{
		setSizePainel(24, 24);
	}

	private void setSizePainel(double width, double height)
	{
		this.getElement().getStyle().setWidth(width, Unit.PX);
		this.getElement().getStyle().setHeight(height, Unit.PX);
	}

	private void alterarCorPainel()
	{
		this.getElement().getStyle().setBackgroundColor(getColor(true));
	}

	public String gerarNovaCor()
	{
		StringBuilder newColor = new StringBuilder();

		// RED
		newColor.append(Integer.toHexString(getNew256Color()));
		// GREEN
		newColor.append(Integer.toHexString(getNew256Color()));
		// BLUE
		newColor.append(Integer.toHexString(getNew256Color()));

		this.setColor(newColor.toString());
		return getColor(true);
	}

	private int getNew256Color()
	{
		return (int) (Math.random() * 256);
	}

}
