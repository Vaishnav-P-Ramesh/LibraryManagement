// You are tasked with building a Library Management System using Object-Oriented Programming. The system should manage books, library members, and their borrowing activities. The library has different types of users: regular members and premium members, each with different borrowing privileges. The system should allow users to borrow and return books, track availability, and maintain a borrowing history.

// Creating a Book Class
// Implement a Book class with attributes for title, author, book ID, and availability status. Provide getter and setter methods.
// Add a getDetails() method to print the details of the book.

// Creating a Member Class
// Implement a Member class that can borrow and return books. Keep track of which books are borrowed by each member.
// Implement a getDetails() method that shows the member details and currently borrowed books.

// Regular Member Class
// Create a RegularMember class that inherits from Member and limits borrowing to a maximum of 3 books.

// Premium Member Class
// Implement a PremiumMember class that extends the Member class, allowing members to borrow up to 6 books and reserve books.

// Library Class
// Implement a Library class that manages books and members, handles borrowing and returning of books, and provides a list of available books.

// print the output for all queries given in input and at last print all available books in library


// Input Format
// Library Setup:
// First line: Three integers:
// n: Number of books in the library.
// m: Number of members in the library.
// q: Number of borrowing queries.

// Books Information:
// Next n lines: Each line contains:
// title author bookID

// Members Information:
// Next m lines: Each line contains:
// memberType name memberID
// Where memberType is 'p' for Premium and 'r' for Regular.
// Borrowing Requests:

// Next q lines: Each line contains:
// bookID memberID


// Constraints

// Output Format
// Output Format
// Borrowing Status Messages: Each borrowing attempt will result in a message indicating whether the borrowing was successful or if there was an issue:

// Successful Borrowing:
// When a member successfully borrows a book, the output will show:
// [Member Name] borrowed '[Book Title]'

// Example:
// Jason borrowed 'MobyDick'

// Book Not Available:
// If a member attempts to borrow a book that is already checked out, the output will indicate:
// '[Book Title]' is currently not available.

// Example:
// 'TheGrapesOfWrath' is currently not available.
// Exceeded Borrowing Limit:

// When a member reaches their borrowing limit, the output will show:
// [Member Name] cannot borrow more books.

// Example:
// Jason cannot borrow more books.

// Available Books List: After all borrowing requests are processed, the system will print a list of available books in the library. The format will be:
// Available Books:
// Title: [Book Title], Author: [Author Name], ID: [Book ID], Status: [Available/Not Available]

// Example:
// Available Books:
// Title: ThePictureOfDorianGray, Author: OscarWilde, ID: 4, Status: Available

// Summary of Output Example
// Successful borrowings are reported with the member's name and book title.
// If a book is not available, a message is displayed indicating its unavailability.
// If a member exceeds their borrowing limit, a message is shown stating they cannot borrow more books.
// Finally, the list of available books is displayed at the end, showing titles, authors, IDs, and their availability status.
// This format ensures clarity in tracking the borrowing activities of members and the availability of books within the library system.


// Sample Input
//  4 2 15
// BraveNewWorld AldousHuxley 1
// JackReacherSeries LeeChild 2
// TheGrapesOfWrath JohnSteinbeck 3
// ThePictureOfDorianGray OscarWilde 4
// r Deborah 1
// p Raymond 2
// 3 1
// 2 1
// 3 1
// 3 1
// 1 1
// 2 1
// 3 1
// 2 1
// 3 1
// 3 1
// 3 1
// 1 1
// 1 1
// 3 1
// 2 1
// Sample Output
//  Deborah borrowed 'TheGrapesOfWrath'
// Deborah borrowed 'JackReacherSeries'
// 'TheGrapesOfWrath' is currently not available.
// 'TheGrapesOfWrath' is currently not available.
// Deborah borrowed 'BraveNewWorld'
// Deborah cannot borrow more books.
// Deborah cannot borrow more books.
// Deborah cannot borrow more books.
// Deborah cannot borrow more books.
// Deborah cannot borrow more books.
// Deborah cannot borrow more books.
// Deborah cannot borrow more books.
// Deborah cannot borrow more books.
// Deborah cannot borrow more books.
// Deborah cannot borrow more books.
// Available Books:
// Title: ThePictureOfDorianGray, Author: OscarWilde, ID: 4, Status: Available


import java.util.*;
public class LibraryManagement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();// number of books in Library
		int m=sc.nextInt();// number of members in library
		int q=sc.nextInt();// number of queries
		Library l=new Library(n,m);
		for(int i=0;i<n;i++) {
			String title=sc.next();
			String author=sc.next();
			int bookID=sc.nextInt();
			l.addBook(new Book(title,author,bookID));
		}
		for(int i=0;i<m;i++) {
			char ch=sc.next().charAt(0);
			if(ch=='p') {
				String name=sc.next();
				int id=sc.nextInt();
				l.addMember(new PremiumMember(name,id));
			}
			else {
				String name=sc.next();
				int id=sc.nextInt();
				l.addMember(new RegularMember(name,id));
			}
		}
		for(int i=0;i<q;i++) {
			int bid=sc.nextInt();
			int mid=sc.nextInt();
			Member mem=l.getMember(mid);
			Book bk=l.getBook(bid);
			mem.borrowBook(bk);
		}
		l.listAvailableBooks();
	}

}

class Library {
    private Book[] books;
    private Member[] members;
    private int bookCount;
    private int memberCount;

    public Library(int maxBooks, int maxMembers) {
        this.books = new Book[maxBooks];
        this.members = new Member[maxMembers];
        this.bookCount = 0;
        this.memberCount = 0;
    }

    public void addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount] = book;
            bookCount++;
        } else {
            System.out.println("Library is full, cannot add more books.");
        }
    }

    public void addMember(Member member) {
        if (memberCount < members.length) {
            members[memberCount] = member;
            memberCount++;
        } else {
            System.out.println("Library is full, cannot add more members.");
        }
    }

    public void listAvailableBooks() {
        System.out.println("Available Books:");
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                System.out.println(books[i].getDetails());
            }
        }
    }
    public Member getMember(int id) {
    	for(Member m :members) {
    		if(m.getId()==id) {
    			return m;
    		}
    	}
    	return null;
    }
    public Book getBook(int id) {
    	for(Book b :books) {
    		if(b.getBookId()==id) {
    			return b;
    		}
    	}
    	return null;
    }
}
class Book {
    private String title;
    private String author;
    private int bookId;
    private boolean available;

    public Book(String title, String author, int bookId) {
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailability(boolean status) {
        this.available = status;
    }

    public String getDetails() {
        String status = available ? "Available" : "Not Available";
        return String.format("Title: %s, Author: %s, ID: %s, Status: %s", title, author, bookId, status);
    }
}
class Member {
    protected String name;
    protected int memberId;
    protected Book[] borrowedBooks;
    protected int borrowedCount;

    public Member(String name, int memberId, int maxBooks) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new Book[maxBooks];
        this.borrowedCount = 0;
    }
    public int getId() {
    	return memberId;
    }

    public void borrowBook(Book book) {
        if (borrowedCount < borrowedBooks.length) {
            if (book.isAvailable()) {
                borrowedBooks[borrowedCount] = book;
                borrowedCount++;
                book.setAvailability(false);
                System.out.println(name + " borrowed '" + book.getTitle() + "'");
            } else {
                System.out.println("'" + book.getTitle() + "' is currently not available.");
            }
        } else {
            System.out.println(name + " cannot borrow more books.");
        }
    }

    public void returnBook(Book book) {
        boolean found = false;
        for (int i = 0; i < borrowedCount; i++) {
            if (borrowedBooks[i] == book) {
                borrowedBooks[i] = borrowedBooks[borrowedCount - 1]; // Replace the returned book with the last borrowed book
                borrowedBooks[borrowedCount - 1] = null; // Clear the last borrowed book
                borrowedCount--;
                book.setAvailability(true);
                System.out.println(name + " returned '" + book.getTitle() + "'");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(name + " did not borrow '" + book.getTitle() + "'.");
        }
    }

    public String getDetails() {
        StringBuilder borrowedTitles = new StringBuilder();
        if (borrowedCount == 0) {
            borrowedTitles.append("No books borrowed.");
        } else {
            for (int i = 0; i < borrowedCount; i++) {
                borrowedTitles.append(borrowedBooks[i].getTitle()).append(", ");
            }
            borrowedTitles.setLength(borrowedTitles.length() - 2); // Remove trailing comma
        }
        return String.format("Member Name: %s, Member ID: %s, Borrowed Books: %s", name, memberId, borrowedTitles.toString());
    }
}
class RegularMember extends Member {
    private static final int MAX_BOOKS = 3;

    public RegularMember(String name, int memberId) {
        super(name, memberId, MAX_BOOKS);
    }
}
class PremiumMember extends Member {
    private static final int MAX_BOOKS = 6;

    public PremiumMember(String name, int memberId) {
        super(name, memberId, MAX_BOOKS);
    }
}