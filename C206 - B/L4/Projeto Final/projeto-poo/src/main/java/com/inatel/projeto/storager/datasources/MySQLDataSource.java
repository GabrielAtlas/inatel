package com.inatel.projeto.storager.datasources;

import com.inatel.projeto.exceptions.DatabaseException;
import com.inatel.projeto.objects.Todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class MySQLDataSource  {

    private static final String tableName = "inatel_todos";
    private Connection connection;
    private ExecutorService executor;

    public MySQLDataSource(String ip, String database, String user, String password, Runnable onSuccess, Runnable onError) {
        super();
        this.openConnection(ip, database, user, password, onSuccess, onError);
        this.executor = Executors.newFixedThreadPool(3);
    }

    private void openConnection(String ip, String database, String user, String password, Runnable onSuccess, Runnable onError) {
        String url = "jdbc:mysql://" + ip + "/" + database + "?autoReconnect=true";
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            this.createTodoTable();
            onSuccess.run();
            System.out.println("Conectado.");
        } catch (SQLException e) {
            e.printStackTrace();
            onError.run();
        }
    }

    public <V> void insert(Todo todo) {
        Runnable runnable = () -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `" + tableName + "`(`key`, `todo`) VALUES(?, ?) ON DUPLICATE KEY UPDATE `todo` = VALUES(`todo`)")) {
                preparedStatement.setString(1, todo.getUuid().toString());
                preparedStatement.setString(2, todo.getMessage());
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        async(runnable);
    }

    public void delete(String key) {
        Runnable runnable = () -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `" + tableName + "` WHERE `key` = ?")) {
                preparedStatement.setString(1, key);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        async(runnable);
    }

    /*
    public List<Todo> getAll(String tableName, Class<? extends Storable> vClass) {
        List<Storable> values = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `" + tableName + "`")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                values.add(gson.fromJson(resultSet.getString("json"), vClass));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }
     */

    public boolean exists(String key, String tableName) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `" + tableName + "` WHERE `key` = ?")) {
            preparedStatement.setString(1, key);
            return preparedStatement.executeQuery().next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTodoTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + tableName + "`(`key` VARCHAR(64) NOT NULL, `todo` TEXT NOT NULL, PRIMARY KEY (`key`))")) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() throws DatabaseException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Future<?> async(Runnable runnable) {
        return executor.submit(runnable);
    }

}