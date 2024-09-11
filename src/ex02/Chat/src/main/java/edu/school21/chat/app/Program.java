package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessageRepositoryJdbcImpl;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.dbSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        dbSource db = new dbSource();
        updateDatabase("schema.sql", db);
        updateDatabase("data.sql", db);

        User user = new User(3L, "user", "user", new ArrayList<>(), new ArrayList<>());
        Chatroom chatroom = new Chatroom(1L, "room", user, new ArrayList<>());
        Message message = new Message(null, user, chatroom, "Hello!", LocalDateTime.now());

        MessagesRepository repository = new MessageRepositoryJdbcImpl(db.getDataSource());
        repository.save(message);

        System.out.println(message.getId());
    }

    private static void updateDatabase(String file, dbSource dbSource) {
        try (Connection connection = dbSource.getConnection();
             Statement statement = connection.createStatement()) {
            InputStream inputStream = Program.class.getClassLoader().getResourceAsStream(file);
            assert inputStream != null;
            Scanner scanner = new Scanner(inputStream).useDelimiter(";");

            while (scanner.hasNext()) {
                statement.execute(scanner.next().trim());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    private static String parseMsg(String msg) {
//        return msg.replaceFirst("\\{", "{\n\t")
//                .replaceAll("author=", "\n\tauthor=")
//                .replaceAll("chatroom=", "\n\tchatroom=")
//                .replaceAll("text=", "\n\ttext=")
//                .replaceAll("dateTime=", "\n\tdateTime=")
//                .replaceAll("}$", "\n}");
//    }
}
