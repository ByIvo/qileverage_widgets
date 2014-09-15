package br.com.qileverage.widgets;

import java.util.Date;

import com.google.gwt.user.client.Cookies;

public class QIControleCookies
{

	public static void adicionarCookie(String name, String value)
	{
		Cookies.setCookie(name, value);
	}

	public static void adicionarCookie(String name, String value, Date expires)
	{
		Cookies.setCookie(name, value, expires);
	}

	public static void removeCookie(String name)
	{
		Cookies.removeCookie(name);
	}

	public static String getCookie(String name)
	{
		return Cookies.getCookie(name);
	}

}
