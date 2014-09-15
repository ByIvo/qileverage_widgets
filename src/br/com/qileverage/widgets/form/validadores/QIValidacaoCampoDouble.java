package br.com.qileverage.widgets.form.validadores;

import br.com.qileverage.widgets.form.campospersonalizados.QICampoAbstrato;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.TextBoxBase;

public class QIValidacaoCampoDouble implements QIValidarCampo
{

	private static final QIValidacaoCampoDouble INSTANCE;

	static
	{
		INSTANCE = new QIValidacaoCampoDouble();
	}

	public static QIValidacaoCampoDouble getInstance()
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
				NumberFormat.getFormat("0.00").parse(campoValidado.getText().trim());
			}
			catch (Exception e)
			{
				return false;
			}
		}

		return true;
	}
}
