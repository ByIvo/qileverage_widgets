package br.com.qileverage.widgets.form;

import java.util.Collection;
import java.util.HashMap;

import br.com.qileverage.widgets.form.campospersonalizados.QICampo;
import br.com.qileverage.widgets.form.campospersonalizados.QICampoAbstrato;
import br.com.qileverage.widgets.form.campospersonalizados.QICampoColorPicker;
import br.com.qileverage.widgets.form.campospersonalizados.QICampoEditavel;
import br.com.qileverage.widgets.form.campospersonalizados.QICampoTextBox;
import br.com.qileverage.widgets.form.eventos.EnvioFormularioEvent;
import br.com.qileverage.widgets.form.eventos.EnvioFormularioHandler;
import br.com.qileverage.widgets.form.validadores.QIValidarCampo;
import br.com.qileverage.widgets.listapersonalizada.QIUnorderedList;
import br.com.qileverage.widgets.paineis.QIPainelCor;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

public class QIForm extends QIUnorderedList
{
	private HashMap<String, QICampoAbstrato> mapCamposAdicionados;
	private String preeCookieName;

	private KeyPressHandler handlerEnterPress;

	public QIForm(String preeCookieName)
	{
		this.preeCookieName = preeCookieName;
		instanciaPadrao();
	}

	private void instanciaPadrao()
	{
		mapCamposAdicionados = new HashMap<String, QICampoAbstrato>();
		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_qiform());

		handlerEnterPress = new KeyPressHandler()
		{

			@Override
			public void onKeyPress(KeyPressEvent event)
			{
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER)
				{
					QIForm.this.fireEvent(new EnvioFormularioEvent());
				}
			}
		};
	}

	@Override
	public final void add(IsWidget child)
	{
		super.add(child);
	}

	private void registrarHandlerEnvioFormulario(Widget campo)
	{
		campo.addDomHandler(handlerEnterPress, KeyPressEvent.getType());
	}

	public void adicionarCampo(String strIdentificadorCampo, Widget campo, QIValidarCampo validador)
	{
		QICampo qiCampo = new QICampo();
		qiCampo.adicionarCampo(strIdentificadorCampo, campo);

		adicionarCampo(strIdentificadorCampo, qiCampo, validador);
	}

	public void adicionarCampoTextBox(String strIdentificadorCampo, TextBoxBase txbCampo, QIValidarCampo validador)
	{
		QICampoTextBox qiCampo = new QICampoTextBox();
		qiCampo.adicionarCampoTextBox(strIdentificadorCampo, txbCampo);

		adicionarCampo(strIdentificadorCampo, qiCampo, validador);
	}

	public void adicionarCampoColorPicker(String strIdentificadorCampo, QIPainelCor painelCor, QIValidarCampo validador)
	{
		QICampoColorPicker qiCampo = new QICampoColorPicker(strIdentificadorCampo, painelCor);

		adicionarCampo(strIdentificadorCampo, qiCampo, validador);
	}

	public void adicionarCampoEditavel(String strIdentificadorCampo, QICampoEditavel qiCampo, QIValidarCampo validador)
	{
		qiCampo.adicionarComoCampo(strIdentificadorCampo);

		adicionarCampo(strIdentificadorCampo, qiCampo, validador);
	}

	public void adicionarCampo(String strIdentificadorCampo, QICampoAbstrato qiCampo, QIValidarCampo validador)
	{
		qiCampo.setValidadorCampo(validador);
		qiCampo.setStrPreCookieName(preeCookieName);

		mapCamposAdicionados.put(strIdentificadorCampo, qiCampo);
		this.add(qiCampo);

		registrarHandlerEnvioFormulario(qiCampo.getCampo());
	}

	public void alterarCampo(String strIdentificadorCampo, Widget campo)
	{
		QICampoAbstrato qiCampo = mapCamposAdicionados.get(strIdentificadorCampo);

		if (qiCampo != null)
		{
			qiCampo.alterarCampo(campo);
		}
	}

	public void alterarLabelCampo(String strIdentificadorCampo, String strLabelCampo)
	{
		QICampoAbstrato qiCampo = mapCamposAdicionados.get(strIdentificadorCampo);

		if (qiCampo != null)
		{
			qiCampo.alterarTextoLabel(strLabelCampo);
		}
	}

	public void ocultarCampo(String strIdentificadorCampo, boolean visible)
	{
		QICampoAbstrato qiCampo = mapCamposAdicionados.get(strIdentificadorCampo);

		if (qiCampo != null)
		{
			qiCampo.setVisible(visible);
		}
	}

	public QICampoAbstrato removerCampo(String strIdentificadorCampo)
	{
		QICampoAbstrato qiCampo = mapCamposAdicionados.remove(strIdentificadorCampo);

		if (qiCampo != null)
		{
			qiCampo.removeFromParent();
		}

		return qiCampo;
	}

	public QICampoAbstrato getCampo(String strIdentificadorCampo)
	{
		return mapCamposAdicionados.get(strIdentificadorCampo);
	}

	public boolean validarCampos()
	{
		boolean passouTeste = true;

		for (QICampoAbstrato campoAtual : mapCamposAdicionados.values())
		{

			if (!campoAtual.validarCampo())
			{
				passouTeste = false;
			}
		}

		return passouTeste;
	}

	public void removerEstiloCamposInvalidos()
	{
		for (QICampoAbstrato campoAtual : mapCamposAdicionados.values())
		{
			campoAtual.removerEstiloCampoInvalido();
		}
	}

	public void gravarCacheCampos()
	{
		for (QICampoAbstrato campoAtual : mapCamposAdicionados.values())
		{
			campoAtual.gravarCookie();
		}
	}

	public void removerCacheCampos()
	{
		for (QICampoAbstrato campoAtual : mapCamposAdicionados.values())
		{
			campoAtual.removerCookie();
		}
	}

	public void setValoresCamposByCookie()
	{
		for (QICampoAbstrato campoAtual : mapCamposAdicionados.values())
		{
			campoAtual.setValorCampoByValorCookie();
		}
	}

	public void setWidthCampos(String width)
	{
		for (QICampoAbstrato campoAtual : mapCamposAdicionados.values())
		{
			campoAtual.getCampo().setWidth(width);
		}
	}

	public void setWidthCampo(String strIdentificadorCampo, String width)
	{
		QICampoAbstrato qiCampo = mapCamposAdicionados.get(strIdentificadorCampo);

		if (qiCampo != null)
		{
			qiCampo.getCampo().setWidth(width);
		}
	}

	public void addOnEnvioFormularioHandler(EnvioFormularioHandler handler)
	{
		this.addHandler(handler, EnvioFormularioEvent.getType());
	}

	public Collection<QICampoAbstrato> getCampos()
	{
		return mapCamposAdicionados.values();
	}

}
