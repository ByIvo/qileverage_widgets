package br.com.qileverage.widgets.form.validadores;

import br.com.qileverage.widgets.form.campospersonalizados.QICampoAbstrato;

import com.google.gwt.user.client.ui.TextBoxBase;

public class QIValidacaoCampoTexto implements QIValidarCampo
{

	private static final QIValidacaoCampoTexto INSTANCE;

	static
	{
		INSTANCE = new QIValidacaoCampoTexto();
	}

	public static QIValidacaoCampoTexto getInstance()
	{
		return INSTANCE;
	}

	@Override
	public boolean validarCampo(QICampoAbstrato campoValidacao)
	{
		if (campoValidacao.getCampo() instanceof TextBoxBase)
		{
			TextBoxBase campoValidado = (TextBoxBase) campoValidacao.getCampo();

			if (campoValidado.getText().trim().isEmpty())
			{
				return false;
			}
		}

		return true;
	}

}
