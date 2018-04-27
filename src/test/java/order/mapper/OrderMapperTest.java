package order.mapper;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gl365.Application;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.util.JsonUtil;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderMapperTest {

	@Autowired
	private OrderMapper orderMapper;
	
	@Test
	public void test(){
		String req = "11761090521100053011004406,01711100711100053017002535,51723090408555544412001042,01790090523100053015000816";
		List<String> paymentNos = Arrays.asList(req.split(","));
		paymentNos = orderMapper.queryPayCompleteOrder(paymentNos);
		System.out.println(JsonUtil.toJsonString(paymentNos));
	}
}
