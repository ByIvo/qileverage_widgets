package br.com.qileverage.widgets.upload;

import java.util.Arrays;

import br.com.qileverage.widgets.QIControleMensagens;
import br.com.qileverage.widgets.QIControlePopupBloqueio;
import br.com.qileverage.widgets.QITela;
import br.com.qileverage.widgets.elementspersonalizados.QIPilhaVertical;
import br.com.qileverage.widgets.mensagens.QIMensagemExibicao.TipoMensagem;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class QIFormUpload extends FormPanel implements QITela
{
	private FileUpload fileUpload;

	private String[] extensoesAceitas;
	private QIPilhaVertical pilha = new QIPilhaVertical();
	private boolean enviarAutomaticamente;
	private boolean abrirBloqueio;

	public QIFormUpload()
	{
		montarEstrutura();
	}

	private void montarEstrutura()
	{
		instanciarItens();
		setarInformacoes();
		montarTela();
	}

	@Override
	public void instanciarItens()
	{
		fileUpload = getNewFileUpload();
		extensoesAceitas = null;

		enviarAutomaticamente = true;
		abrirBloqueio = true;
	}

	@Override
	public void setarInformacoes()
	{
		this.setEncoding(FormPanel.ENCODING_MULTIPART);
		this.setMethod(METHOD_POST);

		this.addSubmitCompleteHandler(new SubmitCompleteHandler()
		{

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event)
			{
				if (abrirBloqueio)
				{
					QIControlePopupBloqueio.mostrarBloqueio(false);
				}
			}
		});
	}

	@Override
	public void montarTela()
	{
		pilha.adicionarWidget(fileUpload);
		this.add(pilha);
	}

	public void enviarArquivo()
	{
		String arquivoPath = fileUpload.getFilename();

		if (!arquivoPath.isEmpty() && verificarExtensao(arquivoPath))
		{
			if (abrirBloqueio)
			{
				QIControlePopupBloqueio.mostrarBloqueio(true);
			}
			enviouConteudo();
			this.submit();

		}
		else
		{
			QIControleMensagens.mostrarMensagem("Selecione um arquivo válido para continuar!", TipoMensagem.ALERTA);
		}
	}

	private boolean verificarExtensao(String arquivo)
	{
		if (extensoesAceitas != null)
		{
			if (extensoesAceitas.length > 0)
			{
				String extensao = arquivo.substring(arquivo.lastIndexOf("."), arquivo.length());

				return Arrays.asList(extensoesAceitas).contains(extensao);
			}
		}
		return true;
	}

	public void limparSelecao()
	{
		pilha.clear();

		fileUpload = getNewFileUpload();

		pilha.adicionarWidget(fileUpload);
	}

	private FileUpload getNewFileUpload()
	{
		final FileUpload fileUpload = new FileUpload();
		fileUpload.setName("fu_upload");

		fileUpload.addChangeHandler(new ChangeHandler()
		{

			@Override
			public void onChange(ChangeEvent event)
			{
				if (!fileUpload.getFilename().isEmpty() && enviarAutomaticamente)
				{
					enviarArquivo();
				}
			}
		});

		return fileUpload;
	}

	public boolean isEnviarAutomaticamente()
	{
		return enviarAutomaticamente;
	}

	public void setEnviarAutomaticamente(boolean enviarAutomaticamente)
	{
		this.enviarAutomaticamente = enviarAutomaticamente;
	}

	public String[] getExtensoesAceitas()
	{
		return extensoesAceitas;
	}

	public void setExtensoesAceitas(String... extensoesAceitas)
	{
		this.extensoesAceitas = extensoesAceitas;
	}

	public boolean isAbrirBloqueio()
	{
		return abrirBloqueio;
	}

	public void setAbrirBloqueio(boolean abrirBloqueio)
	{
		this.abrirBloqueio = abrirBloqueio;
	}

	public boolean selecionouArquivo()
	{
		return !fileUpload.getFilename().isEmpty();
	}

	public void adicionarNovoCampo(Widget campo)
	{
		pilha.adicionarWidget(campo);
	}
	
	
	public FileUpload getFileUpload()
	{
		return fileUpload;
	}

	public abstract void enviouConteudo();

}
