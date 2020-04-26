package com.ss.lms.entity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.DateTimeSyntax;

import com.ss.lms.service.AdminService;
import com.ss.lms.service.BorrowerService;
import com.ss.lms.service.LibraryService;

public class Interface extends Format {

	public static void main(String[] args) {
		boolean app = true;
		boolean main = true;

		boolean mainLib = false;
		boolean mainLib1 = false;
		boolean mainAdmin = false;
		boolean mainBor = false;
		int selection = 0;
		String input = "";
		int bookSelectionIndex;
		int branchSelectionIndex;
		Scanner userInput = new Scanner(System.in);
		while (app == true) {
			while (main == true) {
				mainMenu();
				selection = userInput.nextInt();
				switch (selection) {
				case 1:
					mainLib = true;
					while (mainLib == true) {
						libMenu();
						selection = userInput.nextInt();
						main = false;
						switch (selection) {
						case 1: // display branches
							LibraryService librarySer = new LibraryService();
							List<LibraryBranch> branches;
							try {
								branches = librarySer.readLibraryBranches(null);
								int quitNum = branches.size() + 1;
								System.out.println("\t" + quitNum + ") Quit to previous");
								branchSelectionIndex = userInput.nextInt();
								if (branchSelectionIndex != quitNum) {// checking if quit
									mainLib1 = true;
								} else {
									mainLib1 = false;
								}
								while (mainLib1 == true) {
									libMenu1();
									selection = userInput.nextInt();
									LibraryBranch branch = branches.get(branchSelectionIndex - 1);
									switch (selection) {
									case 1:// lib1
										System.out.println("You have chosen to update the Branch with Branch Id: "
												+ branch.getBranchId() + " and Branch Name: " + branch.getBranchName()
												+ ".");
										System.out.println("Enter ‘quit’ at any prompt to cancel operation.\n");
										System.out.println("Please enter new branch name or enter N/A for no change: ");
										userInput.nextLine();
										input = userInput.nextLine();
										if (input.toLowerCase().contains("quit") == false) {// checking if quit
											if (input.toLowerCase().contains("n/a") == false) {
												branch.setBranchName(input);
												System.out.println("name " + input);
											}
											System.out.println(
													"Please enter new branch address or enter N/A for no change:");
											input = userInput.nextLine();
											userInput.nextLine();
										
										}
										if (input.toLowerCase().contains("quit") == false) {
											if (input.toLowerCase().contains("n/a") == false) {
												branch.setBranchAddress(input);
												System.out.println("address " + input);
											}
											librarySer.saveLibraryBranch(branch);// updating branch info
										}
										mainLib1 = false;
										break;
									case 2: // lib2
										System.out.println("Pick the Book you want to add copies of, to your branch: ");
										List<Book> books = librarySer.readBook(null,null);
										quitNum = books.size() + 1;
										System.out.println("\t" + quitNum + ") Quit to previous");
										bookSelectionIndex = userInput.nextInt();
										
										if (bookSelectionIndex != quitNum) {// checking if quit
											List<BookCopies> copies = librarySer.readBookCopies(branch.getBranchId(),
													books.get(bookSelectionIndex - 1).getBookId());
									if(copies.size() == 0) {
										BookCopies copy = new BookCopies();
										copy.setBranchId(branch.getBranchId());
										librarySer.saveBookCopies(copy,books.get(bookSelectionIndex - 1).getBookId());
									}
									System.out.println("Existing number of copies: " + copies.get(0).getNoOfCopies());
									System.out.println("Enter new number of copies: ");
									selection = userInput.nextInt();
									copies.get(0).setNoOfCopies(copies.get(0).getNoOfCopies() + selection);
									librarySer.saveBookCopies(copies.get(0),null);
									mainLib1 = false;
										} else {
											mainLib1 = false;
										}
										break;
									}

								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
							break;
						case 2: // quite menu
							main = true;
							mainLib = false;
							break;
						default:
							System.out.println(" Invalid Number.");
							break;
						}
					}
					break;
				case 2:
					mainAdmin = true;
					AdminService adminSer = new AdminService();
					while (mainAdmin == true) {
						adminMenu();
						int typeSelection = userInput.nextInt();
						main = false;
						if (typeSelection <= 5) {
							switch (selection) {
							case 1:
								Book book = new Book();
								adminMenu();
								selection = userInput.nextInt();
								if(selection <= 3) {
									if(selection == 1) {
										//ask add questions and set
									}else if(selection == 2) {
										//ask update questions and set
									}
									//adminSer.saveAuthors(author);
								}if(selection == 4) {
									//adminSer.readAuthors();
								}else{mainAdmin = false;}
								break;
							case 2:
								Genre genre = new Genre();
								adminMenu();
								selection = userInput.nextInt();
								if(selection <= 3) {
									if(selection == 1) {
										//ask add questions and set
									}else if(selection == 2) {
										//ask update questions and set
									}
									//adminSer.saveGenres(genre);
								}if(selection == 4) {
									//adminSer.readGenres();
								}else{mainAdmin = false;}
								break;
							case 3:
								Publisher publisher = new Publisher();
								adminMenu();
								selection = userInput.nextInt();
								if(selection <= 3) {
									if(selection == 1) {
										//ask add questions and set
									}else if(selection == 2) {
										//ask update questions and set
									}
									//adminSer.savePublisher(publisher);
								}if(selection == 4) {
									//adminSer.readPublishers();
								}else{mainAdmin = false;}
								break;
							case 4:
								LibraryBranch branch = new LibraryBranch();
								adminMenu();
								selection = userInput.nextInt();
								if(selection <= 3) {
									if(selection == 1) {
										//ask add questions and set
									}else if(selection == 2) {
										//ask update questions and set
									}
									//adminSer.saveLibraryBranch(branch);
								}if(selection == 4) {
									//adminSer.readLibraryBranch();
								}else{mainAdmin = false;}
								break;
							case 5:
								Borrower borrower = new Borrower();
								adminMenu();
								if(selection <= 3) {
									if(selection == 1) {
										//ask add questions and set
									}else if(selection == 2) {
										//ask update questions and set
									}
									//adminSer.saveBorrower(borrower);
								}
								if(selection == 4) {
									//adminSer.readBorrower();
								}else{mainAdmin = false;}
								break;
							default:
								mainAdmin = false;
								break;
							}
							
							
						}
						if (typeSelection == 6) {
							BookLoan loan = new BookLoan();
							boolean notfound = true;
							while (notfound == true) {
								System.out.println("Enter BookId: ");
								loan.setBookId(userInput.nextInt());
								System.out.println("Enter BrandId: ");
								loan.setBranchId(userInput.nextInt());
								System.out.println("Enter Card Number: ");
								loan.setCardNo(userInput.nextInt());
								List<BookLoan> loans;
								try { // show dates out if possiable
									loans = adminSer.readBookLoan(loan, null);
									if (loans.size() > 0) {
										int counter = 1;
										System.out.println("Select Date Out: ");
										for (BookLoan a : loans) {
											System.out.println("\t" + counter + ") " + a.getDateOut());
											counter++;
										}
										selection = userInput.nextInt();// pick book
										loan.setDateOut(loans.get(selection - 1).getDateOut());
										notfound = false;
									} else {
										System.out.println("No book loans found.");
										mainAdmin = false;
									}
								} catch (SQLException e) {
									e.printStackTrace();
								}

							}
							System.out.println("Enter new due date yyyy-mm-dd: ");
							input = userInput.next();
							try {
								Date newDate = Date.valueOf(input);// converting string into sql date
								loan.setDueDate(newDate);
								adminSer.saveBookLoan(loan);// update due date
								System.out.println("Due date was changed.");
								mainAdmin = false;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} else {
							System.out.println(" Invalid Number.");
						}
					}

					break;
				case 3:
					mainBor = true;
					while (mainBor == true) {
						borrowerMenu();
						BorrowerService borrowerSer = new BorrowerService();
						try {
							Borrower borrower = borrowerSer.readBorrowers(userInput.nextInt(), null).get(0);
							System.out.println(" Welcome " + borrower.getBorrowerName() + "\n");
							borrowerMenu2();
							selection = userInput.nextInt();
							switch (selection) {
							case 1:// check out book, new loan
								System.out.println(" Select a Library\n");
								List<LibraryBranch> branches = borrowerSer.readLibraryBranch(null, false);// show all
																											// branches
								branchSelectionIndex = userInput.nextInt();
								System.out.println(" " + branches.get(branchSelectionIndex - 1).getBranchName());
								
								// display book with at least one copy in branch;
								List<Book> booksInBranch = borrowerSer.readBook(branches.get(branchSelectionIndex - 1).getBranchId(), null);
								int bookCounter = 1;
								if (booksInBranch.isEmpty() == false) { // show book
									for (Book a : booksInBranch) {
										System.out.println("\t" + bookCounter + ") " + a.getTitle());
										bookCounter++;
									}
									bookSelectionIndex = userInput.nextInt(); // select book
									BookLoan newLoan = new BookLoan();
									newLoan.setBookId(booksInBranch.get(bookSelectionIndex - 1).getBookId());
									newLoan.setBranchId(branches.get(branchSelectionIndex - 1).getBranchId());
									newLoan.setCardNo(borrower.getCardNo());
									borrowerSer.saveBookLoan(newLoan, -1);
									System.out.println("\n Enjoy your book.");
								} else {
									System.out.println("\n All books are checked out."); // no book where
																							// found
								}
								break;
							case 2: // returns book
								System.out.println(" Select a Library\n");
								List<LibraryBranch> branch = borrowerSer.readLibraryBranch(borrower.getCardNo(), true);// show
																														// all
																														// branches
								branchSelectionIndex = userInput.nextInt();

								System.out.println(" " + branch.get(branchSelectionIndex - 1).getBranchName());
								List<Book> book = borrowerSer.readBook(borrower.getCardNo(),
										branch.get(branchSelectionIndex - 1).getBranchId());

								int counter = 1;
								if (book.isEmpty() == false) { // show loan book
									for (Book a : book) {
										System.out.println("\t" + counter + ") " + a.getTitle());
										counter++;
									}
									bookSelectionIndex = userInput.nextInt();
									BookLoan loan = new BookLoan(); //create new loan object
									loan.setBookId(book.get(bookSelectionIndex - 1).getBookId());
									loan.setBranchId(branch.get(branchSelectionIndex - 1).getBranchId());
									loan.setCardNo(borrower.getCardNo());
									List<BookLoan> loans = borrowerSer.readBookloans(loan);

									borrowerSer.saveBookLoan(loans.get(0), null); // update book dateIn time
									System.out.println(
											"\n " + book.get(bookSelectionIndex - 1).getTitle() + " was returned.\n");
								} else {
									System.out.println("\n You have no books to return."); // no loans where found
								}
								mainBor = false;
								break;
							case 3: // back to main menu
								mainBor = false;
								break;
							default:
								System.out.println(" Invalid Option.");
								break;
							}
						} catch (SQLException e) {
							System.out.println(" Invalid Card Number.");
							e.printStackTrace();
						}
					}
					break;
				default:
					System.out.println(" Opps try again, please enter 1, 2 or 3.");
					break;
				}
			}

		}

	}
}
