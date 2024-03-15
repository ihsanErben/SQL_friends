
import java.sql.*;
import java.util.Scanner;

public class Main {

    private String user_name = "root";
    private String password = "";

    private String host = "localhost";
    private int port = 3306;
    private String db_name = "hocam";

    private Connection connection = null;
    private Statement statement = null;

    Scanner scn = new Scanner(System.in);

    public Main() {

        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_name;
        try {
            connection = DriverManager.getConnection(url, user_name, password);
            statement = connection.createStatement();
            System.out.println("connection : +");
        } catch (Exception e) {
            System.out.println("connection : -");
            System.out.println("-- problem 1 --");
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Main x = new Main();
        while (true) {
            System.out.println("-----------------------------------------------");
            System.out.println("1 - add");
            System.out.println("2 - delete");
            System.out.println("3 - update");
            System.out.println("4 - print");
            System.out.print("option: ");
            int option = scn.nextInt();

            switch (option) {
                case 0:
                    return;
                case 1:
                    x.add_friend();
                    break;
                case 2:
                    x.delete_friends();
                    break;
                case 3:
                    x.update_friends();
                    break;
                case 4:
                    x.print_friends();
                    break;
            }

        }
    }

    public void print_friends() {
        try {
            // select * from friends
            String sorgu = "select * from friends";

            ResultSet rs = statement.executeQuery(sorgu);

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");

                System.out.println("id: " + id + " --- " + "name: " + name + " --- " + "surname: " + surname + " --- " + "email: " + email);
            }

        } catch (Exception e) {
            System.out.println("-- problem 2 --");
        }
    }

    public void add_friend() {
        try {
            System.out.print("name: ");
            String name = scn.next();
            System.out.print("surname: ");
            String surname = scn.next();
            System.out.print("email: ");
            String email = scn.next();

            // insert into friends  ('name', 'surname', 'email') values (...);
            String sorgu = "insert into friends (name, surname, email) values ('" + name + "','" + surname + "','" + email + "')";

            statement.executeUpdate(sorgu);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-- problem 2 --");
        }
    }

    public void update_friends() {
        try {
            System.out.print("Type the ID whose email you want to update: ");
            int setId = scn.nextInt();
            // update friends set email = exampl@gmail.com
            String sorgu = "update friends set email = 'example@gmail.com' where id = " + setId;

            int x = statement.executeUpdate(sorgu);
            System.out.println(x + " information was updated.");

        } catch (Exception e) {
            System.out.println("-- problem 2 --");
        }
    }

    public void delete_friends() {
        try {
            System.out.print("Which id do you want to delete: ");
            int deleteId = scn.nextInt();
            String sorgu = "delete from friends where id = " + deleteId;

            int x = statement.executeUpdate(sorgu);
            System.out.println(x + " friend was deleted.");

        } catch (Exception e) {
            System.out.println("-- problem 2 --");
        }
    }

}
