package br.com.qileverage.widgets.popup;

import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasRows;

public class ShowMorePagerPanel extends AbstractPager
{

	/**
	 * The default increment size.
	 */
	private static final int DEFAULT_INCREMENT = 20;

	/**
	 * The increment size.
	 */
	private int incrementSize = DEFAULT_INCREMENT;

	/**
	 * The last scroll position.
	 */
	private int lastScrollPos = 0;

	/**
	 * The scrollable panel.
	 */
	private final ScrollPanel scrollable = new ScrollPanel();

	/**
	 * Construct a new {@link ShowMorePagerPanel}.
	 */
	public ShowMorePagerPanel()
	{
		initWidget(scrollable);

		// Handle scroll events.
		scrollable.addScrollHandler(new ScrollHandler()
		{
			public void onScroll(ScrollEvent event)
			{
				// If scrolling up, ignore the event.
				int oldScrollPos = lastScrollPos;
				lastScrollPos = scrollable.getVerticalScrollPosition();
				if (oldScrollPos >= lastScrollPos)
				{
					return;
				}

				HasRows view = ShowMorePagerPanel.this.getDisplay();
				if (view == null)
				{
					return;
				}
				int maxScrollTop = scrollable.getWidget().getOffsetHeight() - scrollable.getOffsetHeight();
				if (lastScrollPos >= maxScrollTop)
				{
					// We are near the end, so increase the page size.
					int newPageSize = Math.min(view.getVisibleRange().getLength() + incrementSize, view.getRowCount());
					view.setVisibleRange(0, newPageSize);
				}
			}
		});
	}

	/**
	 * Get the number of rows by which the range is increased when the scrollbar
	 * reaches the bottom.
	 * 
	 * @return the increment size
	 */
	public int getIncrementSize()
	{
		return incrementSize;
	}

	/**
	 * Set the number of rows by which the range is increased when the scrollbar
	 * reaches the bottom.
	 * 
	 * @param incrementSize
	 *            the incremental number of rows
	 */
	public void setIncrementSize(int incrementSize)
	{
		this.incrementSize = incrementSize;
	}

	@Override
	public void setDisplay(HasRows view)
	{
		assert view instanceof Widget : "view must extend Widget";
		scrollable.setWidget((Widget) view);
		super.setDisplay(view);
	}

	@Override
	protected void onRangeOrRowCountChanged()
	{
	}
}
