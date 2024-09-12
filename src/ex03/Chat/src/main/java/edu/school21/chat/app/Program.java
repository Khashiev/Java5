package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessageRepositoryJdbcImpl;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.dbSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        dbSource db = new dbSource();
        updateDatabase("schema.sql", db);
        updateDatabase("data.sql", db);

        MessagesRepository repository = new MessageRepositoryJdbcImpl(db.getDataSource());
        Optional<Message> messageOptional = repository.findById(5L);

        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("Buy");
            message.setDateTime(null);
            repository.update(message);

            String msgStr = parseMsg(message.toString());
            System.out.printf("Message : %s\n", msgStr);
        } else {
            System.out.println("Message not found");
        }
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

    private static String parseMsg(String msg) {
        return msg.replaceFirst("\\{", "{\n\t")
                .replaceAll("author=", "\n\tauthor=")
                .replaceAll("chatroom=", "\n\tchatroom=")
                .replaceAll("text=", "\n\ttext=")
                .replaceAll("dateTime=", "\n\tdateTime=")
                .replaceAll("}$", "\n}");
    }
}
