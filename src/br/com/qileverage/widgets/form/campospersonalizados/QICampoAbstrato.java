package br.com.qileverage.widgets.form.campospersonalizados;

import br.com.qileverage.widgets.QIControleCookies;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.form.validadores.QIValidarCampo;
import br.com.qileverage.widgets.funcoes.CControleFuncoesClient;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class QICampoAbstrato extends QIPilhaHorizontal
{
	private Label lblCampo;
	private String strLabelCampo;
	private Widget wCampo;

	private QIValidarCampo validadorCampo;

	private String strPreCookieName;

	public QICampoAbstrato()
	{
		lblCampo = new Label();
		strLabelCampo = "";
		wCampo = null;
		strPreCookieName = "";

		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_qiform_containercampo());
		lblCampo.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_qiform_containercampo_labelcampo());
	}

	@Override
	public final void adicionarWidget(Widget w)
	{
		super.adicionarWidget(w);
	}

	public void adicionarCampo(String strLabelCampo, Widget campo)
	{
		alterarTextoLabel(strLabelCampo);
		procedimentoAdicao(campo);
	}

	public void alterarCampo(Widget campo)
	{
		procedimentoAdicao(campo);
	}

	public void alterarTextoLabel(String strLabelCampo)
	{
		this.strLabelCampo = strLabelCampo;
		this.lblCampo.setText(CControleFuncoesClient.getNomeCampoComSeparador(this.strLabelCampo));
	}

	public void setValidadorCampo(QIValidarCampo validador)
	{
		this.validadorCampo = validador;
	}

	public boolean validarCampo()
	{
		if (validadorCampo != null)
		{
			removerEstiloCampoInvalido();

			boolean testeCampo = validadorCampo.validarCampo(this);

			if (testeCampo)
			{
				return testeCampo;
			}

			adicionarEstiloCampoInvalido();
			return false;
		}

		return true;
	}

	private void procedimentoAdicao(Widget campo)
	{
		this.clear();
		this.wCampo = campo;

		this.adicionarWidget(lblCampo);
		this.adicionarWidget(wCampo);

		wCampo.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_qiform_containercampo_widgetcampo());
	}

	public void adicionarEstiloCampoInvalido()
	{
		wCampo.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_qiform_containercampo_widgetcampo_campoinvalido());
		lblCampo.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_qiform_containercampo_labelcampo_campoinvalido());
	}

	public void removerEstiloCampoInvalido()
	{
		wCampo.removeStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_qiform_containercampo_widgetcampo_campoinvalido());
		lblCampo.removeStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_qiform_containercampo_labelcampo_campoinvalido());
	}

	public String getCookieName()
	{
		return strPreCookieName + strLabelCampo;
	}

	public void gravarCookie()
	{
		QIControleCookies.adicionarCookie(getCookieName(), getValorCampo());
	}

	public void removerCookie()
	{
		QIControleCookies.removeCookie(getCookieName());
	}

	public String getValueCookie()
	{
		return QIControleCookies.getCookie(getCookieName());
	}

	public void setValorCampoByValorCookie()
	{
		String value = getValueCookie();
		setValorCampoByValorCookie(value);
	}

	// GETTERS

	public Widget getCampo()
	{
		return wCampo;
	}

	public String getTextoLabelCampo()
	{
		return strLabelCampo;
	}

	public String getStrPreCookieName()
	{
		return strPreCookieName;
	}

	public void setStrPreCookieName(String strPreCookieName)
	{
		this.strPreCookieName = strPreCookieName;
	}

	// METODOS ABSTRATOS
	public abstract String getValorCampo();

	public abstract void setValorCampoByValorCookie(String value);

}
