package br.com.qileverage.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface Entidade extends Serializable, IsSerializable
{
	public abstract void setId(Integer id);

	public abstract Integer getId();

}
