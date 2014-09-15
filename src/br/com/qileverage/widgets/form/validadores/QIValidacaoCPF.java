package br.com.qileverage.widgets.form.validadores;

import br.com.qileverage.shared.QIUteis;
import br.com.qileverage.widgets.form.campospersonalizados.QICampoAbstrato;

import com.google.gwt.user.client.ui.TextBoxBase;

public class QIValidacaoCPF implements QIValidarCampo
{
	private String mascaraCPF;

	public QIValidacaoCPF(String mascaraCPF)
	{
		super();
		this.mascaraCPF = mascaraCPF;
	}

	public String getMascaraCPF()
	{
		return mascaraCPF;
	}

	public void setMascaraCPF(String mascaraCPF)
	{
		this.mascaraCPF = mascaraCPF;
	}

	@Override
	public boolean validarCampo(QICampoAbstrato campoValidacao)
	{
		if (campoValidacao.getCampo() instanceof TextBoxBase)
		{
			TextBoxBase campoValidado = (TextBoxBase) campoValidacao.getCampo();

			String strSemFormatacao = QIUteis.getStringSemMascara(campoValidado.getText(), mascaraCPF);

			return QIUteis.validarCPF(strSemFormatacao);
		}

		return true;
	}
}
