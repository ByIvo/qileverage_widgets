package br.com.qileverage.widgets.form.campospersonalizados;


public abstract class QICampoEditavelDouble extends QICampoEditavel
{
	private Double valorValidado;

	public QICampoEditavelDouble()
	{
		super();
		
		valorValidado = 0.0;
		setValorContido("0");
	}

	@Override
	public boolean validarValorDigitado(String valorDigitado)
	{
		try
		{
			valorValidado = Double.parseDouble(valorDigitado);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public Double getValorContidoDouble()
	{
		return valorValidado;
	}

	@Override
	public void alterouValor(String valor)
	{
		alterouValorDouble(this.getValorContidoDouble());
	}

	public abstract void alterouValorDouble(double valor);
}
