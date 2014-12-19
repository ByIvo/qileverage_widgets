package br.com.qileverage.shared;

public class QIUteis
{
	
	public static String getStringFormatada(String valor, String mascara)
	{
		
		if (valor.isEmpty())
		{
			return valor;
		}
		
		StringBuffer retorno = new StringBuffer();
		
		int lastChar = 0;
		for (int i = 0; i < mascara.length(); i++)
		{
			char actualChar = mascara.charAt(i);
			
			if (actualChar == '#')
			{
				if (lastChar < valor.length())
				{
					retorno.append(valor.charAt(lastChar));
					lastChar++;
				}
				else
				{
					break;
				}
			}
			else
			{
				retorno.append(actualChar);
			}
		}
		
		if (valor.length() > lastChar)
		{
			retorno.append(valor.substring(lastChar, valor.length()));
		}
		
		// System.out.print(valor);
		// System.out.print(" - ");
		// System.out.print(mascara);
		// System.out.print(" - ");
		// System.out.print(retorno.toString());
		// System.out.println();
		
		return retorno.toString();
	}
	
	public static String getStringSemMascara(String valor, String mascara, int maxSize)
	{
		String retorn = getStringSemMascara(valor, mascara);
		
		if (retorn.length() > maxSize)
		{
			retorn = retorn.substring(0, maxSize);
		}
		return retorn;
	}
	
	public static String getStringSemMascara(String valor, String mascara)
	{
		StringBuffer retorno = new StringBuffer();
		
		for (int i = 0; i < mascara.length(); i++)
		{
			char actualChar = mascara.charAt(i);
			
			if (valor.length() > i)
			{
				if (actualChar != valor.charAt(i))
				{
					retorno.append(valor.charAt(i));
				}
			}
		}
		
		if (valor.length() > mascara.length())
		{
			retorno.append(valor.substring(mascara.length(), valor.length()));
		}
		
		return retorno.toString();
	}
	
	public static int getCursorPosition(String valor, String mascara)
	{
		for (int i = 0; i < mascara.length(); i++)
		{
			char actualChar = mascara.charAt(i);
			
			if (valor.length() > i)
			{
				if (actualChar == '#')
				{
					if (valor.charAt(i) != '_')
					{
						continue;
					}
				}
				else
				{
					continue;
				}
			}
			
			return i;
		}
		
		return valor.length();
		
	}
	
	public static boolean validarCPF(String strCpf)
	{
		if (strCpf.equals("00000000000") || strCpf.equals("11111111111") || strCpf.equals("22222222222") || strCpf.equals("33333333333") || strCpf.equals("44444444444") || strCpf.equals("55555555555") || strCpf.equals("66666666666") || strCpf.equals("77777777777") || strCpf.equals("88888888888") || strCpf.equals("99999999999") || (strCpf.length() != 11))
			return (false);
		
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;
		
		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;
		
		for (int nCount = 1; nCount < strCpf.length() - 1; nCount++)
		{
			digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();
			
			// multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4
			// e assim por diante.
			d1 = d1 + (11 - nCount) * digitoCPF;
			
			// para o segundo digito repita o procedimento incluindo o primeiro
			// digito calculado no passo anterior.
			d2 = d2 + (12 - nCount) * digitoCPF;
		};
		
		// Primeiro resto da divisão por 11.
		resto = (d1 % 11);
		
		// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
		// menos o resultado anterior.
		if (resto < 2)
			digito1 = 0;
		else
			digito1 = 11 - resto;
		
		d2 += 2 * digito1;
		
		// Segundo resto da divisão por 11.
		resto = (d2 % 11);
		
		// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
		// menos o resultado anterior.
		if (resto < 2)
			digito2 = 0;
		else
			digito2 = 11 - resto;
		
		// Digito verificador do CPF que está sendo validado.
		String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());
		
		// Concatenando o primeiro resto com o segundo.
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
		
		// comparar o digito verificador do cpf com o primeiro resto + o segundo
		// resto.
		return nDigVerific.equals(nDigResult);
	}
	
	public static String inverterDecimalSeparator(String strNumero)
	{
		strNumero = strNumero.replace(".", "@");
		strNumero = strNumero.replace(",", ".");
		strNumero = strNumero.replace("@", ",");
		
		return strNumero;
	}
	
}
