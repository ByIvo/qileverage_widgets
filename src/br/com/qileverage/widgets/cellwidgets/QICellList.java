package br.com.qileverage.widgets.cellwidgets;

import java.util.ArrayList;
import java.util.List;

import br.com.qileverage.shared.Entidade;
import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.popup.ShowMorePagerPanel;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.SingleSelectionModel;

public class QICellList<ENTIDADE> extends CellList<ENTIDADE> implements QITela
{
	private SingleSelectionModel<ENTIDADE> singleSelectionModel;
	private ShowMorePagerPanel pagerPanel;

	public QICellList(QIAbstractCell<ENTIDADE> celula)
	{
		super(celula);

		montarEstrutura();
	}

	// METODOS ACOES

	private void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	public ENTIDADE getEntidadeSelecionada()
	{
		return singleSelectionModel.getSelectedObject();
	}

	// METODOS SOBRECARREGADOS

	@Override
	public void instanciarItens()
	{
		singleSelectionModel = new SingleSelectionModel<ENTIDADE>();
		pagerPanel = new ShowMorePagerPanel();
	}

	@Override
	public void setarInformacoes()
	{
		this.setSelectionModel(singleSelectionModel);
		pagerPanel.setDisplay(this);
	}

	@Override
	public void montarTela()
	{

	}
	
	public void ativarImagemCarregandoComoEmptyList()
	{
		SimplePanel spImagme = new SimplePanel();
		spImagme.getElement().getStyle().setProperty("width","100%");
		spImagme.getElement().getStyle().setProperty("textAlign","center");
		
		Image imgCarregando = new Image(br.com.qileverage.widgets.resources.Resources.INSTANCE.imgLoading());
		
		spImagme.add(imgCarregando);
		
		imgCarregando.getElement().getStyle().setProperty("margin", "auto");
		this.setEmptyListWidget(spImagme);
		
		this.setRowData(new ArrayList<ENTIDADE>());
	}

	@SuppressWarnings("unchecked")
	public List<ENTIDADE> converterLista(List<Entidade> listaEntidade)
	{
		List<ENTIDADE> listaEntidadeConvertida = new ArrayList<ENTIDADE>();

		for (Entidade entidadeAtual : listaEntidade)
		{
			listaEntidadeConvertida.add((ENTIDADE) entidadeAtual);
		}

		return listaEntidadeConvertida;
	}

	// GETTERS AND SETTERS

	public SingleSelectionModel<ENTIDADE> getSingleSelectionModel()
	{
		return singleSelectionModel;
	}

	public void setSingleSelectionModel(SingleSelectionModel<ENTIDADE> singleSelectionModel)
	{
		this.singleSelectionModel = singleSelectionModel;
	}

	public ShowMorePagerPanel getPagerPanel()
	{
		return pagerPanel;
	}

	public void setPagerPanel(ShowMorePagerPanel pagerPanel)
	{
		this.pagerPanel = pagerPanel;
	}

}
