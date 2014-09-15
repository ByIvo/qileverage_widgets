package br.com.qileverage.widgets.form.campospersonalizados;

public abstract class QICampoEditavelInteger extends QICampoEditavel
{
	private Integer valorValidado;

	public QICampoEditavelInteger()
	{
		super();

		setValorContido("0");
		valorValidado = 0;
	}

	@Override
	public boolean validarValorDigitado(String valorDigitado)
	{
		try
		{
			valorValidado = Integer.parseInt(valorDigitado);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public Integer getValorContidoInteger()
	{
		return valorValidado;
	}

	@Override
	public void alterouValor(String valor)
	{
		alterouValorInteger(this.getValorContidoInteger());
	}

	public abstract void alterouValorInteger(int valor);
}
