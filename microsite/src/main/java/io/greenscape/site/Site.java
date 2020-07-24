package io.greenscape.site;

import java.util.List;

public class Site {
	Long id;
	String name;
	String title;
	String description;
	Site parent;
	boolean active;
	boolean isDefault;
	Theme defaultTheme;
	List<Page> pages;
	SiteTemplate template;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Site getParent() {
		return parent;
	}

	public void setParent(Site parent) {
		this.parent = parent;
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

	public Theme getDefaultTheme() {
		return defaultTheme;
	}

	public void setDefaultTheme(Theme theme) {
		this.defaultTheme = theme;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public SiteTemplate getTemplate() {
		return template;
	}

	public void setTemplate(SiteTemplate template) {
		this.template = template;
	}

	public Page getDefaultPage() {
		return pages.stream().filter(p -> p.isDefault()).findFirst().get();
	}
}
