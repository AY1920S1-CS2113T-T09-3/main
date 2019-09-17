package com.nwjbrandon.duke.services.ui;

import com.nwjbrandon.duke.services.task.TaskList;

import java.util.Scanner;

public class Terminal {

    /**
     * Divider for messages.
     */
    private static String divider = "____________________________________________________________";

    /**
     * Scanner for input.
     */
    private Scanner scan = new Scanner(System.in);

    /**
     * Create user interface.
     */
    public Terminal() {
    }

    /**
     * Read input.
     * @return input.
     */
    public String readInput() {
        return scan.nextLine();
    }

    /**
     * Has input.
     * @return input.
     */
    public boolean hasInput() {
        return scan.hasNextLine();
    }

    /**
     * Show divider.
     */
    private static void printDivider() {
        System.out.println("\t" + Terminal.divider);
    }

    /**
     * Show greeting message.
     */
    public static void greetingMessage() {
        String output = "\t Hello! I'm Duke\n"
                + "\t What can I do for you?\n";
        Terminal.printDivider();
        System.out.print(output);
        Terminal.printDivider();
    }

    /**
     * Show farewell message.
     */
    public static void farewellMessage() {
        String output = "\t Bye. Hope to see you again soon!\n";
        Terminal.printDivider();
        System.out.print(output);
        Terminal.printDivider();
    }

    /**
     * Show list of tasks.
     * @param tasksList list of tasks.
     */
    public static void showTasksList(TaskList tasksList) {
        int size = tasksList.numberOfTasks();
        StringBuilder output = new StringBuilder("\t Here are the tasks in your lists:\n");
        for (int i = 0; i < size; i++) {
            output.append("\t ").append(i + 1).append(".")
                    .append(tasksList.getTask(i).toTaskDescriptionString())
                    .append("\n");
        }
        Terminal.printDivider();
        System.out.print(output);
        Terminal.printDivider();
    }

    /**
     * Show add task message.
     * @param taskDescription description of task.
     * @param numberOfTasks number of tasks.
     */
    public static void showAddTaskString(String taskDescription, int numberOfTasks) {
        String output =  "\t Got it. I've added this task:\n"
                + "\t   " + taskDescription + "\n"
                + "\t Now you have " + numberOfTasks + " tasks in the list.\n";
        Terminal.printDivider();
        System.out.print(output);
        Terminal.printDivider();
    }

    /**
     * Show remove task message.
     * @param taskDescription description of task.
     * @param size number of task.
     */
    public static void showRemoveTaskString(String taskDescription, int size) {
        String output =  "\t Noted. I've removed this task:\n"
                + "\t   " + taskDescription + "\n"
                + "\t Now you have " + (size - 1) + " tasks in the list.\n";
        Terminal.printDivider();
        System.out.print(output);
        Terminal.printDivider();
    }

    /**
     * Show added task message.
     * @param command command.
     * @param taskDescription description of task.
     */
    public static void showTaskActionString(String command, String taskDescription) {
        Terminal.printDivider();
        System.out.print(command + taskDescription + "\n");
        Terminal.printDivider();
    }

    /**
     * Shows the search task according to the tasklist.
     * @param tasksList The Tasklist which contains the tasks.
     * @param keyword The word being searched.
     */
    public static void showSearchTask(TaskList tasksList, String keyword) {
        StringBuilder output = new StringBuilder("\t Here are the matching tasks in your list:\n");
        int size = tasksList.numberOfTasks();
        int count = 1;
        for (int i = 0; i < size; i++) {
            if (tasksList.getTask(i).getTaskName().contains(keyword)) {
                output.append("\t ").append(count).append(".").append(tasksList.getTask(i)
                        .toTaskDescriptionString()).append("\n");
                count++;
            }
        }
        Terminal.printDivider();
        System.out.print(output);
        Terminal.printDivider();
    }

    /**
     * Show set status message.
     * @param taskDescription description of task.
     */
    public static void showSetDoneStatus(String taskDescription) {
        String output = "\t Nice! I've marked this task as done:\n"
                + "\t " + taskDescription + "\n";
        Terminal.printDivider();
        System.out.print(output);
        Terminal.printDivider();
    }

    /**
     * Show error message.
     * @param errorMessage error message.
     */
    public static void showError(String errorMessage) {
        Terminal.printDivider();
        System.out.print("\t " + errorMessage + "\n");
        Terminal.printDivider();
    }

}