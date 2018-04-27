package order;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gl365.Application;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class OrderTest {

	private Scanner sc = new Scanner(System.in);

	@Test
	public void test() {
		while (true) {
			execute();
			Assert.assertTrue(sc.nextBoolean());
		}
	}

	protected abstract void execute();

}
