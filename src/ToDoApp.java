import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        int selection;

        boolean isRunning = true;

        while(isRunning) {
            System.out.println(" ");
            System.out.println("        Task List \uD83D\uDDF3️       ");
            System.out.println();
            System.out.println("1.      Add Task ➕️       ");
            System.out.println("2.      View Task \uD83D\uDD0E️       ");
            System.out.println("3.      Mark Task Done ✅️       ");
            System.out.println("4.      Undo Mark Task ↩️");
            System.out.println("5.      Remove Task \uD83D\uDDD1️       ");
            System.out.println("6.      Save & Exit \uD83D\uDCBE       ");
            System.out.println("Press a selection (1-6): ");
            selection = scanner.nextInt();
            System.out.println(" ");

            switch(selection) {
                case 1 -> addTask(tasks, scanner);
                case 2 -> viewTask(tasks, scanner);
                case 3 -> completeTask(tasks, scanner);
                case 4 -> undoCompleteTask(tasks, scanner);
                case 5 -> removeTask(tasks, scanner);
                case 6 -> System.out.println("You are saving and exiting");
                default -> System.out.println("Invalid choice");
            }
        }
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("          Goodbye !         ");
        scanner.close();
    }

    public static void addTask(List<Task> tasks, Scanner scanner) {
        scanner.nextLine(); // clear newline left from nextInt
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        Task newTask = new Task(description, false);
        tasks.add(newTask);

        System.out.println("Task added!");
    }

    public static void viewTask(List<Task> tasks, Scanner ignoredScanner) {
        if (tasks.isEmpty()) {
            System.out.println("No current tasks. \n");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.isDone() ? "✅" : "❌";
            System.out.println((i + 1) + ".  " + task.getDescription() + " " + status);

        }
    }

    public static void completeTask(List<Task> tasks, Scanner scanner) {
        // display tasks
        viewTask(tasks, scanner);
        // default for no tasks
        if(tasks.isEmpty()) {
            System.out.println("No tasks placed to be completed ");
            return;
        }
        // user selection for which task to complete
        System.out.println("Select task number to be completed ");
        int taskNumber;
        try {
            taskNumber = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number matching an existing task.");
            scanner.nextLine();
            return;
        }

        // updating lists from incomplete to complete
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1); // -1 because list index starts at 0
            if (!task.isDone()) {
                task.setDone(true);
                System.out.println("Task marked as done ✅: " + task.getDescription());
            } else {
                System.out.println("This task is already marked as done.");
            }
        } else {
            System.out.println("Invalid task number.");
        }
        scanner.nextLine();
    }

    public static void undoCompleteTask(List<Task> tasks, Scanner scanner) {
        viewTask(tasks,scanner);

        if (tasks.isEmpty()) {
            System.out.println("No task to undo");
            return;
        }

        System.out.println("Enter the task number to undo indicated with a ✅");
        int taskNumber;

        try {
            taskNumber = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid task number to undo select a number with -> ✅");
            scanner.nextLine();
            return;
        }
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);

            if (task.isDone()) {
                task.setDone(false);
                System.out.println("Task marked as NOT done ❌: " + task.getDescription());
            } else {
                System.out.println("This task is already not done.");
            }
        } else {
            System.out.println("Invalid task number.");
        }
        scanner.nextLine();
    }

    public static void removeTask(List<Task> tasks, Scanner scanner) {
        viewTask(tasks, scanner);

        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }

        System.out.print("Enter the number of the task to remove: ");

        int taskNumber;
        try {
            taskNumber = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }

        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println("Removed task: " + removedTask.getDescription());
        } else {
            System.out.println("Invalid task number.");
        }

        scanner.nextLine();
    }


}
