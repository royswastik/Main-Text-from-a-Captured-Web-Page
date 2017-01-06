package com.swastikroy.model;

/**
 * Model class to store information about article content
 * @author Swastik
 *
 */
public class ArticleContent {
	private String title;
	private String description;
	private String date;
	private String everything;
	
	public ArticleContent(String title, String description, String date) {
		super();
		this.title = title;
		this.description = description;
		this.date = date;
	}
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEverything() {
		return everything;
	}
	public void setEverything(String everything) {
		this.everything = everything;
	}
	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer();
		bf.append("Title: \n");
		bf.append(this.title);
		bf.append("\n \n Description: \n");
		bf.append(this.description);
		bf.append("\n \n Date: \n");
		bf.append(this.date);
		return bf.toString();
	}
	
}
