package br.com.qileverage.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class QIRetornoRPC<RETORNO> implements Serializable, IsSerializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RETORNO objetoRetorno;
	private Boolean ocorreuErro;
	private String mensagemErro;

	public QIRetornoRPC()
	{
		objetoRetorno = null;
		ocorreuErro = false;
		mensagemErro = "";
	}

	public RETORNO getObjetoRetorno()
	{
		return objetoRetorno;
	}

	public void setObjetoRetorno(RETORNO objetoRetorno)
	{
		this.objetoRetorno = objetoRetorno;
	}

	public Boolean getOcorreuErro()
	{
		return ocorreuErro;
	}

	public void setOcorreuErro(Boolean ocorreuErro)
	{
		this.ocorreuErro = ocorreuErro;
	}

	public String getMensagemErro()
	{
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro)
	{
		setOcorreuErro(true);
		this.mensagemErro = mensagemErro;
	}

}
