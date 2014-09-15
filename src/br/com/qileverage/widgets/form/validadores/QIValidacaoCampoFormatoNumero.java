package br.com.qileverage.widgets.form.validadores;

import br.com.qileverage.widgets.form.campospersonalizados.QICampoAbstrato;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.TextBoxBase;

public class QIValidacaoCampoFormatoNumero implements QIValidarCampo
{

	private static final QIValidacaoCampoFormatoNumero INSTANCE;

	static
	{
		INSTANCE = new QIValidacaoCampoFormatoNumero();
	}

	public static QIValidacaoCampoFormatoNumero getInstance()
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
				NumberFormat numberFormat = NumberFormat.getFormat(campoValidado.getText());

				numberFormat.format(5.0);
				numberFormat.parse("5.0");
			}
			catch (Exception e)
			{
				return false;
			}
		}

		return true;
	}
}
