package myJPA;

public class TestUserDAO {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User user = new User("lisa", 20);
        userDAO.add(user);
    }
}
