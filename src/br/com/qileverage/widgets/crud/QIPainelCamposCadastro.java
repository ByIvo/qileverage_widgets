package br.com.qileverage.widgets.crud;

import br.com.qileverage.widgets.elementspersonalizados.QIHrWidget;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaHorizontal;
import br.com.qileverage.widgets.form.QIForm;
import br.com.qileverage.widgets.resources.Resources;

import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class QIPainelCamposCadastro extends QIPilhaHorizontal
{
	private QIForm qiForm;
	private QIHrWidget qiHrLinhaVertical;
	private Widget widget;

	public QIPainelCamposCadastro()
	{
		qiForm = new QIForm("");
		qiHrLinhaVertical = new QIHrWidget();
		widget = new SimplePanel();

		qiHrLinhaVertical.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelcamposcadastro_hrdivisao());

		this.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelcamposcadastro());

		refresh();
	}

	private void refresh()
	{
		this.clear();
		this.adicionarWidget(qiForm);
		this.adicionarWidget(qiHrLinhaVertical);
		this.adicionarWidget(widget);
		
		widget.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelcamposcadastro_widgetpainelextra());
		qiForm.addStyleName(Resources.INSTANCE.cssQiLeverageWidgets().widget_crud_painelcamposcadastro_qiform());
	}

	// GETTERS AND SETTERS
	public QIForm getQiForm()
	{
		return qiForm;
	}

	public void setQiForm(QIForm qiForm)
	{
		this.qiForm = qiForm;
		refresh();
	}

	public Widget getWidget()
	{
		return widget;
	}

	public void setWidgetAuxCadastro(Widget widget)
	{
		this.widget = widget;
		refresh();
	}

}
