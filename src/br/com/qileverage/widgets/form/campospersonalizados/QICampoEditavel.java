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

public abstract class QICampoEditavel extends QICampoAbstrato implements QITela
{
	private SimplePanel spAlteraWidgets;

	private Image imgEditar;
	private Label lblValor;
	private TextBox txbAlteracao;

	private QIPilhaHorizontal pilhaItens;
	
	private String strValorContido;

	private Estado estado;

	public QICampoEditavel()
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
			txbAlteracao.setText(getValorContido());

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
			setValorContido(novoValor);

			spAlteraWidgets.clear();
			spAlteraWidgets.add(lblValor);
			
			alterouValor(novoValor);
			
			estado = Estado.FECHADO;
		}
		else
		{
			txbAlteracao.setFocus(true);
			txbAlteracao.selectAll();
		}
	}

	// GETTERS AND SETTERS

	public String getValorContido()
	{
		return strValorContido;
	}

	public void setValorContido(String valorContido)
	{
		this.strValorContido = valorContido;
		lblValor.setText(valorContido);
	}

	// METODOS SOBRECARREGADOS

	@Override
	public void instanciarItens()
	{
		spAlteraWidgets = new SimplePanel();

		lblValor = new Label();
		strValorContido = "";
		txbAlteracao = new TextBox();
		imgEditar = new Image(Resources.INSTANCE.imgEdit());

		pilhaItens = new QIPilhaHorizontal();
		
		estado = Estado.FECHADO;
	}

	@Override
	public void setarInformacoes()
	{
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
				QICampoEditavel.this.iniciarEdicao();
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

//		this.adicionarWidget(lblDescricao);
		pilhaItens.adicionarWidget(imgEditar);
		pilhaItens.adicionarWidget(spAlteraWidgets);
	}

	// METODOS ABSTRATOS
	public abstract boolean validarValorDigitado(String valorDigitado);

	public abstract void alterouValor(String valor);

	// ENUM ESTADO

	private enum Estado
	{
		EDICAO, FECHADO;
	}
	
	

}
