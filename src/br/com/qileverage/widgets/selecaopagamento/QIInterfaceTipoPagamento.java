package br.com.qileverage.widgets.selecaopagamento;

import com.google.gwt.resources.client.ImageResource;

public interface QIInterfaceTipoPagamento
{
	public abstract Integer getIdentificadorPagamento();

	public abstract String getNomePagamento();

	public abstract ImageResource getImagemPagamento();
	
	public abstract ImageResource getImagemPagamentoThumb();
}
