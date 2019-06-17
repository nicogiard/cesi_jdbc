package com.company.services;

import com.company.models.exception.SQLServiceException;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService {

    static DBService instance;

    private DBService() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/jdbc_cesi?user=root&password=coaxys&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            System.exit(1);
        }
    }

    public static DBService get() {
        if (instance == null) {
            instance = new DBService();
        }
        return instance;
    }

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }


    public <T> List<T> select(Class<T> clazz, String requete) throws SQLServiceException {
        try {
            Connection conn = getConnection();
            return executeStatement(conn, clazz, requete);
        } catch (SQLException e) {
            throw new SQLServiceException("Problème de connexion SQL", e);
        }
    }

    private static <T> List<T> executeStatement(Connection conn, Class<T> clazz, String requete) throws SQLException, SQLServiceException {
        try (Statement statement = conn.createStatement()) {
            return executeQuery(statement, clazz, requete);
        }
    }

    private static <T> List<T> executeQuery(Statement statement, Class<T> clazz, String requete) throws SQLException, SQLServiceException {
        try (ResultSet rs = statement.executeQuery(requete)) {
            List<T> result = new ArrayList<>();
            while (rs.next()) {
                T item = clazz.newInstance();
                ResultSetMetaData rsmd = rs.getMetaData();
                setData(item, clazz, rs, rsmd);
                result.add(item);
            }
            return result;
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new SQLServiceException("Problème de classe Java", e);
        }
    }

    private static <T> void setData(T item, Class<T> clazz, ResultSet rs, ResultSetMetaData rsmd) throws SQLException, IllegalAccessException, ClassNotFoundException {
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            String sqlFieldName = rsmd.getColumnLabel(i);

            try {
                Field field = clazz.getDeclaredField(sqlFieldName);

                switch (field.getType().getCanonicalName()) {
                    case "java.lang.Integer":
                        field.set(item, rs.getInt(sqlFieldName));
                        break;
                    case "java.lang.Long":
                        field.set(item, rs.getLong(sqlFieldName));
                        break;
                    case "java.lang.Double":
                        field.set(item, rs.getDouble(sqlFieldName));
                        break;
                    case "java.lang.Float":
                        field.set(item, rs.getFloat(sqlFieldName));
                        break;
                    case "java.lang.String":
                        field.set(item, rs.getString(sqlFieldName));
                        break;
                    case "java.util.Date":
                        field.set(item, rs.getDate(sqlFieldName));
                        break;
                    case "java.lang.Boolean":
                        field.set(item, rs.getBoolean(sqlFieldName));
                        break;
                    default:
                        if (field.getType().isEnum()) {
                            Class fieldEnum = Class.forName(field.getType().getCanonicalName());
                            field.set(item, Enum.valueOf(fieldEnum, rs.getString(sqlFieldName)));
                        }
                        break;
                }
            } catch (NoSuchFieldException nsfe) {
                System.err.println("select : [" + sqlFieldName + "] didn't exists");
            }
        }
    }
}
