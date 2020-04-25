package com.company;

import com.company.dao.impl.UserRepository;
import com.company.dao.impl.UserRepositoryCustom;
import com.company.dao.impl.UserRepositoryCustomImpl;
import com.company.entity.Country;
import com.company.entity.User;
import com.company.service.impl.UserServiceImpl;
import org.junit.*;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @Mock
//    @Spy
    UserRepositoryCustomImpl userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeClass
    public static void setUp() {
        System.out.println("set up called");
    }

    @Before
    public void before() {
        System.out.println("before called");

        MockitoAnnotations.initMocks(this);

        List<User> list = new ArrayList<>();

        User u = new User();
        u.setName("Elvar7");
        u.setSurname("Khudiev7");
        u.setEmail("elvarkhudiev7@mail.ru");
        u.setNationality(new Country(1));

        list.add(u);
        Mockito.when(userRepository.getAll(null, null, null)).thenReturn(list);

        Mockito.when(userRepository.getAll("Elvar7", "Khudiev7", 1)).thenReturn(list);

        Mockito.when(userRepository.getByEmail(null)).thenReturn(null);

//        Mockito.doReturn("alma").when(userRepository).test();
//
//        Mockito.doReturn(1).when(userRepository).getI();
    }

//    @Test
//    @Ignore
//    public void test() {
//        System.out.println(userRepository.test());
//        System.out.println(userRepository.test());
//        System.out.println(userRepository.test());
//        System.out.println(userRepository.test());
//    }

    @Test
    public void shouldReturnAllUsers_whenNoParameterPassed() {
        List<User> list = userService.getAll(null, null, null);

        Assert.assertEquals("user size must be 1", 1, list.size());

        Mockito.verify(userRepository, Mockito.atMostOnce())
                .getAll(null, null, null);
    }

    @Test
    public void testGivenAllParamsGetAllByFilter() {
        List<User> list = userService.getAll("Elvar7", "Khudiev7", 1);

        Assert.assertTrue("user size must be greater than 0", list.size() > 0);

        User user = list.get(0);

        Assert.assertEquals("name wrong", "Elvar7", user.getName());
        Assert.assertEquals("surname wrong", "Khudiev7", user.getSurname());
        Assert.assertEquals("nat wrong", 1L, (long) user.getNationality().getId());

        Mockito.verify(userRepository, Mockito.atMostOnce())
                .getAll("Elvar7", "Khudiev7", 1);
    }

    @Test
    public void shouldReturnNull_whenNoParameterPassed() {
        User user = userService.getByEmail(null);

        Assert.assertNull("user must not be null", user);
    }
}
