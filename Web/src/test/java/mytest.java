import com.guotion.material.service.service.PremisesService;
import com.guotion.material.service.service.UserSerivice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wx on 15/11/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class mytest {


    @Autowired
    private PremisesService premisesService;

    @Autowired
    private UserSerivice userSerivice;

    @Test
    public  void test()
    {
        userSerivice.search(0,10,1,1+"");

    }
}
