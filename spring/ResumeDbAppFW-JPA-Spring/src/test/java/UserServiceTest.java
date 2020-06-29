import com.company.dao.impl.UserRepository;
import com.company.service.impl.UserServiceImpl;
import org.junit.*;
import org.mockito.*;

public class UserServiceTest {

//    @Mock
    @Spy
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeClass
    public static void setUp() {
        System.out.println("set up called");
    }

    //  ---@Mock---
//    @Before
//    public void before() {
//
//        System.out.println("before called");
//
//        MockitoAnnotations.initMocks(this);
//
//        List<User> list = new ArrayList<>();
//
//        User u = new User();
//        u.setName("test");
//        u.setSurname("test");
//        u.setNationalityId(new Country(1));
//
//        list.add(u);
//        Mockito.when(userRepository.getAll(null, null, null)).thenReturn(list);
//
//        Mockito.when(userRepository.getAll("test", "test", 1)).thenReturn(list);
//
//        Mockito.when(userRepository.getByEmail(null)).thenReturn(null);
//    }
//
//    @Test
//    public void shouldReturnAllUsers_whenNoParameterPassed() {
//        List<User> list = userService.getAll(null, null, null);
//
//        Assert.assertEquals("user size must be 1", 1, list.size());
//
//        Mockito.verify(userRepository, Mockito.atMostOnce())
//                .getAll(null, null, null);
//    }
//
//    @Test
//    public void testGivenAllParamsGetAllByFilter() {
//        List<User> list = userService.getAll("test", "test", 1);
//
//        Assert.assertTrue("user size must be greater than 0", list.size() > 0);
//
//        User user = list.get(0);
//
//        Assert.assertEquals("name wrong", "test", user.getName());
//        Assert.assertEquals("surname wrong", "test", user.getSurname());
//        Assert.assertEquals("nat wrong", 1L, (long) user.getNationalityId().getId());
//
//        Mockito.verify(userRepository, Mockito.atMostOnce())
//                .getAll("test", "test", 1);
//    }
//
//    @Test
//    public void shouldReturnNull_whenNoParameterPassed() {
//        User user = userService.getByEmail(null);
//
//        Assert.assertNull("user must not be null", user);
//    }

    //  ---@Spy---
    @Before
    public void before2() {

        System.err.println("before called");

        MockitoAnnotations.initMocks(this);

        Mockito.doReturn("alma").when(userRepository).test();

        Mockito.doReturn(1).when(userRepository).getI();
    }

    @Test
    public void testSpy() {
        System.out.println(userRepository.test());
        System.out.println(userRepository.test());
    }

    @Test
    public void testSpy2() {
        System.out.println(userRepository.test());
        System.out.println(userRepository.test());
    }

    @Test
    @Ignore
    public void testSpy3() {
        System.out.println(userRepository.test());
        System.out.println(userRepository.test());
    }
}
