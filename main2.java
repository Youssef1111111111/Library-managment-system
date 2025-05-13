//Name: youssef yasser elsayed
//Id: 20230519
//section: s23,s24



import java.util.ArrayList;
import java.util.Scanner;


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

class user{
    static Scanner scanner = new Scanner(System.in);
    private String name;
    private String ID;
    private String membership;
    private int userLimit;

    public user(){
        name="none";
        ID= "none";
        membership= "none";
    }

    public user(String nm,String id, String member){
        name=nm;
        ID= id;
        membership= member;

    }

    public String getname()
    {
        return name;
    }
    public String getID()
    {
        return ID;
    }
    public void setname(String nm )
    {
        name = nm ;
    }
    public void setID(String id )
    {
        ID = id ;
    }
    public String getmember()
    {
        return membership;
    }
    public void setmember(String member )
    {
        membership = member ;
    }
    public void setUserLimit(int limit){
        userLimit = limit ;
    }
    public int getUserLimit(){
        return userLimit;
    }





}


class book{
    static Scanner scanner = new Scanner(System.in);
    static Colors colours = new Colors();


    String title;
    String author;
    int year;
    boolean is_borrowed = false;
    String borrowed_by = "";

    public book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public void displayBook() {
        System.out.println(Colors.GREEN + " Title: "  + title + Colors.RESET);
        System.out.println(Colors.RED + " Author: "  + author + Colors.RESET);
        System.out.println(Colors.YELLOW + " Year: "  + year + Colors.RESET);
        if (is_borrowed) {
            System.out.println(Colors.MAGENTA + " Status: Borrowed by " + borrowed_by + Colors.RESET);
        }
        else
        {
            System.out.println(Colors.CYAN + " Status: Available" + Colors.RESET);
        }

    }




}


class LibrarySystem {
    user user = new user("","","");
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<book> books = new ArrayList<>();
    static Colors colours = new Colors();
    static private int borrowed_books = 0 ;

    public static void addBook() {
        System.out.println("Add Book ");
        System.out.println("please enter the title of the book");
        String title = scanner.nextLine();
        System.out.println("please enter the author name");
        String author = scanner.nextLine();
        System.out.println("please enter the year of publication");
        int year = scanner.nextInt();
        scanner.nextLine();

        book newBook = new book(title, author, year);
        books.add(newBook);
        System.out.println("book is added!");


    }


    public static void listBooks() {
        System.out.println(" List Books ");
        if (books.isEmpty()) {
            System.out.println(" No books in the library.");
            return;
        }

        System.out.println("\n List of Books:");
        for (book b : books) {
            b.displayBook();
            System.out.println("------------------");
        }
    }

    public static void searchBook() {

        System.out.println(Colors.MAGENTA + "\nSearch Book" + Colors.RESET);
        System.out.println(Colors.YELLOW + "1.search by title: " + Colors.RESET);
        System.out.println(Colors.YELLOW + "2.search by author:"+ Colors.RESET);
        System.out.println(Colors.YELLOW + "3.search by year:" +Colors.RESET);

        int choices = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        if (choices == 1) {
            System.out.print("Enter the book title: ");
            String searchTitle = scanner.nextLine();
            for (book b : books) {
                if (b.title.equalsIgnoreCase(searchTitle)) {

                    b.displayBook();
                    found = true;
                }
            }
        }
        else if (choices == 2) {
            System.out.print("Enter the author name: ");
            String searchAuthor = scanner.nextLine();
            for (book b : books) {
                if (b.author.equalsIgnoreCase(searchAuthor)) {
                    b.displayBook();
                    found = true;
                }
            }
        } else if (choices == 3) {
            System.out.print("Enter the publication year: ");
            int searchYear = scanner.nextInt();
            for (book b : books) {
                if (b.year == searchYear) {
                    b.displayBook();
                    found = true;
                }
            }
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        if (!found) {
            System.out.println(Colors.RED + "No matching books found." + Colors.RESET);
        }
    }




    public static void removeBook() {
        System.out.println(Colors.MAGENTA + "\nRemove Book" + Colors.RESET);
        System.out.println(Colors.RED + "first you need to search the book you want to remove" + Colors.RESET);


        System.out.print("Enter the title of the book to remove: ");
        String searchTitle = scanner.nextLine();

        boolean found = false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).title.equalsIgnoreCase(searchTitle)) {
                books.remove(i);
                System.out.println(Colors.RED + "Book removed successfully!" + Colors.RESET);
                found = true;
                borrowed_books--;
                break;
            }
        }

        if (!found) {
            System.out.println(Colors.RED + "Book not found!" + Colors.RESET);
        }
    }


    public  void borrowBook() {
        System.out.println(Colors.MAGENTA + "\nBorrow a Book" + Colors.RESET);
        System.out.print("Enter book title to borrow: ");
        String searchTitle = scanner.nextLine();

        if(user.getUserLimit() > borrowed_books){
            for (book b : books) {
                if (b.title.equalsIgnoreCase(searchTitle)) {
                    if (!b.is_borrowed) {
                        b.borrowed_by = user.getname();
                        b.is_borrowed = true;
                        System.out.println(Colors.YELLOW + "You have borrowed the book: " + b.title + Colors.RESET);
                        borrowed_books++;
                    }
                    else
                    {
                        System.out.println(Colors.RED + "Sorry, this book is already borrowed by " + b.borrowed_by + Colors.RESET);
                    }
                    return;
                }
            }
            System.out.println(Colors.RED + "Book not found!" + Colors.RESET);
        }
        else{
            System.out.println(Colors.RED + "You Reached the limit of  Borrowed Books!     - You Can Change Your Plan from the main menu -" + Colors.RESET );
            return;
        }

    }
    public static void returnBook() {
        System.out.println(Colors.MAGENTA + "\nReturn a Book" + Colors.RESET);
        System.out.print("Enter book title to return: ");
        String searchTitle = scanner.nextLine();

        for (book b : books) {
            if (b.title.equalsIgnoreCase(searchTitle)) {
                if (b.is_borrowed) {
                    b.is_borrowed = false;
                    System.out.println(Colors.GREEN + "Book returned successfully!" + Colors.RESET);
                    borrowed_books--;
                }
                else
                {
                    System.out.println(Colors.RED + "This book was not borrowed!" + Colors.RESET);
                }
                return;
            }
        }
        System.out.println(Colors.RED + "Book not found!" + Colors.RESET);
    }

    public void chooseyourplan(){
        String plan;
        System.out.println("Choose your plan");
        System.out.println("1- Free Plan  [ 2 Books Limit ] ");
        System.out.println("2- Standard Plan  [ 10 Books Limit ] ");
        System.out.println("3- Premium Plan  [ 20 Books Limit ] ");
        plan = scanner.nextLine();
        switch (plan) {
            case "1":
                user.setmember("Free");
                user.setUserLimit(2);
                break;
            case "2":
                user.setmember("Standard");
                user.setUserLimit(10);
                break;
            case "3":
                user.setmember("Premium");
                user.setUserLimit(20);
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
    }

    public void Register(){
        String nm, id;
        System.out.println("------------- Register -------------");
        System.out.println("Enter your name: ");
        nm = scanner.nextLine();
        user.setname(nm);
        System.out.println("Enter your id: ");
        id = scanner.nextLine();
        user.setID(id);
        chooseyourplan();

    }

    public void displayprofile(){
        System.out.println("------------- Profile -------------");
        System.out.println("Name: " + user.getname());
        System.out.println("ID: " + user.getID());
        System.out.println("Membership: " + user.getmember());
        System.out.println("Borrowed Books: " + borrowed_books);

    }






}






public class main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibrarySystem input = new LibrarySystem();
        Colors colours = new Colors();

        input.Register();

        while (true) {

            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Search Book");
            System.out.println("4. Remove Book");
            System.out.println("5. borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Change Your Plan");
            System.out.println("8. Profile");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();


            if (choice == 1) {
                input.addBook();
            }
            else if (choice == 2) {
                input.listBooks();
            }
            else if(choice == 3){
                input.searchBook();
            }
            else if(choice == 4 ){
                input.removeBook();

            }
            else if(choice == 5 ){
                input.borrowBook();

            }
            else if(choice == 6 ){
                input.returnBook();

            }
            else if (choice == 7) {
                input.chooseyourplan();
            }
            else if (choice == 8) {
                input.displayprofile();
            }
            else if (choice == 9) {
                System.out.println(Colors.YELLOW + "Exiting... " + Colors.RESET);
                break;
            }
            else {
                System.out.println(Colors.RED +"Invalid choice! Try again." + Colors.RESET);
            }

        }

        scanner.close();
    }
}