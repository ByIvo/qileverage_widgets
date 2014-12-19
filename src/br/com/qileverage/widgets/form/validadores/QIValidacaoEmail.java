package br.com.qileverage.widgets.form.validadores;

import br.com.qileverage.widgets.form.campospersonalizados.QICampoAbstrato;

public class QIValidacaoEmail implements QIValidarCampo
{
	private static final QIValidacaoEmail INSTANCE;
	
	private String formatoEmail = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
	
	static
	{
		INSTANCE = new QIValidacaoEmail();
	}

	public static QIValidacaoEmail getInstance()
	{
		return INSTANCE;
	}

	@Override
	public boolean validarCampo(QICampoAbstrato campoValidacao)
	{
		String textoCampoValidado = campoValidacao.getValorCampo();
		
		return textoCampoValidado.matches(formatoEmail) || textoCampoValidado.isEmpty();
	}

}
