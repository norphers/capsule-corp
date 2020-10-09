package com.itacademy.capsulecorp.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> 
{
	private String url;
	private Page<T> page;
	private int totalPages;
	private int numElementsPerPage;
	private int currentPage;

	private List<PageItem> pages;

	public PageRender(String url, Page<T> page) 
	{
		this.url = url;
		this.page = page;
		this.pages = new ArrayList<PageItem>();

		numElementsPerPage = 6;
		totalPages = page.getTotalPages();
		currentPage = page.getNumber() + 1;

		int desde, hasta;
		if (totalPages <= numElementsPerPage) 
		{
			desde = 1;
			hasta = totalPages;
		} 
		else 
		{
			if (currentPage <= numElementsPerPage / 2) 
			{
				desde = 1;
				hasta = numElementsPerPage;
			} 
			else if (currentPage >= totalPages - numElementsPerPage / 2) 
			{
				desde = totalPages - numElementsPerPage + 1;
				hasta = numElementsPerPage;
			} 
			else 
			{
				desde = currentPage - numElementsPerPage / 2;
				hasta = numElementsPerPage;
			}
		}

		for (int i = 0; i < hasta; i++) 
		{
			pages.add(new PageItem(desde + i, currentPage == desde + i));
		}

	}

	public String getUrl() 
	{
		return url;
	}

	public int getTotalPages() 
	{
		return totalPages;
	}

	public int getCurrentPages() 
	{
		return currentPage;
	}

	public List<PageItem> getPages() 
	{
		return pages;
	}

	public boolean isFirst() 
	{
		return page.isFirst();
	}

	public boolean isLast() 
	{
		return page.isLast();
	}

	public boolean isHasNext() 
	{
		return page.hasNext();
	}

	public boolean isHasPrevious() 
	{
		return page.hasPrevious();
	}

}