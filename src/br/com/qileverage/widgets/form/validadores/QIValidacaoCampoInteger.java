package br.com.qileverage.widgets.form.validadores;

import br.com.qileverage.widgets.form.campospersonalizados.QICampoAbstrato;

import com.google.gwt.user.client.ui.TextBoxBase;

public class QIValidacaoCampoInteger implements QIValidarCampo
{

	private static final QIValidacaoCampoInteger INSTANCE;

	static
	{
		INSTANCE = new QIValidacaoCampoInteger();
	}

	public static QIValidacaoCampoInteger getInstance()
	{
		return INSTANCE;
	}

	@Override
	public boolean validarCampo(QICampoAbstrato campoValidacao)
	{
		if (campoValidacao.getCampo() instanceof TextBoxBase)
		{
			TextBoxBase campoValidado = (TextBoxBase) campoValidacao.getCampo();

			try
			{
				Integer.parseInt(campoValidado.getText());
			}
			catch (Exception e)
			{
				return false;
			}
		}

		return true;
	}
}
