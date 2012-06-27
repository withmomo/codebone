package org.codebone.framework.util;

public class PagingNavigation {
	
	private double totalRecord = 0;
	private double recordPerPage = 0;
	private double pagePerBlock = 0;
	private double currentPage = 0;
	private String href;
	private String paramters;
	
	public String getHtml() {
		int pageLeft = 0;
		int pageRight = 0;
		int totalNumOfPage = (int)Math.ceil(getTotalRecord()/getRecordPerPage()); //16page
		int currentBlock = (int)Math.ceil(getCurrentPage()/getPagePerBlock()); // 1page
		
		int startPage = (currentBlock-1)*(int)getPagePerBlock()+1;  // 1page
		
		int endPage = startPage+(int)getPagePerBlock() -1; // 10page 
		if(endPage > totalNumOfPage){
			endPage = totalNumOfPage;
		}
		
		if(getCurrentPage() == 1){
			pageLeft = 1;
			pageRight = 2;
		}else{
			pageLeft = (int)(getCurrentPage() - 1);
			pageRight = (int)(getCurrentPage() + 1);
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("<div style=\"text-align:center\" class=\"pagination\">");
		builder.append("<ul>");
		builder.append("<li><a href=\""+getHref()+"?page="+pageLeft+"&"+getParamters()+"\">«</a></li>");		
		
		for(int i=startPage;i<=endPage;i++){
			if( i == (int)getCurrentPage() )
				builder.append("<li class=\"active\">");
			else
				builder.append("<li>");
			
			builder.append("<a href=\""+getHref()+"?page="+i+"&"+getParamters()+"\">");
			builder.append(i);
			builder.append("</a></li>");
		} 

		builder.append("<li><a href=\""+getHref()+"?page="+pageRight+"&"+getParamters()+"\">»</a></li>");
		builder.append("<ul>");
		builder.append("</div>");
		return builder.toString();
	}

	public double getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(double totalRecord) {
		this.totalRecord = totalRecord;
	}

	public double getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(double recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public double getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(double pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public double getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(double currentPage) {
		this.currentPage = currentPage;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getParamters() {
		return paramters;
	}

	public void setParamters(String paramters) {
		this.paramters = paramters;
	}
}
