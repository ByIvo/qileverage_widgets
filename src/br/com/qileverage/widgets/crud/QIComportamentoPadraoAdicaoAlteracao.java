package br.com.qileverage.widgets.crud;

import br.com.qileverage.shared.Entidade;
import br.com.qileverage.shared.QIAcao;
import br.com.qileverage.shared.QIRetornoRPC;

public class QIComportamentoPadraoAdicaoAlteracao<ENTIDADE extends Entidade, TIPO_RETORNO_RPC> implements QIAcao
{
	private QIRetornoRPC<TIPO_RETORNO_RPC> result;
	private ENTIDADE entidade;
	private QICrud<ENTIDADE> crudPadrao;

	public QIComportamentoPadraoAdicaoAlteracao(QIRetornoRPC<TIPO_RETORNO_RPC> result, ENTIDADE entidade, QICrud<ENTIDADE> crudPadrao)
	{
		super();
		this.result = result;
		this.entidade = entidade;
		this.crudPadrao = crudPadrao;

		doAcao();
	}

	@Override
	public void doAcao()
	{
		@SuppressWarnings("unchecked")
		ENTIDADE alunoRetorno = (ENTIDADE) result.getObjetoRetorno();

		if (entidade.getId() == null)
		{
			crudPadrao.adicionarEntidadeNoGrid(alunoRetorno);
		}
		else
		{
			crudPadrao.substituirEntidade(entidade, alunoRetorno);
		}

		crudPadrao.limparCampos();
	}

	@Override
	public void doAcao(Object obj)
	{
		// TODO Auto-generated method stub
		
	}

}
