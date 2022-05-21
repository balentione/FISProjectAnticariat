package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.PublishBookException;
import org.loose.fis.sre.exceptions.SignUpException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.WrongCredentialsException;
import org.loose.fis.sre.model.BooksWithOffer;
import org.loose.fis.sre.model.PublishedBooks;
import org.loose.fis.sre.model.User;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    //for users
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String FNAME = "fname";
    public static final String LNAME = "lname";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";

    //for books
    public static final String CATEGORY = "category";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String NOPAG = "nopag";
    public static final String CONDITION = "condition";


    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = "/Users/gabriel-claudiuprivantu/Documents/FACULTATE AC_CTIro/AN2/SEM2/FIS/database1/test_registration-example";
        FileUtils.cleanDirectory(FileSystemService.APPLICATION_HOME_PATH.toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }

    @Test
    @DisplayName("Check if the database is init")
    void initDatabase() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    @DisplayName("Add new User")
    void addUser() throws UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,ROLE,FNAME,LNAME,EMAIL,PHONE);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(USERNAME);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(USERNAME, PASSWORD));
        assertThat(user.getRole()).isEqualTo(ROLE);
        assertThat(user.getFirst_name()).isEqualTo(FNAME);
        assertThat(user.getLast_name()).isEqualTo(LNAME);
        assertThat(user.getEmail()).isEqualTo(EMAIL);
        assertThat(user.getPhone()).isEqualTo(PHONE);
    }

    @Test
    @DisplayName("Add new Book")
    void addBook() {
        UserService.addBook(USERNAME,CATEGORY,TITLE,AUTHOR,NOPAG,CONDITION);
        assertThat(UserService.getAllPublishedBooks()).isNotEmpty();
        assertThat(UserService.getAllPublishedBooks()).size().isEqualTo(1);
        PublishedBooks publishedBooks = UserService.getAllPublishedBooks().get(0);
        assertThat(publishedBooks).isNotNull();
        assertThat(publishedBooks.getUsername()).isEqualTo(USERNAME);
        assertThat(publishedBooks.getCategory()).isEqualTo(CATEGORY);
        assertThat(publishedBooks.getTitle()).isEqualTo(TITLE);
        assertThat(publishedBooks.getAuthor()).isEqualTo(AUTHOR);
        assertThat(publishedBooks.getNumber_pag()).isEqualTo(NOPAG);
        assertThat(publishedBooks.getCondition()).isEqualTo(CONDITION);
    }

    @Test
    @DisplayName("Make an offer")
    void makeOffer() {
        UserService.makeOffer(USERNAME,USERNAME,CATEGORY,TITLE,AUTHOR,NOPAG,CONDITION);
        assertThat(UserService.getAllBooksWithOffer()).isNotEmpty();
        assertThat(UserService.getAllBooksWithOffer()).size().isEqualTo(1);
        BooksWithOffer booksWithOffer = UserService.getAllBooksWithOffer().get(0);
        assertThat(booksWithOffer).isNotNull();
        assertThat(booksWithOffer.getUsername()).isEqualTo(USERNAME);
        assertThat(booksWithOffer.getCustomer_username()).isEqualTo(USERNAME);
        assertThat(booksWithOffer.getCategory()).isEqualTo(CATEGORY);
        assertThat(booksWithOffer.getTitle()).isEqualTo(TITLE);
        assertThat(booksWithOffer.getAuthor()).isEqualTo(AUTHOR);
        assertThat(booksWithOffer.getNumber_pag()).isEqualTo(NOPAG);
        assertThat(booksWithOffer.getCondition()).isEqualTo(CONDITION);
    }

    @Test
    @DisplayName("Delete a published book")
    void deleteBook() {
        UserService.addBook(USERNAME,CATEGORY,TITLE,AUTHOR,NOPAG,CONDITION);
        PublishedBooks publishedBooks = UserService.getAllPublishedBooks().get(0);
        UserService.deleteBook(publishedBooks);
        assertThat(UserService.getAllPublishedBooks()).isEmpty();
    }

    @Test
    void deleteOffer() {
        UserService.makeOffer(USERNAME,USERNAME,CATEGORY,TITLE,AUTHOR,NOPAG,CONDITION);
        BooksWithOffer booksWithOffer = UserService.getAllBooksWithOffer().get(0);
        UserService.deleteOffer(booksWithOffer);
        assertThat(UserService.getAllBooksWithOffer()).isEmpty();
    }

    @Test
    @DisplayName("Check if all the fields are filled for the PublishBook form")
    void checkFilledInformationsPublishBook() throws PublishBookException {
        assertThrows(PublishBookException.class, () -> {
            UserService.addBook(USERNAME,CATEGORY,"",AUTHOR,"",CONDITION);
            PublishedBooks publishedBooks = UserService.getAllPublishedBooks().get(0);
            UserService.checkFilledInformationsPublishBook(publishedBooks.getCategory(),publishedBooks.getTitle(),publishedBooks.getAuthor(),publishedBooks.getNumber_pag(),publishedBooks.getCondition());
        });
    }

    @Test
    @DisplayName("Check username doesn't already exists in the database")
    void checkUserDoesNotAlreadyExist() throws UsernameAlreadyExistsException{
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(USERNAME,PASSWORD,ROLE,FNAME,LNAME,EMAIL,PHONE);
            UserService.addUser(USERNAME,PASSWORD,ROLE,FNAME,LNAME,EMAIL,PHONE);
        });
    }

    @Test
    @DisplayName("Check if the provided credentials are correct")
    void checkCredentials() throws WrongCredentialsException{
        assertThrows(WrongCredentialsException.class, () -> {
            UserService.addUser(USERNAME,PASSWORD,ROLE,FNAME,LNAME,EMAIL,PHONE);
            User user = UserService.getAllUsers().get(0);
            UserService.checkCredentials(user.getUsername(),"altceva",user.getRole());
        });
    }

    @Test
    @DisplayName("Check if all the fields are filled for the SignUp form")
    void checkFilledInformationsSignUp() throws SignUpException {
        assertThrows(SignUpException.class, () -> {
            UserService.addUser(USERNAME,PASSWORD,ROLE,FNAME,LNAME,"",PHONE);
            User user = UserService.getAllUsers().get(0);
            UserService.checkFilledInformationsSignUp(user.getUsername(),user.getPassword(),user.getRole(),user.getFirst_name(),user.getLast_name(),user.getEmail(),user.getPhone());
        });
    }
}