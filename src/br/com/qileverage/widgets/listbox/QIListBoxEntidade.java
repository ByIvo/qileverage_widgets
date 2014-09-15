package br.com.qileverage.widgets.listbox;

import br.com.qileverage.shared.Entidade;

public abstract class QIListBoxEntidade extends QIListBox<Entidade>
{

	@Override
	public String getKey(Entidade entidade)
	{
		return entidade.getId().toString();
	}

}
