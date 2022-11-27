package com.gft.webClient;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NoticiaApi {

	private int count;
	private List<PesquisarNoticia> list;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<PesquisarNoticia> getList() {
		return list;
	}

	public void setList(List<PesquisarNoticia> list) {
		this.list = list;
	}

}
