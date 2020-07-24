package io.greenscape.site;

import java.util.List;

public class Page {
	Long id;
	String name;
	String title;
	Theme theme;
	PageTemplate template;
	boolean active;
	boolean isDefault;
	List<WebletInstance> weblets;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public PageTemplate getTemplate() {
		return template;
	}

	public void setTemplate(PageTemplate template) {
		this.template = template;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public List<WebletInstance> getWeblets() {
		return weblets;
	}

	public void setWeblets(List<WebletInstance> weblets) {
		this.weblets = weblets;
	}

}
