package order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gl365.Application;
import com.gl365.order.remote.PaymentClient;
import com.gl365.order.remote.dto.PaymentBaseResp;
import com.gl365.order.remote.dto.PaymentCancelReq;

@Ignore
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentRefundTest {

	@Autowired
	private PaymentClient paymentClient;
	
	private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Test
	public void test(){
		PaymentCancelReq reqParam = new PaymentCancelReq();
		reqParam.setCashAmount(new BigDecimal(0.01));
		reqParam.setMerchantOrderNo("test111");
		reqParam.setOrganCode("10003");
		reqParam.setOrganOrderNo("test111");
		reqParam.setOrganPayTime(LocalDateTime.now());
		reqParam.setOrigMerchantOrderNo("test111");
		reqParam.setOrigOrganOrderNo("test111");
		try{
			PaymentBaseResp resp = paymentClient.cancel(reqParam);
			System.out.println(resp.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
