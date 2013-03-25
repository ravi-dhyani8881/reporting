package com.ytk.models;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
@JsonWriteNullProperties(false)
public class AutoCompleteResponse {
	
	private String query;
	private List   suggestions;
	private List   data;
	private List   id;
	private List   title;
	
	@JsonProperty("query")
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	@JsonProperty("suggestions")
	public List getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(List suggestions) {
		this.suggestions = suggestions;
	}
	@JsonProperty("data")
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	@JsonProperty("id")
	public List getId() {
		return id;
	}
	public void setId(List id) {
		this.id = id;
	}
	@JsonProperty("title")
	public List getTitle() {
		return title;
	}
	public void setTitle(List title) {
		this.title = title;
	}
	

	
}
