package br.com.qileverage.widgets.misc;

import java.util.Date;

import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.datepicker.client.DateBox;

public class QICampoEscolhaData extends QIPilhaHorizontal implements QITela
{
	private DateBox dbDate;
	private Image imgBotaoHoraAgora;

	public QICampoEscolhaData()
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
		dbDate = new DateBox();
		imgBotaoHoraAgora = new Image();
	}

	@Override
	public void setarInformacoes()
	{
		setFormatoDateBox("dd/MM/yyyy");

		imgBotaoHoraAgora.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				atualizarParaDataAtual();
			}
		});

		imgBotaoHoraAgora.setTitle("Selecionar data atual");
		imgBotaoHoraAgora.setResource(Resources.INSTANCE.imgClock());
		imgBotaoHoraAgora.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_misc_escolhadata_botaodataatual());
	}

	@Override
	public void montarTela()
	{
		this.adicionarWidget(dbDate);
		this.adicionarWidget(imgBotaoHoraAgora);
	}

	public Date getDataAtual()
	{
		return new Date();
	}

	public void setFormatoDateBox(String pattern)
	{
		DateTimeFormat format = DateTimeFormat.getFormat(pattern);
		dbDate.setFormat(new DateBox.DefaultFormat(format));
	}

	public void atualizarParaDataAtual()
	{
		dbDate.setValue(QICampoEscolhaData.this.getDataAtual());
	}

	public Date getDataSelecionada()
	{
		return dbDate.getValue();
	}
}
