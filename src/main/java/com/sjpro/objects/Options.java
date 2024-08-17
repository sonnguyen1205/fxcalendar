package com.sjpro.objects;

import java.util.Map;

public class Options {
	protected static final int WIDTH = 600;
	protected static final int HEIGHT = 480;

	protected static final String[] DAY_SHORT_LABEL = new String[] { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };

	protected static final Map<String, String> CELL_HEADER_STYLE = Map.of("-fx-border-color", "#a3c9ff",
			"-fx-border-width", "1", "-fx-border-style", "solid solid none none;", "-fx-background-color", "#ffffff");
	protected static final Map<String, String> CELL_MONTH_STYLE = Map.of("-fx-border-color", "#a3c9ff",
			"-fx-border-width", "2 1 1 1", "-fx-border-style", "solid solid none none", "-fx-background-color",
			"#f9f9f9");

	protected static final Map<String, String> CELL_DAY_STYLE = Map.of("-fx-border-color", "#a3c9ff",
			"-fx-border-style", "solid solid none none", "-fx-background-color", "#f9f9f9");
	protected static final Map<String, String> CELL_BLANK_STYLE = Map.of("-fx-border-color", "#a3c9ff",
			"-fx-border-style", "solid solid none none", "-fx-background-color", "#ffffff");

	private String[] dayLabels;

	private Map<String, String> cellHeaderStyles;
	private Map<String, String> cellMonthStyles;
	private Map<String, String> cellDayStyles;
	private Map<String, String> cellBlankStyles;
	private Integer width;
	private Integer height;

	public void setDayLabels(String[] dayLabels) {
		this.dayLabels = dayLabels;
	}

	public String[] getDayLabels() {
		if (this.dayLabels != null) {
			return this.dayLabels;
		}
		return DAY_SHORT_LABEL;
	}

	public Map<String, String> getCellHeaderStyles() {
		if (this.cellHeaderStyles != null) {
			return this.cellHeaderStyles;
		}
		return CELL_HEADER_STYLE;
	}

	public void setCellHeaderStyles(Map<String, String> cellHeaderStyles) {
		this.cellHeaderStyles = cellHeaderStyles;
	}

	public Map<String, String> getCellMonthStyles() {
		if (cellMonthStyles != null) {
			return cellMonthStyles;
		}
		return CELL_MONTH_STYLE;
	}

	public void setCellMonthStyles(Map<String, String> cellMonthStyles) {
		this.cellMonthStyles = cellMonthStyles;
	}

	public Map<String, String> getCellDayStyles() {
		if (cellDayStyles != null) {
			return cellDayStyles;
		}
		return CELL_DAY_STYLE;
	}

	public void setCellDayStyles(Map<String, String> cellDayStyles) {
		this.cellDayStyles = cellDayStyles;
	}

	public Map<String, String> getCellBlankStyles() {
		if (cellBlankStyles != null) {
			return cellBlankStyles;
		}
		return CELL_BLANK_STYLE;
	}

	public void setCellBlankStyles(Map<String, String> cellBlankStyles) {
		this.cellBlankStyles = cellBlankStyles;
	}

	public Integer getWidth() {
		if (width != null) {
			return width;
		}
		return WIDTH;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		if (height != null) {
			return height;
		}
		return HEIGHT;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
}
