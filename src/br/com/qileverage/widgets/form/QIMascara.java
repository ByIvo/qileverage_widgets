package br.com.qileverage.widgets.form;

import br.com.qileverage.shared.QIUteis;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.TextBoxBase;

public class QIMascara
{
	
	public static void adicionarMascara(final TextBoxBase widget, final String mascara)
	{
		
		widget.addKeyUpHandler(new KeyUpHandler()
		{
			
			@Override
			public void onKeyUp(KeyUpEvent event)
			{
				if (event.getNativeKeyCode() > 32)
				{
					if (event.getNativeKeyCode() != (KeyCodes.KEY_DELETE))
					{
						String novoTexto = QIUteis.getStringSemMascara(widget.getText(), mascara);
						
						if (novoTexto.isEmpty())
						{
							widget.setText("");
						}
						else
						{
							widget.setText(QIUteis.getStringFormatada(novoTexto, mascara));
						}
					}
				}
			}
		});
	}
	
	public static void adicionarRestricaoLetras(final TextBoxBase widget, final String valoresRestritos)
	{
		
		widget.addKeyPressHandler(new KeyPressHandler()
		{
			
			@Override
			public void onKeyPress(KeyPressEvent event)
			{
				String novoChar = String.valueOf(event.getCharCode());
				
				if (!valoresRestritos.toLowerCase().contains(novoChar.toLowerCase()))
				{
					widget.cancelKey();
				}
			}
		});
	}
}
