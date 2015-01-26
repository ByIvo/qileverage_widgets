package br.com.qileverage.widgets.form.validadores;

import br.com.qileverage.widgets.form.campospersonalizados.QICampoAbstrato;

import com.google.gwt.user.datepicker.client.DateBox;

public class QIValidacaoData implements QIValidarCampo
{
	private static final QIValidacaoData INSTANCE;

	static
	{
		INSTANCE = new QIValidacaoData();
	}

	public static QIValidacaoData getInstance()
	{
		return INSTANCE;
	}

	@Override
	public boolean validarCampo(QICampoAbstrato campoValidacao)
	{
		if (campoValidacao.getCampo() instanceof DateBox)
		{
			DateBox dbCampo = (DateBox) campoValidacao.getCampo();

			return dbCampo.getValue() != null;
		}

		return false;
	}

}
