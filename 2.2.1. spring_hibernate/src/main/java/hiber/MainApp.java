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

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car BMW = new Car("X6", 2024);
      user1.setCar(BMW);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car RollsRoyce = new Car(" Phantom", 2020);
      user2.setCar(RollsRoyce);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      Car Mercedes = new Car("M600", 2023);
      user3.setCar(Mercedes);

      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      Car Ferrari = new Car("f40", 2024);
      user4.setCar(Ferrari );

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      User userByCar = userService.getUserByModelAndSeries("f40", 2024);
      if (userByCar != null) {
         Car car = userByCar.getCar();
         System.out.printf("User with model %s and series %d: %s%n",
                 car.getModel(),
                 car.getSeries(),
                 userByCar.getFirstName());
      } else {
         System.out.println("No user found with the specified car model and series.");
      }


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }


      context.close();
   }
}
