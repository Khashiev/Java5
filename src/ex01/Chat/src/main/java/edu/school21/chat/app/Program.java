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

        Scanner in = new Scanner(System.in);
        System.out.println("Enter message ID");
        System.out.print("-> ");

        if (in.hasNextLong()) {
            String line = in.nextLine();
            Long id = Long.parseLong(line);
            Optional<Message> message = repository.findById(id);

            if (message.isPresent()) {
                String msgStr = parseMsg(message.get().toString());
                System.out.printf("Message : %s\n", msgStr);
            } else {
                System.out.println("Message not found");
            }
        } else {
            System.out.println("Invalid ID");
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
            throw new RuntimeException(e);
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
