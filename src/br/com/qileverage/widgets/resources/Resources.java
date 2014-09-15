package br.com.qileverage.widgets.resources;

import br.com.qileverage.widgets.resources.constantes.QIConstantesWidgets;
import br.com.qileverage.widgets.resources.css.CSSQILeverageWidgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public final class Resources
{
	public static final ResourcesContent INSTANCE;
	public static final QIConstantesWidgets CONSTANTES;

	static
	{
		INSTANCE = GWT.create(ResourcesContent.class);
		INSTANCE.cssQiLeverageWidgets().ensureInjected();

		CONSTANTES = GWT.create(QIConstantesWidgets.class);
	}

	private Resources()
	{
	}

	public interface ResourcesContent extends ClientBundle
	{

		@Source("css/CSSQILeverageWidgets.css")
		public CSSQILeverageWidgets cssQiLeverageWidgets();

		@Source("imagens/search.png")
		public ImageResource imgSearch();

		@Source("imagens/campo_editar.png")
		public ImageResource imgEdit();

		@Source("imagens/salvar.png")
		public ImageResource imgSalvar();

		@Source("imagens/cancelar.png")
		public ImageResource imgCancelar();

		@Source("imagens/visualizar_imagem_entidade.png")
		public ImageResource imgVisualizacaoImagem();

		@Source("imagens/deletar_entidade.png")
		public ImageResource imgDeletarEntidade();

		@Source("imagens/alerta.png")
		public ImageResource imgAlerta();

		@Source("imagens/question.png")
		public ImageResource imgPergunta();

		@Source("imagens/sucess.png")
		public ImageResource imgSucess();

		@Source("imagens/error.png")
		public ImageResource imgErro();

		@Source("imagens/loading.gif")
		public ImageResource imgLoading();

		@Source("imagens/loading-small.gif")
		public ImageResource imgLoadingSmall();

		@Source("imagens/add.png")
		public ImageResource imgAdd();

		@Source("imagens/aberto.png")
		public ImageResource imgAberto();

		@Source("imagens/fechado.png")
		public ImageResource imgFechado();

		@Source("imagens/refresh.png")
		public ImageResource imgRefresh();

		@Source("imagens/clock.png")
		public ImageResource imgClock();

		@Source("imagens/mostrar_entidade.png")
		public ImageResource imgMostrarEntidade();

		@Source("imagens/ocultar_entidade.png")
		public ImageResource imgOcultarEntidade();

		@Source("imagens/left.png")
		public ImageResource imgLeft();

		@Source("imagens/right.png")
		public ImageResource imgRight();

		@Source("imagens/double_left.png")
		public ImageResource imgDoubleLeft();

		@Source("imagens/double_right.png")
		public ImageResource imgDoubleRight();
	}
}
