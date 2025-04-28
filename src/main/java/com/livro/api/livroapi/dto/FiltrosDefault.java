package com.livro.api.livroapi.dto;


public class FiltrosDefault {
	
	private int pageNumber;
	private int pageSize;
	private String orderBy;
	private String direction;

	public String getOrderBy() {
		if(orderBy == null || orderBy.isBlank()) {
			orderBy = "id";
		}
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getDirection() {
		if(direction == null || direction.isBlank()) {
			direction = "desc";
		}
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		if(pageSize <= 0) pageSize = 10;
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
