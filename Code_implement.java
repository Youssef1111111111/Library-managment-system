import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Colors {
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String MAGENTA = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";
}

class user implements Serializable {
    static Scanner scanner = new Scanner(System.in);
    public String name;
    private String email;
    private String password;

    public user() {
        name = "none";
        email = "none";
        password = "none";
    }

    public user(String nm, String em, String ps) {
        name = nm;
        email = em;
        password = ps;
    }

    public String getname() {
        return name;
    }

    public String getemail() {
        return email;
    }

    public String getps() {
        return password;
    }

    public void setname(String nm) {
        name = nm;
    }

    public void setemail(String em) {
        email = em;
    }

    public void setps(String ps) {
        password = ps;
    }
}

class account implements Serializable {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<user> users = new ArrayList<>();
    static final String FILE_PATH = "E:\\java\\userinfo.ser";

    private void loadUsersFromFile() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            users = new ArrayList<>();
            return;
        }

        FileInputStream fis = new FileInputStream(FILE_PATH);
        ObjectInputStream ois = new ObjectInputStream(fis);
        users = (ArrayList<user>) ois.readObject();
        ois.close();
        fis.close();
    }

    private void saveUsersToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_PATH);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(users);
        oos.close();
        fos.close();
    }

    public void Register() throws IOException, ClassNotFoundException {
        loadUsersFromFile();

        System.out.println(Colors.BLUE + "\n=====================================");
        System.out.println("           REGISTER ACCOUNT          ");
        System.out.println("=====================================" + Colors.RESET);

        
        System.out.print(Colors.CYAN + "Enter your name    : " + Colors.RESET);
        String nm = scanner.nextLine();

        System.out.print(Colors.CYAN + "Enter your email    : " + Colors.RESET);
        String em = scanner.nextLine();

        System.out.print(Colors.CYAN + "Enter your password    : " + Colors.RESET);
        String ps = scanner.nextLine();

        user newUser = new user(nm, em, ps);
        users.add(newUser);
        saveUsersToFile();

        System.out.println(Colors.GREEN + "User registered successfully!" + Colors.RESET);
    }

    public boolean Check_acc() throws IOException, ClassNotFoundException {
        loadUsersFromFile();

        System.out.print("Enter your email: ");
        String inputEmail = scanner.nextLine();
        System.out.print("Enter your password: ");
        String inputPassword = scanner.nextLine();

        for (user u : users) {
            if (u.getemail().equals(inputEmail) && u.getps().equals(inputPassword)) {
            System.out.println(Colors.GREEN + "Login successful!" + Colors.RESET);
            System.out.println(Colors.MAGENTA + "welcome " + u.getname() + "!"+ Colors.RESET);
            return true;
            
            }
        }

        System.out.println(Colors.RED + "Invalid email or password." + Colors.RESET);
        return false;
    }

    
}

// ====== Income Class ======
class Income implements Serializable {
    private static final long serialVersionUID = 1L;
    private float amount;
    private String date;
    private String name;

    public Income(float amount, String name, String date) {
        this.amount = amount;
        this.name = name;
        this.date = date;
    }

    public void editIncome(float newAmount, String targetName) {
        if (this.name != null && this.name.equals(targetName)) {
            this.amount = newAmount;
            System.out.println(ConsoleColors.GREEN + "Income updated successfully." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "Income not found or name mismatch!" + ConsoleColors.RESET);
        }
    }

    public void printIncome() {
        System.out.println(ConsoleColors.CYAN + "Income Details:" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + " Name: " + name + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + " Amount: " + amount + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + " Date: " + date + ConsoleColors.RESET);
    }
}

// ====== Expense Class ======
class Expense implements Serializable {
    private static final long serialVersionUID = 1L;
    private float amount;
    private String name;
    private String category;

    public Expense(float amount, String name, String category) {
        this.amount = amount;
        this.name = name;
        this.category = category;
    }

    public void editExpense(float newAmount, String targetName) {
        if (this.name != null && this.name.equals(targetName)) {
            this.amount = newAmount;
            System.out.println(ConsoleColors.GREEN + "Expense updated successfully." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "Expense not found or name mismatch!" + ConsoleColors.RESET);
        }
    }

    public void printExpense() {
        System.out.println(ConsoleColors.CYAN + "Expense Details:" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + " Name: " + name + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + " Amount: " + amount + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + " Category: " + category + ConsoleColors.RESET);
    }
}

// ====== Save Goal Class ======
class SaveGoal implements Serializable {
    private static final long serialVersionUID = 1L;
    private float goalAmount;
    private String description;

    public SaveGoal(float goalAmount, String description) {
        this.goalAmount = goalAmount;
        this.description = description;
    }

    public void editGoal(float newAmount) {
        this.goalAmount = newAmount;
        System.out.println(ConsoleColors.GREEN + "Goal updated successfully." + ConsoleColors.RESET);
    }

    public void printGoal() {
        System.out.println(ConsoleColors.CYAN + "Goal Details:" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + " Description: " + description + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + " Target Amount: " + goalAmount + ConsoleColors.RESET);
    }
}

// ====== File Helper ======
class FileHelper {
    public static void saveObject(Object obj, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(obj);
            System.out.println(ConsoleColors.GREEN + "Saved successfully." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println(ConsoleColors.RED + "Save failed: " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    public static Object loadObject(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object obj = ois.readObject();
                System.out.println(ConsoleColors.GREEN + "Loaded successfully." + ConsoleColors.RESET);
                return obj;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(ConsoleColors.RED + "Load failed: " + e.getMessage() + ConsoleColors.RESET);
            }
        } else {
            System.out.println(ConsoleColors.RED + "No file found." + ConsoleColors.RESET);
        }
        return null;
    }

    public static void deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println(ConsoleColors.GREEN + " File deleted successfully." + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + " Failed to delete file." + ConsoleColors.RESET);
            }
        } else {
            System.out.println(ConsoleColors.RED + " No file to delete." + ConsoleColors.RESET);
        }
    }
}

// ====== Console Colors ======
class ConsoleColors {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
}

// ====== run Class ======
class Run{
    private static final String INCOME_FILE = "income.ser";
    private static final String EXPENSE_FILE = "expense.ser";
    private static final String GOAL_FILE = "goal.ser";
    Scanner scanner = new Scanner(System.in);
    
       

    public static void incomeMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nIncome Menu:");
            System.out.println("1. Add");
            System.out.println("2. Edit");
            System.out.println("3. View");
            System.out.println("4. Delete");
            System.out.println("5. Back");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter income name: ");
                    String inName = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    float inAmount = Float.parseFloat(scanner.nextLine());
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    String inDate = scanner.nextLine();
                    FileHelper.saveObject(new Income(inAmount, inName, inDate), INCOME_FILE);
                    break;
                case "2":
                    Income inEdit = (Income) FileHelper.loadObject(INCOME_FILE);
                    if (inEdit != null) {
                        System.out.print("Enter name to edit: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter new amount: ");
                        float amt = Float.parseFloat(scanner.nextLine());
                        inEdit.editIncome(amt, name);
                        FileHelper.saveObject(inEdit, INCOME_FILE);
                    }
                    break;
                case "3":
                    Income inView = (Income) FileHelper.loadObject(INCOME_FILE);
                    if (inView != null) inView.printIncome();
                    break;
                case "4":
                    FileHelper.deleteFile(INCOME_FILE);
                    break;
                case "5":
                    return;
                default:
                    System.out.println(ConsoleColors.RED + " Invalid option." + ConsoleColors.RESET);
            }
        }
    }

    public static void expenseMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nExpense Menu:");
            System.out.println("1. Add");
            System.out.println("2. Edit");
            System.out.println("3. View");
            System.out.println("4. Delete");
            System.out.println("5. Back");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter expense name: ");
                    String exName = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    float exAmount = Float.parseFloat(scanner.nextLine());
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    FileHelper.saveObject(new Expense(exAmount, exName, category), EXPENSE_FILE);
                    break;
                case "2":
                    Expense exEdit = (Expense) FileHelper.loadObject(EXPENSE_FILE);
                    if (exEdit != null) {
                        System.out.print("Enter name to edit: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter new amount: ");
                        float amt = Float.parseFloat(scanner.nextLine());
                        exEdit.editExpense(amt, name);
                        FileHelper.saveObject(exEdit, EXPENSE_FILE);
                    }
                    break;
                case "3":
                    Expense exView = (Expense) FileHelper.loadObject(EXPENSE_FILE);
                    if (exView != null) exView.printExpense();
                    break;
                case "4":
                    FileHelper.deleteFile(EXPENSE_FILE);
                    break;
                case "5":
                    return;
                default:
                    System.out.println(ConsoleColors.RED + " Invalid option." + ConsoleColors.RESET);
            }
        }
    }

    public static void goalMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nSave Goal Menu:");
            System.out.println("1. Add");
            System.out.println("2. Edit");
            System.out.println("3. View");
            System.out.println("4. Delete");
            System.out.println("5. Back");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter goal description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter target amount: ");
                    float amt = Float.parseFloat(scanner.nextLine());
                    FileHelper.saveObject(new SaveGoal(amt, desc), GOAL_FILE);
                    break;
                case "2":
                    SaveGoal goal = (SaveGoal) FileHelper.loadObject(GOAL_FILE);
                    if (goal != null) {
                        System.out.print("Enter new target amount: ");
                        float newAmt = Float.parseFloat(scanner.nextLine());
                        goal.editGoal(newAmt);
                        FileHelper.saveObject(goal, GOAL_FILE);
                    }
                    break;
                case "3":
                    SaveGoal g = (SaveGoal) FileHelper.loadObject(GOAL_FILE);
                    if (g != null) g.printGoal();
                    break;
                case "4":
                    FileHelper.deleteFile(GOAL_FILE);
                    break;
                case "5":
                    return;
                default:
                    System.out.println(ConsoleColors.RED + " Invalid option." + ConsoleColors.RESET);
            }
        }
    }
}

class ReminderApp {
    private ArrayList<Reminder> reminders = new ArrayList<>();
    private Timer timer = new Timer(true);
    private Scanner scanner;
    private final String SAVE_FILE = "reminders_data.ser";

    public ReminderApp(Scanner scanner) {
        this.scanner = scanner;
        loadReminders();
    }

    public void start() {
        System.out.println("Simple Reminder App (" + reminders.size() + " saved)");

        while (true) {
            System.out.println("\n1. Add Reminder");
            System.out.println("2. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                addReminder();
            } else if (choice.equals("2")) {
                saveAndExit();
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    private static class Reminder implements Serializable {
        String title;
        LocalDateTime when;

        Reminder(String t, LocalDateTime w) {
            title = t;
            when = w;
        }
    }

    private void addReminder() {
        try {
            System.out.print("\nReminder title: ");
            String title = scanner.nextLine();

            System.out.print("Reminder Date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.print("Reminder Time (HH:MM): ");
            LocalTime time = LocalTime.parse(scanner.nextLine());

            LocalDateTime when = LocalDateTime.of(date, time);

            if (when.isBefore(LocalDateTime.now())) {
                System.out.println("That's passed!");
                return;
            }

            Reminder r = new Reminder(title, when);
            reminders.add(r);
            scheduleReminder(r);
            System.out.println("Reminder done");
        } catch (Exception e) {
            System.out.println("Invalid date or time format.");
        }
    }

    private void scheduleReminder(Reminder r) {
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("\nREMINDER: " + r.title);
                System.out.println("Scheduled time: " + r.when.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            }
        }, Date.from(r.when.atZone(ZoneId.systemDefault()).toInstant()));
    }

    private void loadReminders() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            reminders = (ArrayList<Reminder>) in.readObject();
            for (Reminder r : reminders) {
                if (r.when.isAfter(LocalDateTime.now())) {
                    scheduleReminder(r);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing reminders found");
        }
    }

    private void saveAndExit() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            out.writeObject(reminders);
        } catch (IOException e) {
            System.out.println("Error saving reminders");
        }
        timer.cancel();
    }
}


class BudgetApp {

    public static class Budget implements Serializable {
        private String category;
        private double amount;
        private double spending;

        public Budget(String category, double amount, double spending) {
            this.category = category;
            this.amount = amount;
            this.spending = spending;
        }

        public void analyzeSpending() {
            System.out.println("\n--- Budget Analysis ---");
            System.out.println("Category: " + category);
            System.out.println("Budgeted Amount: $" + amount);
            System.out.println("Actual Spending: $" + spending);

            if (spending > amount) {
                System.out.println("You exceeded your budget");
            } else if (spending == amount) {
                System.out.println("You spent exactly your budget.");
            } else {
                System.out.println("Good! You saved $" + (amount - spending));
            }
        }

        public void saveToFile(String filename) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
                out.writeObject(this);
                System.out.println("Budget saved.");
            } catch (IOException e) {
                System.out.println("Error saving budget: " + e.getMessage());
            }
        }

        public static Budget loadFromFile(String filename) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
                return (Budget) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading budget: " + e.getMessage());
                return null;
            }
        }
    }

    private Scanner scanner;
    private Budget currentBudget;
    private final String SAVE_FILE;

    public BudgetApp(Scanner scanner, String saveFileName) {
        this.scanner = scanner;
        this.SAVE_FILE = saveFileName;
    }

    public void createBudget() {
        System.out.print("Enter Budget Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Budget Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Actual Spending: ");
        double spending = Double.parseDouble(scanner.nextLine());

        currentBudget = new Budget(category, amount, spending);
        currentBudget.analyzeSpending();
        currentBudget.saveToFile(SAVE_FILE);
    }

    public void loadBudget() {
        Budget loaded = Budget.loadFromFile(SAVE_FILE);
        if (loaded != null) {
            currentBudget = loaded;
            System.out.println("\nLoaded budget from file:");
            currentBudget.analyzeSpending();
        }
        else{ System.out.println(Colors.RED + "there is no budget in our data base add one then try again! " + Colors.RESET);}
    }

    public Budget getCurrentBudget() {
        return currentBudget;
    }
}

public class Code_implement {
    private static final String INCOME_FILE = "income.ser";
    private static final String EXPENSE_FILE = "expense.ser";
    private static final String GOAL_FILE = "goal.ser";
    static final String SAVE_FILE = "reminders_data.ser";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Colors colours = new Colors();
        account implement = new account();
        Run run = new Run();
        ReminderApp reminder = new ReminderApp(scanner);
        BudgetApp budgetApp = new BudgetApp(scanner, "budget.ser");
        
        while (true) {
            System.out.println(Colors.BLUE + "\n=====================================");
            System.out.println("         BUDGETING SYSTEM MENU        ");
            System.out.println("=====================================" + Colors.RESET);
            System.out.println(Colors.YELLOW + "Please choose an option:");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Exit" + Colors.RESET);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    implement.Register();
                    break;

                case "2":
                    if(implement.Check_acc()== true){
                        while (true) {
                            
                            System.out.println("\nChoose a section:");
                            System.out.println("1. Income");
                            System.out.println("2. Expense");
                            System.out.println("3. Save Goal");
                            System.out.println("4. Reminder app");
                            System.out.println("5. Budget app");
                            System.out.println("6. Exit");
                            System.out.print("Enter choice (1-6): ");
                            String section = scanner.nextLine();

                        switch (section) {
                            case "1":
                                run.incomeMenu(scanner);
                                break;
                            case "2":
                                run.expenseMenu(scanner);
                                break;
                            case "3":
                                run.goalMenu(scanner);
                            
                                break;

                            case "4": 
                                reminder.start();
                                break;

                            case "5": 
                                while (true) {
                                    System.out.println("\n=== Budget Menu ===");
                                    System.out.println("1. Create New Budget");
                                    System.out.println("2. Load Budget from File");
                                    System.out.println("3. Analyze Current Budget");
                                    System.out.println("4. Exit");
                                    System.out.print("Choose an option: ");

                                    String section_2 = scanner.nextLine();

                                    switch (section_2) {
                                        case "1":
                                            budgetApp.createBudget();
                                            break;
                                        case "2":
                                            budgetApp.loadBudget();
                                            break;
                                        case "3":
                                            if (budgetApp.getCurrentBudget() != null) {
                                                budgetApp.getCurrentBudget().analyzeSpending();
                                                } 
                                            else 
                                            {
                                            System.out.println("No budget loaded or created yet.");
                                            }
                                                break;
                                        case "4":
                                            System.out.println("Goodbye!");
                                            scanner.close();
                                                return;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                        }
                                        break;
                                    }                               
                            case "6":
                                System.out.println(ConsoleColors.YELLOW + " Goodbye!" + ConsoleColors.RESET);
                                scanner.close();
                                return;
                            default:
                                System.out.println(ConsoleColors.RED + " Invalid section." + ConsoleColors.RESET);
                                }
                        }  
                    } 
                                               
                        break;
                case "3":
                    System.out.println(Colors.BLUE + "Exiting the system. Goodbye!" + Colors.RESET);
                    System.exit(0);

                default:
                    System.out.println(Colors.RED + "Invalid option. Please try again." + Colors.RESET);
            }
        }
    }
}

