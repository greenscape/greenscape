package io.greenscape.site;

public class Theme {
	private Long id;
	private String name;
	private String description;
	private Style style;
	private String previewIcon;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the style
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	/**
	 * @return the previewIcon
	 */
	public String getPreviewIcon() {
		return previewIcon;
	}

	/**
	 * @param previewIcon the previewIcon to set
	 */
	public void setPreviewIcon(String previewIcon) {
		this.previewIcon = previewIcon;
	}
}
