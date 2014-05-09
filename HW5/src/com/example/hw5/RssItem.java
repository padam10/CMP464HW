package com.example.hw5;

public class RssItem {
	private String title;
	private String description;
	private static String pubDate;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		RssItem.pubDate = pubDate;
	}
	
	
	

}
