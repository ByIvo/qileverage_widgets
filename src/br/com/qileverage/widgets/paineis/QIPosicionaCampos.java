package br.com.qileverage.widgets.paineis;

import java.util.HashMap;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.funcoes.CControleFuncoesClient;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class QIPosicionaCampos extends Widget implements QITela
{

	private HashMap<String, Widget> listaItensAdicionados;

	public QIPosicionaCampos()
	{
		montarInformacoes();
	}

	private void montarInformacoes()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	@Override
	public void instanciarItens()
	{
		listaItensAdicionados = new HashMap<String, Widget>();
	}

	@Override
	public void setarInformacoes()
	{
	}

	@Override
	public void montarTela()
	{
	}

	public void adicionarNovoCampo(String label, Widget w)
	{
		if (listaItensAdicionados.containsValue(label))
		{
			return;
		}
		listaItensAdicionados.put(label, w);

		Label lblLabel = new Label(CControleFuncoesClient.getNomeCampoComSeparador(label));

		adicionarProximoCampo(lblLabel, w);

		if (!getEstiloLabel().isEmpty())
		{
			lblLabel.addStyleName(getEstiloLabel());
		}

		if (!getEstiloWidget().isEmpty())
		{
			w.addStyleName(getEstiloWidget());
		}
	}

	protected abstract void adicionarProximoCampo(Widget label, Widget w);

	protected abstract void setElementMaster();

	public abstract String getEstiloLabel();

	public abstract String getEstiloWidget();
}
