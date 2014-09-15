package br.com.qileverage.widgets.form.eventos;

import com.google.gwt.event.shared.GwtEvent;

public class EnvioFormularioEvent extends GwtEvent<EnvioFormularioHandler>
{
	private static Type<EnvioFormularioHandler> TYPE;

	static
	{
		TYPE = new Type<EnvioFormularioHandler>();
	}

	public EnvioFormularioEvent()
	{
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<EnvioFormularioHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(EnvioFormularioHandler handler)
	{
		handler.onEnvioFormulario(this);
	}

	public static Type<EnvioFormularioHandler> getType()
	{
		return TYPE;
	}

}
