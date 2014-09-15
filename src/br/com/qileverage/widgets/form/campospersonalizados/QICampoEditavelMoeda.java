package br.com.qileverage.widgets.form.campospersonalizados;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

public abstract class QICampoEditavelMoeda extends QICampoAbstrato implements QITela
{
	private SimplePanel spAlteraWidgets;

	private Image imgEditar;
	private Label lblValor;
	private TextBox txbAlteracao;

	private QIPilhaHorizontal pilhaItens;

	private double valorAdicionado;

	private Estado estado;

	public QICampoEditavelMoeda()
	{
		montarEstrutura();
	}

	private void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	public void iniciarEdicao()
	{
		if (estado == Estado.FECHADO)
		{
			spAlteraWidgets.clear();
			spAlteraWidgets.add(txbAlteracao);
			txbAlteracao.setText(getValorParaEdicao(getValorAdicionado()));

			txbAlteracao.setFocus(true);
			txbAlteracao.selectAll();

			estado = Estado.EDICAO;
		}
	}

	public void encerrarEdicao()
	{
		if (estado != Estado.EDICAO)
		{
			return;
		}

		String novoValor = txbAlteracao.getText().trim();

		if (validarValorDigitado(novoValor))
		{
			double novoValorFormatado = converterValorDigitado(novoValor);

			spAlteraWidgets.clear();
			spAlteraWidgets.add(lblValor);

			setValorAdicionado(novoValorFormatado);
			alterouValor(novoValorFormatado);

			estado = Estado.FECHADO;
		}
		else
		{
			txbAlteracao.setFocus(true);
			txbAlteracao.selectAll();
		}
	}

	private void setarConteudoExibidoLabel(double valorAdicionado)
	{
		lblValor.setText(getValorFormatado(valorAdicionado));
	}

	// GETTERS AND SETTERS

	// METODOS SOBRECARREGADOS

	@Override
	public void instanciarItens()
	{
		spAlteraWidgets = new SimplePanel();

		lblValor = new Label();
		txbAlteracao = new TextBox();
		imgEditar = new Image(Resources.INSTANCE.imgEdit());

		pilhaItens = new QIPilhaHorizontal();

		estado = Estado.FECHADO;
	}

	public double getValorAdicionado()
	{
		return valorAdicionado;
	}

	public void setValorAdicionado(double valorAdicionado)
	{
		this.valorAdicionado = valorAdicionado;
		setarConteudoExibidoLabel(valorAdicionado);
	}

	@Override
	public void setarInformacoes()
	{
		setValorAdicionado(0);

		pilhaItens.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_campoeditavel());
		spAlteraWidgets.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_campoeditavel_containertrocawidgets());
		lblValor.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_campoeditavel_labelvaloreditavel());
		imgEditar.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_campoeditavel_imagemeditar());
		txbAlteracao.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_campoeditavel_txbvaloreditavel());

		imgEditar.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				QICampoEditavelMoeda.this.iniciarEdicao();
			}
		});

		txbAlteracao.addBlurHandler(new BlurHandler()
		{

			@Override
			public void onBlur(BlurEvent event)
			{
				encerrarEdicao();
			}
		});

		txbAlteracao.addKeyUpHandler(new KeyUpHandler()
		{

			@Override
			public void onKeyUp(KeyUpEvent event)
			{
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				{
					encerrarEdicao();
				}
			}
		});
	}

	@Override
	public String getValorCampo()
	{
		return "";
	}

	@Override
	public void setValorCampoByValorCookie(String value)
	{

	}

	public void adicionarComoCampo(String strLblCampo)
	{
		this.adicionarCampo(strLblCampo, pilhaItens);
	}

	@Override
	public void montarTela()
	{
		spAlteraWidgets.add(lblValor);

		// this.adicionarWidget(lblDescricao);
		pilhaItens.adicionarWidget(imgEditar);
		pilhaItens.adicionarWidget(spAlteraWidgets);
		
	}

	// METODOS ABSTRATOS
	public abstract boolean validarValorDigitado(String valorDigitado);

	public abstract void alterouValor(double valor);

	public abstract String getValorFormatado(double valor);

	public abstract String getValorParaEdicao(double valor);

	public abstract double converterValorDigitado(String valor);

	// ENUM ESTADO

	private enum Estado
	{
		EDICAO, FECHADO;
	}

}
