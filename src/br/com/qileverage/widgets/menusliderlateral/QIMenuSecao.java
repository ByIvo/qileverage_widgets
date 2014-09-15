package br.com.qileverage.widgets.menusliderlateral;

import java.util.ArrayList;
import java.util.List;

import br.com.qileverage.widgets.funcoes.CControleFuncoesClient;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

public class QIMenuSecao extends SimplePanel implements HasClickHandlers
{
	private Image imagem;
	private String nomeMenu;
	private List<QIMenuLinkPainelAcesso> listaAcessos;

	public QIMenuSecao(String imagePath, String nomeMenu, ClickHandler clickHandler)
	{
		listaAcessos = new ArrayList<QIMenuLinkPainelAcesso>();
		imagem = new Image(imagePath);
		this.nomeMenu = nomeMenu;
		
		imagem.setAltText(nomeMenu);
		this.add(imagem);
		
		setClickHandler(clickHandler);
		CControleFuncoesClient.sinkClickEvents(this);
	}

	// METODOS

	public void setClickHandler(ClickHandler clickHandler)
	{
		this.addClickHandler(clickHandler);
	}

	public void adicionarAcesso(QIMenuLinkPainelAcesso acesso)
	{
		listaAcessos.add(acesso);
	}

	// GETTERS AND SETTERS

	public String getNomeMenu()
	{
		return nomeMenu;
	}

	public void setNomeMenu(String nome)
	{
		this.nomeMenu = nome;
	}

	public List<QIMenuLinkPainelAcesso> getListaAcessos()
	{
		return listaAcessos;
	}

	public void setListaAcessos(List<QIMenuLinkPainelAcesso> listaAcessos)
	{
		this.listaAcessos = listaAcessos;
	}

	// EQUALS
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof QIMenuSecao)
		{
			QIMenuSecao objConvertido = (QIMenuSecao) obj;

			if (objConvertido.getNomeMenu().equalsIgnoreCase(this.getNomeMenu()))
			{
				return true;
			}
		}

		return false;
	}

	// METODOS SOBRECARREGADOS

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler)
	{
		return addHandler(handler, ClickEvent.getType());
	}
}
