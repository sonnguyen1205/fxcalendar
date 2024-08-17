package com.sjpro;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;

import com.sjpro.objects.Options;

import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CalendarFX implements ICalendarFX {
	
	private StackPane nodeWP;
	private LocalDate calendarForYear;
	private Options options;
	
	private double stageWidth;
	private double stageHeight;
	
	private HBox currentMonthObject;
	
	protected VBox instance;
	private ScrollPane monthPanel;

	public CalendarFX(StackPane nodeWP, Options options) {
		super();
		this.nodeWP = nodeWP;
		
		this.options = new Options();
		
		if(options != null) {
			this.options = options;
		}
		
		this.calendarForYear = LocalDate.now();
		
		this.stageWidth = this.options.getWidth();
		this.stageHeight = this.options.getHeight();
		
		this.nodeWP.getChildren().add(build());
	}
	
	private VBox build() {
		
		int numOfDays = Year.of(calendarForYear.getYear()).length();
		int monthNow = this.calendarForYear.getMonthValue();
		
		LocalDate dateFirst = LocalDate.of(calendarForYear.getYear(), 1, 1);
		
		VBox board = new VBox();
		AnchorPane headerPane = createHeaderCell();
		board.getChildren().add(headerPane);
		
		monthPanel = new ScrollPane();
		monthPanel.setPrefWidth(stageWidth);
		monthPanel.setPrefHeight(stageHeight - headerPane.getHeight());
		board.getChildren().add(monthPanel);
		
		VBox listMonthsOfYear = new VBox();
		listMonthsOfYear.setMaxWidth(stageWidth);
		monthPanel.setContent(listMonthsOfYear);
		
		HBox monthsOfYear = new HBox();
		VBox monthRows = new VBox();
		HBox dayCols = new HBox();
		
		for (int day = 0; day < numOfDays; day++) {
			LocalDate currentDate = dateFirst.plusDays(day);

			long weekOfMonth = WeekFields.of(DayOfWeek.MONDAY, 1).weekOfMonth().getFrom(currentDate);
			int dayofweek = currentDate.get(ChronoField.DAY_OF_WEEK);
			int totalDayOfMonth = currentDate.lengthOfMonth();
			int currentDay = currentDate.getDayOfMonth();
			
			if(currentDay == 1) {
				
				monthsOfYear.getChildren().add(addMonthLabel(currentDate));
				
				if(dayofweek > 1) {
					addMissBlankCell(dayCols, 0, dayofweek, weekOfMonth);
				}
				
				dayCols.getChildren().add(addDateCell(currentDate, weekOfMonth));
				
				monthRows.getChildren().add(dayCols);
				
			} else if(currentDay == totalDayOfMonth) {
				
				if(dayofweek == 1) {
					dayCols = new HBox();
				}
				
				dayCols.getChildren().add(addDateCell(currentDate, weekOfMonth));
				
				if(dayofweek < 7) {
					addMissBlankCell(dayCols, dayofweek, 8, weekOfMonth);
				}
				
				if(!monthRows.getChildren().contains(dayCols)) {
					monthRows.getChildren().add(dayCols);
				}
				
				monthsOfYear.getChildren().add(monthRows);
				listMonthsOfYear.getChildren().add(monthsOfYear);
				
				if(currentMonthObject == null && currentDate.getMonthValue() == monthNow + 1) {
					currentMonthObject = monthsOfYear;
				}
				
				dayCols = new HBox();
				monthRows = new VBox();
				monthsOfYear = new HBox();
				
			} else {
				if(dayofweek == 1) {
					dayCols = new HBox();
				}
				
				dayCols.getChildren().add(addDateCell(currentDate, weekOfMonth));
				
				if(dayofweek == 7) {
					if(!monthRows.getChildren().contains(dayCols)) {
						monthRows.getChildren().add(dayCols);
					}
				}
			}
		}
		
		return board;
	}
	
	@Override
	public void scrollToCurrentMonth() {
		Platform.runLater(() -> {
			monthPanel.setVvalue(this.currentMonthObject.getBoundsInLocal().getMaxY() / monthPanel.getBoundsInLocal().getHeight());
		});
	}
	
	private AnchorPane createHeaderCell() {
		AnchorPane c = new AnchorPane();
		c.setPrefHeight(30);
		c.setPrefWidth(this.stageWidth / 7);
		
		options.getCellHeaderStyles().forEach((k, v) -> c.setStyle(c.getStyle() + ";" + k + ": " + v));
		
		return c;
	}
	
	private BorderPane addMonthLabel(LocalDate currentDate) {
		Text monLabel = new Text(DateTimeFormatter.ofPattern("MMMM").format(currentDate));
		monLabel.setFont(Font.font(18));
		monLabel.setRotate(-90);
		BorderPane mLabel = new BorderPane();
		mLabel.setMaxWidth(60);
		mLabel.setMinWidth(60);
		mLabel.setPrefWidth(60);
		mLabel.setCenter(monLabel);
		
		options.getCellMonthStyles()
			.forEach((k, v) -> mLabel.setStyle(mLabel.getStyle() + ";" + k + ": " + v));
		
		return mLabel;
	}
	
	private void addMissBlankCell(HBox row, int from, int to, long weekOfMonth) {
		for (int i = from; i < to - 1; i++) {
			AnchorPane c = cell(true, weekOfMonth == 1);
			row.getChildren().add(c);
		}
	}
	
	private AnchorPane addDateCell(LocalDate currentDate, long weekOfMonth) {
		Text t = new Text(Integer.toString(currentDate.getDayOfMonth()));
		t.setLayoutX(5);
		t.setLayoutY(40);
		t.setFont(Font.font(18));
		AnchorPane c = cell(false, weekOfMonth == 1);
		c.getChildren().add(t);
		return c;
	}

	private AnchorPane cell(boolean blank, boolean isTop) {
		AnchorPane c = new AnchorPane();
		c.setPrefHeight(50);
		c.setPrefWidth((this.stageWidth - 60) / 7);
		
		if (blank) {
			options.getCellBlankStyles()
			.forEach((k, v) -> c.setStyle(c.getStyle() + ";" + k + ": " + v));
		} else {
			options.getCellDayStyles()
			.forEach((k, v) -> c.setStyle(c.getStyle() + ";" + k + ": " + v));
		}
		
		String border = "-fx-border-width: 1";
		if(isTop) {
			border = "-fx-border-width: 2 1 1 1";
		}
		c.setStyle(c.getStyle() + "; " + border);
		
		return c;
	}
	
}
