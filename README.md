# FXCalendar

Building a year calendar for javaFX

<img  width="458"  alt="image"  src="https://github.com/user-attachments/assets/03cdc96d-fe35-4f60-bab0-cee74f79b3b4">


## Usage example:

    StackPane board = new StackPane();
    Options options = new Options();
    options.setWidth(640);
    options.setHeight(480);
    ICalendarFX calendar = new CalendarFX(board, options);
    calendar.scrollToCurrentMonth();
    
    var scene = new Scene(board, 640, 480);
    stage.setScene(scene);
    stage.show();

This is the first version, please contribute to add more features, thanks.
