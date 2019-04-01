/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.server_side;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class Database {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost:3306/RSSREADER?useSSL=false&serverTimezone=UTC";
    String USER = "root";
    String PASS = "f=gmm/r2";

    public Database() {
    }

    public String operate(String info) {
        boolean same = false;
        Connection conn = null;
        Statement stmt = null;

        try {
            try {
                Class.forName(this.JDBC_DRIVER);
                String sql1 = "INSERT INTO userRecord(username,password,telephone,winrecord) values(?,?,?,?)";
                conn = DriverManager.getConnection(this.DB_URL, this.USER, this.PASS);
                PreparedStatement ps = conn.prepareStatement(sql1);
                ResultSet rs = null;
                stmt = conn.createStatement();
                String sql2 = "SELECT username,password,telephone,winrecord FROM userRecord";
                rs = stmt.executeQuery(sql2);
                StringTokenizer stringTokenizer = new StringTokenizer(info, "~", false);
                String op = stringTokenizer.nextToken();
                if (op.equals("0")) {
                    ps.setString(1, stringTokenizer.nextToken());
                    ps.setString(2, stringTokenizer.nextToken());
                    ps.setString(3, stringTokenizer.nextToken());
                    ps.setString(4, "0");
                    ps.executeUpdate();
                } else if (op.equals("1")) {
                    String username = stringTokenizer.nextToken();
                    String password = stringTokenizer.nextToken();
                    String title = "";
                    String name = "";
                    while (rs.next()) {
                        title = rs.getString("username");
                        name = rs.getString("password");

                        if (username.equals(title)) {
                            String var18;
                            if (password.equals(name)) {
                                var18 = "0";
                                return var18;
                            }

                            var18 = "-2";
                            return var18;
                        }
                    }
                } else if (op.equals("2")) {
                    String name = stringTokenizer.nextToken();
                    String record = "";
                    while (rs.next()) {
                        String perhapsName = rs.getString("username");
                        if (name.equals(perhapsName)) {
                            int win = Integer.parseInt(rs.getString("winRecord"));
                            win++;
                            record = win + "";
                        }
                    }


                    if (record != null && record.length() != 0) {
                        String update = "UPDATE userRecord SET winRecord='" + record + "'  WHERE username='" + name + "'";
                        ps.executeUpdate(update);
                    }

                } else {
                    String name = stringTokenizer.nextToken();
                    String username = "";
                    String win = "";
                    while (rs.next()) {
                        username = rs.getString("username");
                        win = rs.getString("winrecord");
                    }

                    if (username.equals(name)) {
                        return win;
                    }
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException var39) {
                var39.printStackTrace();
            } catch (Exception var40) {
                var40.printStackTrace();
            }

            return "-1";
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException var38) {
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var37) {
                var37.printStackTrace();
            }

        }
    }
}
