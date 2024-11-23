package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Model X", 2020);
      User user1 = new User("John", "Doe", "john.doe@example.com", car1);

      Car car2 = new Car("Model Y", 2021);
      User user2 = new User("Jane", "Smith", "jane.smith@example.com", car2);

      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car = " + user.getCar().getModel() + ", Series = " + user.getCar().getSeries());
            System.out.println();
         } else {
            System.out.println("Car = null");
            System.out.println();
         }
      }

      context.close();
   }
}
