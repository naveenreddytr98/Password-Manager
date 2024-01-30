package edu.udayton.passwordmanager2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnect {
    private final String user = "postgres";
    private final String pass = "1234";
    private String url = "jdbc:postgresql://10.0.2.2:5432/postgres";

    public ArrayList<userlogins> userDetailsList;

    public void insertUserData(String userName, String password) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Connection conn = null;
                System.out.println("trying to connect");
                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://10.0.2.2:5432/postgres", user, pass);
                    System.out.println("Connected to the PostgreSQL server successfully.");
                    String SQL = "insert into \"PasswordManager\".userlogins(username, passwords) values('"+userName+"','"+password+"')";
                    try {
                        Statement st = conn.createStatement();
                        st.executeUpdate(SQL);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<userlogins> displayData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                System.out.println("trying to connect");
                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://10.0.2.2:5432/postgres", user, pass);
                    System.out.println("Connected to the PostgreSQL server successfully.");
                    String SQL = "select * from \"PasswordManager\".userlogins";
                    try (Connection conn1 = conn;
                    Statement stmt = conn1.createStatement();
                    ResultSet rs = stmt.executeQuery(SQL)) {
                        userDetailsList = new ArrayList<>();
                        while (rs.next()) {
                            userlogins user = new userlogins();
                            user.setUserId(rs.getString("userid"));
                            user.setUserName(rs.getString("username"));
                            user.setPasswords(rs.getString("passwords"));
                            user.setUrl(rs.getString("url"));
                            userDetailsList.add(user);
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDetailsList;
    }


//    public void selectStatement() {
//        String SQL = "select * from \"PasswordManager\".userlogins";
//
//        try (Connection conn1 = conn;
//             Statement stmt = conn1.createStatement();
//             ResultSet rs = stmt.executeQuery(SQL)) {
//            // display actor information
//
//
//            while (rs.next()) {
//                System.out.println(rs.getString("userid") + "\t"
//                        + rs.getString("username") + "\t");
//                //+ rs.getString("last_name"));
//
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

//    public void connect1() {
//        Thread thread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println("trying to connect");
//                try {
//                    Class.forName("org.postgresql.Driver");
//                    connection = DriverManager.getConnection("jdbc:postgresql://10.0.2.2:5432/postgres", user, pass);
//                } catch (ClassNotFoundException e) {
//                    throw new RuntimeException(e);
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//
//        thread.start();
//        try {
//            thread.join();
//        } catch (Exception e) {
//            e.printStackTrace();
//            this.status = false;
//        }
//    }

    void updateData(String userName, String password, int userId) throws SQLException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                System.out.println("------------trying to connect");
                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://10.0.2.2:5432/postgres", user, pass);
                    System.out.println("update statement ");
                    String SQL = "update \"PasswordManager\".userlogins set username = ?, passwords=? where userid = 3 ";
                    try (Connection conn1 = conn;
                         PreparedStatement pstmt = conn1.prepareStatement(SQL)) {

                        pstmt.setString(1, userName);
                        pstmt.setString(2, password);
                        //pstmt.setInt(3, userId);

                        pstmt.executeUpdate();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
