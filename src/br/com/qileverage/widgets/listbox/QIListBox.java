package br.com.qileverage.widgets.listbox;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.ui.ListBox;

public abstract class QIListBox<ENTIDADE> extends ListBox
{
	private HashMap<String, ENTIDADE> mapEntidades;

	public QIListBox()
	{
		mapEntidades = new HashMap<String, ENTIDADE>();
	}

	public void limpar()
	{
		mapEntidades.clear();
		this.clear();
	}

	public void adicionarListaEntidade(List<ENTIDADE> listaEntidade)
	{
		for (ENTIDADE entidade : listaEntidade)
		{
			this.adicionarEntidade(entidade);
		}
	}

	public void adicionarListaEntidade(ENTIDADE... entidades)
	{
		adicionarListaEntidade((List<ENTIDADE>) Arrays.asList(entidades));
	}

	public void adicionarEntidade(ENTIDADE entidade)
	{
		String key = this.getKey(entidade);
		mapEntidades.put(key, entidade);

		this.addItem(getTextoExibicao(entidade), key);
	}

	public void selecionarEntidade(ENTIDADE entidade)
	{
		int posicaoEntidade = this.getIndexEntidade(entidade);

		if (posicaoEntidade == -1)
		{
			this.resetSelecao();
		}
		else
		{
			this.setSelectedIndex(posicaoEntidade);
		}
	}

	public int getIndexEntidade(ENTIDADE entidade)
	{
		String key = this.getKey(entidade);

		for (int i = 0; i < this.getItemCount(); i++)
		{
			if (this.getValue(i).equalsIgnoreCase(key))
			{
				return i;
			}
		}

		return -1;
	}

	public void resetSelecao()
	{
		if (this.getItemCount() != 0)
		{
			this.setSelectedIndex(0);
		}
	}

	public boolean removerEntidade(ENTIDADE entidade)
	{
		String key = this.getKey(entidade);

		if (mapEntidades.containsKey(key))
		{
			mapEntidades.remove(key);
			this.removeItem(this.getIndexEntidade(entidade));

			return true;
		}

		return false;
	}

	public ENTIDADE getEntidadeSelecionada()
	{
		if (this.getItemCount() == 0)
		{
			return null;
		}

		int posicaoSelecionada = this.getSelectedIndex();
		String key = this.getValue(posicaoSelecionada);

		return mapEntidades.get(key);
	}

	public boolean isEmpty()
	{
		return this.getItemCount() == 0;
	}

	public abstract String getKey(ENTIDADE entidade);

	public abstract String getTextoExibicao(ENTIDADE entidade);
}
