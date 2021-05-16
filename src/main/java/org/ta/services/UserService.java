package org.ta.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.ta.controllers.LoginController;
import org.ta.exceptions.LocationAlreadyExistsException;
import org.ta.exceptions.UsernameAlreadyExistsException;
import org.ta.exceptions.UsernameDoesNotExistException;
import org.ta.exceptions.WrongPasswordException;
import org.ta.model.Trip;
import org.ta.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

public class UserService {

    private static ObjectRepository<User> userRepository;
    private static Nitrite database;

    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("users.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    public static ObjectRepository<User> getUserRepository() {
        return userRepository;
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    static String encodePassword(String salt, String password) {
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

    public static String checkUserCredentials(String username, String password) throws UsernameDoesNotExistException, WrongPasswordException {

        int oku=0,okp=0,okr=0;
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()) &&
                    Objects.equals(encodePassword(username, password), user.getPassword()))
                return user.getRole();
            if(Objects.equals(username,user.getUsername())) {
                oku = 1;
            }
            if(Objects.equals(encodePassword(username,password),user.getPassword()))
                okp = 1;
        }
        if( oku == 0 )
            throw new UsernameDoesNotExistException(username);
        if ( okp == 0 )
            throw new WrongPasswordException();

        return "";
    }

    public static String getLoggedUser(String username){
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                return username;
        }
        return "";
    }

    public static String getUserRole(String username){
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                if(Objects.equals(user.getRole(),"Customer"))
                    return "Customer";
                else
                    return "Travel Agency";
        }
        return "";
    }

    public static Nitrite getDatabase(){
        return database;
    }

    public static ArrayList<User> getAllUsers(){
        ArrayList<User> list = new ArrayList<>();
        for(User user : userRepository.find()) {
            list.add(user);
        }
        return list;
    }

    public static void checkUsernameDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username,user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static void checkUsernameAndPassword(String username,String password) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()) && Objects.equals(encodePassword(username, password), user.getPassword()))
                throw new UsernameAlreadyExistsException(username);
        }
    }
}
