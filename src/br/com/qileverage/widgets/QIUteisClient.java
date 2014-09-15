package br.com.qileverage.widgets;

import br.com.qileverage.shared.QIRetornoRPC;

public class QIUteisClient
{
	public static boolean ocorreuErro(QIRetornoRPC<?> retorno)
	{
		if (retorno.getOcorreuErro())
		{
			QIControleMensagens.mostrarMensagemInstantanea(retorno.getMensagemErro());
		}
		else
		{
			return false;
		}

		return true;
	}

}
