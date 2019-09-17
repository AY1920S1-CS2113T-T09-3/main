package com.nwjbrandon.duke.services.task;

import com.joestelmach.natty.Parser;
import com.nwjbrandon.duke.exceptions.DukeWrongCommandFormatException;
import com.nwjbrandon.duke.services.ui.Terminal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Task {

    /**
     * Task name.
     */
    String taskName;

    /**
     * Task done status.
     */
    private boolean isDone;

    /**
     * Task date.
     */
    String by = "";

    /**
     * Task name and date.
     */
    private String taskDescription = "";

    /**
     * Converts a string to a Date object
     * @param dateString the string containing the date
     * @return The corresponding Date object if the dateString is valid
     * @throws ParseException if there is no date found in the dateString
     */
    public static Date parseDate (String dateString) throws ParseException {
        Parser dateParser = new Parser();
        Date date = null;
        try {
            date = dateParser.parse(dateString).get(0).getDates().get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException("",0);
        }
        return date;

    }

    /**
     * Create task.
     * @param taskDescription description of task.
     * @param numberOfTasks number of tasks.
     * @throws DukeWrongCommandFormatException command in wrong format.
     */
    Task(String taskDescription, int numberOfTasks) throws DukeWrongCommandFormatException {
        this(taskDescription, numberOfTasks, false);
    }

    /**
     * Create task.
     * @param taskDescription description of tasks.
     * @param numberOfTasks number of tasks.
     * @param isDone done status.
     * @throws DukeWrongCommandFormatException command in wrong format.
     */
    private Task(String taskDescription, int numberOfTasks, boolean isDone) throws DukeWrongCommandFormatException {
        this.taskDescription = this.formatTaskName(taskDescription);
        this.isDone = isDone;
        this.addTaskString(numberOfTasks);
    }

    /**
     * Format task name.
     * @param taskDescription description of task.
     * @return formatted task name.
     * @throws DukeWrongCommandFormatException command in wrong format.
     */
    public abstract String formatTaskName(String taskDescription) throws DukeWrongCommandFormatException;

    /**
     * Get the task date.
     * @return task date.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Get the task description.
     * @return task description.
     */
    String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Get the task name.
     * @return task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Get the done status.
     * @return done status.
     */
    public boolean getIsDoneStatus() {
        return isDone;
    }

    /**
     * Set the done status.
     * @param isDone done status.
     */
    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
        this.showSetDoneStatus();
    }

    /**
     * Show the set done status message.
     */
    private void showSetDoneStatus() {
        Terminal.showSetDoneStatus(this.toTaskDescriptionString());
    }

    /**
     * Get icon for done status.
     * @return icon for done status.
     */
    String getStatusIcon() {
        return (isDone ? "✓" : "✗");
    }

    /**
     * Get the task description.
     * @return task description.
     */
    public String toTaskDescriptionString() {
        return "[" + this.getStatusIcon() + "] " + this.getTaskDescription();
    }

    /**
     * Format date from file input.
     * @param originalDate representation of date from file input.
     * @return representation of date in terminal.
     * @throws ParseException incorrect format of date.
     */
    String dateFormatter(String originalDate) throws ParseException {
        String pattern = "dd/MM/yyyy hhmm";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = formatter.parse(originalDate);

        pattern = "d";
        formatter = new SimpleDateFormat(pattern);
        final String a = formatter.format(date);

        pattern = "MMMM yyyy, h";
        formatter = new SimpleDateFormat(pattern);
        final String b = formatter.format(date);

        pattern = "a";
        formatter = new SimpleDateFormat(pattern);
        final String c = formatter.format(date).toLowerCase();

        String symbol = "";
        switch (a) {
        case "1":
            symbol = "st";
            break;
        case "2":
            symbol = "nd";
            break;
        case "3":
            symbol = "rd";
            break;
        default:
            symbol = "th";
            break;
        }

        return "" + a + symbol + " of " + b + c;
    }

    /**
     * Add task.
     * @param size number of tasks.
     */
    public void addTaskString(int size) {
        Terminal.showTaskActionString("\t added: ", this.getTaskDescription());
    }

    /**
     * Remove task.
     * @param size number of tasks.
     */
    public void removeTaskString(int size) {
        Terminal.showTaskActionString("\t removed: ", this.getTaskDescription());
    }
}
