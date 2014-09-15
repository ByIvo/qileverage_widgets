package br.com.qileverage.widgets.selecaopesquisa;

import java.util.ArrayList;
import java.util.List;

import br.com.qileverage.shared.Entidade;
import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.cellwidgets.QIAbstractCell;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.popup.QIPopupPesquisa;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public abstract class QISelecaoPesquisa<ENTIDADE> extends QIPilhaHorizontal implements QITela
{
	private Image imgPesquisa;
	private Label lblTitulo;
	private ENTIDADE entidadeSelecionada;
	private QIPopupPesquisa<ENTIDADE> qiPopupPesquisa;
	private QIAbstractCell<ENTIDADE> celulaCellList;

	public QISelecaoPesquisa(QIAbstractCell<ENTIDADE> celulaCellList)
	{
		this.celulaCellList = celulaCellList;
		montarEstrutura();
	}

	public void setTitulo(String texto)
	{
		lblTitulo.setText(texto);
		qiPopupPesquisa.setarTituloPopup(texto);
	}

	private void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	public ENTIDADE getEntidadeSelecionada()
	{
		return entidadeSelecionada;
	}

	public void selecionarNovaEntidade(ENTIDADE entidade)
	{
		entidadeSelecionada = entidade;
		novaEntidadeSelecionada(entidadeSelecionada);
	}

	public void setListaEntidade(List<ENTIDADE> listaEntidade)
	{
		qiPopupPesquisa.setListaEntidade(listaEntidade);
	}

	@SuppressWarnings("unchecked")
	public List<ENTIDADE> transformarLista(List<Entidade> listaConteudoGenerico)
	{
		List<ENTIDADE> listaRetorno = new ArrayList<ENTIDADE>();

		for (Entidade entidadeAtual : listaConteudoGenerico)
		{
			listaRetorno.add((ENTIDADE) entidadeAtual);
		}

		return listaRetorno;
	}

	// METODOS SOBRECARREGADOS
	@Override
	public void instanciarItens()
	{
		imgPesquisa = new Image(Resources.INSTANCE.imgSearch());
		lblTitulo = new Label();

		qiPopupPesquisa = new QIPopupPesquisa<ENTIDADE>(celulaCellList)
		{

			@Override
			public void selecionarNovaEntidade(ENTIDADE novaEntidade)
			{
				QISelecaoPesquisa.this.selecionarNovaEntidade(novaEntidade);
			}

			@Override
			public void realizarPesquisaEntidades(String strFiltro)
			{
				QISelecaoPesquisa.this.realizarPesquisaEntidades(strFiltro);
			}

			@Override
			public boolean validarSelecaoUsuario(ENTIDADE entidadeSelecionada)
			{
				return QISelecaoPesquisa.this.validarSelecaoEntidade(entidadeSelecionada);
			}

			@Override
			public void acaoErroSelecaoNulla()
			{
				QISelecaoPesquisa.this.acaoErroSelecaoNulla();
			}

			@Override
			public void acaoErroSelecaoInvalida()
			{
				QISelecaoPesquisa.this.acaoErroSelecaoInvalida();
			}
		};
	}

	@Override
	public void setarInformacoes()
	{
		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_selecaopesquisa());
		imgPesquisa.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_selecaopesquisa_imagem());
		lblTitulo.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_selecaopesquisa_labeltitulo());

		imgPesquisa.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				qiPopupPesquisa.show();
			}
		});
	}

	@Override
	public void montarTela()
	{
		adicionarWidget(imgPesquisa);
		adicionarWidget(lblTitulo);
	}

	// METODOS ABSTRATOS

	public abstract void novaEntidadeSelecionada(ENTIDADE novaEntidade);

	public abstract void realizarPesquisaEntidades(String strFiltro);

	public abstract boolean validarSelecaoEntidade(ENTIDADE entidadeSelecionada);

	public abstract void acaoErroSelecaoNulla();

	public abstract void acaoErroSelecaoInvalida();

	// GETTERS
	public Label getLblTitulo()
	{
		return lblTitulo;
	}

	public void setLblTitulo(Label lblTitulo)
	{
		this.lblTitulo = lblTitulo;
	}

	public void setPermitirSelecaoNulla(boolean permitirSelecaoNulla)
	{
		qiPopupPesquisa.setPermitirSelecaoNulla(permitirSelecaoNulla);
	}

}
