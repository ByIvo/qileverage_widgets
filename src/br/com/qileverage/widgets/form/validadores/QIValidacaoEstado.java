package br.com.qileverage.widgets.form.validadores;

import br.com.qileverage.widgets.form.campospersonalizados.QICampoAbstrato;
import br.com.qileverage.widgets.localides.QILocalidadesBrasileiras;

import com.google.gwt.user.client.ui.ListBox;

public class QIValidacaoEstado implements QIValidarCampo
{

	private static final QIValidacaoEstado INSTANCE;

	static
	{
		INSTANCE = new QIValidacaoEstado();
	}

	public static QIValidacaoEstado getInstance()
	{
		return INSTANCE;
	}

	@Override
	public boolean validarCampo(QICampoAbstrato campoValidacao)
	{
		if (campoValidacao.getCampo() instanceof ListBox)
		{
			ListBox campoValidado = (ListBox) campoValidacao.getCampo();

			String value = campoValidado.getValue(campoValidado.getSelectedIndex());
			
			if (value.isEmpty() || QILocalidadesBrasileiras.getArrayEstados(value) == null)
			{
				return false;
			}

			return true;
		}

		return true;
	}
}
