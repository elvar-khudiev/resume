import com.company.ResumeDbAppFwJpaSpringApplication;
import com.company.dao.impl.UserRepositoryCustomImpl;

import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ResumeDbAppFwJpaSpringApplication.class, properties = {"job.autorun.enabled=false"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryCustomImplIT {

    @Autowired
    UserRepositoryCustomImpl userRepository;

    @Before
    public void before() {
        deleteAllUsers_and_AddTwoUsers();
    }

    @Test
    public void testGetAll() {
        List<User> list = userRepository.getAll(null, null, null);

        Assert.assertEquals("users size must be 2", 2, list.size());
        System.out.println("list: " + list);
    }

    @Test
    public void testFindByEmail() {
        User user = userRepository.getByEmail("TEST_EMAIL_2@MAIL.RU");

        Assert.assertNotNull("user must not be empty", user);
        System.out.println("user: " + user);
    }

    private void deleteAllUsers_and_AddTwoUsers() {
        for (User u : userRepository.getAll(null, null, null)) {
            userRepository.delete(u.getId());
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = simpleDateFormat.parse("2000-01-01");
            Date date2 = simpleDateFormat.parse("2000-02-02");

            User user1 = new User(null, "TEST_NAME_1", "TEST_SURNAME_1",
                    "TEST_EMAIL_1@MAIL.RU", "1", "+994111111111");
            User user2 = new User(null, "TEST_NAME_2", "TEST_SURNAME_2",
                    "TEST_EMAIL_2@MAIL.RU", "2", "+994222222222");

            userRepository.add(user1);
            userRepository.add(user2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
