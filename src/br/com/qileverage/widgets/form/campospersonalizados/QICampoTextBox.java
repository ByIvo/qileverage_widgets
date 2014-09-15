package br.com.qileverage.widgets.form.campospersonalizados;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.TextBoxBase;

public class QICampoTextBox extends QICampoAbstrato
{
	private TextBoxBase txbCampo;

	public QICampoTextBox()
	{
	}

	public void adicionarCampoTextBox(String strLabelCampo, TextBoxBase campo)
	{
		this.txbCampo = campo;
		super.adicionarCampo(strLabelCampo, campo);
		campo.addKeyUpHandler(new QICampoTextBoxCookieRefresh(this));
	}

	// GETTERS AND SETTERS

	@Override
	public String getValorCampo()
	{
		return txbCampo.getText();
	}

	@Override
	public void setValorCampoByValorCookie(String value)
	{
		txbCampo.setText(value);
	}

	// INNERCLASSES

	public class QICampoTextBoxCookieRefresh implements KeyUpHandler
	{
		QICampoTextBox qiCampoTextBox;

		public QICampoTextBoxCookieRefresh(QICampoTextBox qiCampoTextBox)
		{
			super();
			this.qiCampoTextBox = qiCampoTextBox;
		}

		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			qiCampoTextBox.gravarCookie();
		}

	}
}
