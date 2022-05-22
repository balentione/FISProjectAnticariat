package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.PublishBookException;
import org.loose.fis.sre.exceptions.SignUpException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.WrongCredentialsException;
import org.loose.fis.sre.model.BooksWithOffer;
import org.loose.fis.sre.model.PublishedBooks;
import org.loose.fis.sre.model.SelledBooks;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;

    public static ObjectRepository<PublishedBooks> getBooksRepository() {
        return booksRepository;
    }

    private static ObjectRepository<PublishedBooks> booksRepository;

    private static ObjectRepository<BooksWithOffer> booksWithOfferRepository;

    public static ObjectRepository<BooksWithOffer> getBooksWithOfferRepository() {
        return booksWithOfferRepository;
    }

    private static ObjectRepository<SelledBooks> selledBooksRepository;

    public static ObjectRepository<SelledBooks> getSelledBooksRepository() {
        return selledBooksRepository;
    }
    private static Nitrite database;

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
        booksRepository = database.getRepository(PublishedBooks.class);
        booksWithOfferRepository = database.getRepository(BooksWithOffer.class);
        selledBooksRepository = database.getRepository(SelledBooks.class);
    }

    public static void addUser(String username, String password, String role, String first_name, String last_name, String email, String phone) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role, first_name, last_name, email, phone));
    }

    public static List<User> getAllUsers() {
        return userRepository.find().toList();
    }
    public static List<PublishedBooks> getAllPublishedBooks() {
        return booksRepository.find().toList();
    }
    public static List<BooksWithOffer> getAllBooksWithOffer() {
        return booksWithOfferRepository.find().toList();
    }
    public static List<SelledBooks> getAllSelledBooks() {
        return selledBooksRepository.find().toList();
    }

    public static void addBook(String username, String category, String title, String author, String number_pag, String condition){
        booksRepository.insert(new PublishedBooks(username, category, title, author, number_pag, condition));
    }

    public static void makeOffer(String username, String customer_username, String category, String title, String author, String number_pag, String condition){
        booksWithOfferRepository.insert(new BooksWithOffer(username, customer_username, category, title, author, number_pag, condition));
    }

    public static void deleteBook(PublishedBooks publishedBook){
        booksRepository.remove(publishedBook);
    }

    public static void deleteOffer(BooksWithOffer bookOffer){
        booksWithOfferRepository.remove(bookOffer);
    }

    public static void sellBook(String username, String customer_username, String category, String title, String author, String number_pag, String condition){
        selledBooksRepository.insert(new SelledBooks(username, customer_username, category, title, author, number_pag, condition));
    }

    public static void checkFilledInformationsPublishBook(String category, String title, String author, String number_pag, String condition) throws PublishBookException {
        if (category.isEmpty() || title.isEmpty() || author.isEmpty() || number_pag.isEmpty() || condition.isEmpty())
            throw new PublishBookException(category,title,author,number_pag,condition);
    }

    public static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static void checkCredentials(String username, String password, String role) throws WrongCredentialsException {
        int credentials_ok=0;

        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()) && Objects.equals(encodePassword(username, password), user.getPassword()) && Objects.equals(role, user.getRole()))
                credentials_ok=1;
        }

        if (credentials_ok==0)
            throw new WrongCredentialsException(username,password,role);
    }

    public static void checkFilledInformationsSignUp(String username, String password, String role, String first_name, String last_name, String email, String phone) throws SignUpException {
        if (username.isEmpty() || password.isEmpty() || role.isEmpty() || first_name.isEmpty() || last_name.isEmpty() ||email.isEmpty() || phone.isEmpty())
            throw new SignUpException(username,password,role,first_name,last_name,email,phone);
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static void close() {
        userRepository.close();
        database.close();
    }

}
