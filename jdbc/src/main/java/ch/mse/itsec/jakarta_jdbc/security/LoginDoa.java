package ch.mse.itsec.jakarta_jdbc.security;

import ch.mse.itsec.jakarta_jdbc.datasource.EntityManagerfactory;
import ch.mse.itsec.jakarta_jdbc.entities.UserDto;

import javax.persistence.EntityManager;
import java.util.List;

public class LoginDoa {

    public static boolean validate(String user, String password) {
        EntityManager jakartaUser = EntityManagerfactory.createEntityManager("jakartaUser");

        List<UserDto> matchingUser = jakartaUser.createQuery("Select u.username from Users u where u.username = :uname and u.password = :upassword", UserDto.class)
                .setParameter("uname", user)
                .setParameter("upassword", password)
                .getResultList();

        return !matchingUser.isEmpty();
    }
}

