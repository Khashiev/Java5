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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter message ID");
            String line = scanner.nextLine();

            if (line.equals("exit")) {
                System.exit(0);
            }

            Long id = Long.parseLong(line);
            Optional<Message> message = repository.findById(id);

            if (message.isPresent()) {
                System.out.println(message.get());
            } else {
                System.out.println("Message not found");
            }
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
            e.printStackTrace();
        }
    }
}
