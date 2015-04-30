package br.com.qileverage.widgets.crud;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

public abstract class QIPainelFiltrarResultados extends SimplePanel implements QITela
{
	
	private QIPilhaVertical layoutPagina;
	
	private Label lblTitulo;
	private TextBox txbPesquisa;
	
	public QIPainelFiltrarResultados()
	{
		montarInformacoes();
	}
	
	private void montarInformacoes()
	{
		this.instanciarItens();
		this.setarInformacoes();
		this.montarTela();
	}
	
	@Override
	public void instanciarItens()
	{
		layoutPagina = new QIPilhaVertical();
		
		txbPesquisa = new TextBox();
		
		lblTitulo = new Label();
	}
	
	@Override
	public void setarInformacoes()
	{
		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_griddados_filtroresultados());
		layoutPagina.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_griddados_filtroresultados_layout());
		lblTitulo.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_griddados_filtroresultados_labeltitulo());
		txbPesquisa.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_griddados_filtroresultados_textboxfiltro());
		
		lblTitulo.setText("Filtrar resultados:");
		
		txbPesquisa.addBlurHandler(new BlurHandler()
		{
			
			@Override
			public void onBlur(BlurEvent event)
			{
				hide();
			}
		});
		
		txbPesquisa.addKeyUpHandler(new KeyUpHandler()
		{
			
			@Override
			public void onKeyUp(KeyUpEvent event)
			{
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				{
					filtrar(txbPesquisa.getText());
					hide();
				}
			}
		});
	}
	
	@Override
	public void montarTela()
	{
		this.hide();
		
		layoutPagina.adicionarWidget(lblTitulo);
		layoutPagina.adicionarWidget(txbPesquisa);
		
		this.add(layoutPagina);
	}
	
	@Override
	protected void onLoad()
	{
		super.onLoad();
	}
	
	public void show()
	{
		this.setVisible(true);
		this.txbPesquisa.setFocus(true);
	}
	
	public void hide()
	{
		this.setVisible(false);
		txbPesquisa.setText("");
	}
	
	// abstratos
	
	public abstract void filtrar(String filtro);
	
}
