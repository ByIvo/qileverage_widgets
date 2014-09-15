package br.com.qileverage.widgets.form.validadores;

import br.com.qileverage.widgets.form.campospersonalizados.QICampoAbstrato;

public class QIValidacaoHora implements QIValidarCampo
{
	private static final QIValidacaoHora INSTANCE;

	static
	{
		INSTANCE = new QIValidacaoHora();
	}

	public static QIValidacaoHora getInstance()
	{
		return INSTANCE;
	}

	@Override
	public boolean validarCampo(QICampoAbstrato campoValidacao)
	{
		String textoCampoValidado = campoValidacao.getValorCampo();

		if (textoCampoValidado.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$"))
		{
			return true;
		}

		return false;
	}

}
