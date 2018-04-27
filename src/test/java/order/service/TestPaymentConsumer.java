package order.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.util.StringUtils;
import com.gl365.order.dto.mq.payment.PaymentMQ;
import com.gl365.order.handler.FactoryPaymentHandler;
import com.gl365.order.service.OrderService;
import com.google.gson.Gson;

import order.OrderTest;

public class TestPaymentConsumer extends OrderTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	private FactoryPaymentHandler factoryPaymentHandler;

	String normalMsg = "{'tranType':'1100','msgCategory':'P','paymentBody':{'payMain':{'payId':'80170613141179HWLM','requestId':'mock1706131411381NA7','organCode':'10001','organMerchantNo':'110290054110001','merchantNo':'1000001900002','merchantName':'嘟嘟汽车租赁3346','terminal':'12345678','operator':'101','merchantAgentNo':'man88tou001','userAgentType':'1','userAgentNo':'2422','orderType':'1','userDevManager':'1000001900002','userDevStaff':'5','settleOrganNo':'10001','parentAgentNo':'man88tou001','inviteAgentNo':'119','userMobile':'13530488236','cardNo':'030x0020****440404','province':6,'city':77,'district':709,'transType':'1100','scene':'00','merchantOrderNo':'xiaofei605233E38701216789017','rewardUserId':'','userId':'70dfa0d6b3ce4fd6949a7a9d09052375','userName':'135****8236','cardIndex':'6091bc019a','totalAmount':30.00,'noBenefitAmount':0.00,'payFeeType':'D','maxPayFee':100.00,'payFee':3.58,'cashAmount':23.85,'beanAmount':6.15,'coinAmount':0.00,'payFeeRate':15.00,'commRate':20.65,'commAmount':4.92,'marcketFee':1.34,'giftRate':20.50,'giftAmount':6.15,'giftPoint':0.00,'merchantSettleAmount':18.93,'payStatus':'01','payDesc':'已支付','createBy':'payment system','modifyBy':'payment system'},'payStream':{'payId':'80170613141179HWLM','requestId':'mock1706131411381NA7','organCode':'10001','organMerchantNo':'110290054110001','terminal':'12345678','transType':'1100','totalAmount':30.00,'dealStatus':'88','createBy':'10000','modifyBy':'10000','uniqueSerial':'12345678-_-mock1706131411381NA7'}},'messageId':'18078f8c-85ef-492a-8ef5-360547123559'}";

	String returnMsg = "{'tranType':'3100','msgCategory':'N','paymentBody':{'payMain':{'payId':'90170612094922V924','requestId':'mock1706120948740RQR','prePayId':'98170612094955LWV5','organCode':'100001','organMerchantNo':'110290054110001','merchantNo':'1000001900002','merchantName':'嘟嘟汽车租赁3346','terminal':'12345678','merchantAgentNo':'man88tou001','userAgentType':'1','userAgentNo':'2422','orderType':'1','userDevManager':'1000001900002','userDevStaff':'5','settleOrganNo':'100001','parentAgentNo':'man88tou001','inviteAgentNo':'119','userMobile':'13530488236','cardNo':'030x0020****440404','province':6,'city':77,'district':709,'transType':'1000','scene':'03','rewardUserId':'','userId':'70dfa0d6b3ce4fd6949a7a9d09052375','userName':'135****8236','cardIndex':'6091bc019a','totalAmount':600.00,'noBenefitAmount':0.00,'payFeeType':'D','maxPayFee':20.00,'payFee':20.00,'cashAmount':481.10,'beanAmount':118.90,'coinAmount':0.00,'payFeeRate':20.65,'commRate':20.65,'commAmount':98.50,'marcketFee':78.50,'giftRate':20.50,'giftAmount':123.00,'giftPoint':0.00,'merchantSettleAmount':378.50,'payStatus':'03','payDesc':'已部分退货','createBy':'payment system','modifyBy':'payment system'},'payReturn':{'payId':'85170613154663F3D7','requestId':'mock170613154532MASH','origPayId':'90170612094922V924','organCode':'10001','organMerchantNo':'110290054110001','merchantNo':'1000001900002','merchantName':'嘟嘟汽车租赁3346','terminal':'12345678','merchantAgentNo':'man88tou001','userAgentType':'1','userAgentNo':'2422','userDevManager':'1000001900002','userDevStaff':'5','settleOrganNo':'100001','parentAgentNo':'man88tou001','inviteAgentNo':'119','userMobile':'13530488236','cardNo':'030x0020****440404','province':6,'city':77,'district':709,'transType':'3100','userId':'70dfa0d6b3ce4fd6949a7a9d09052375','userName':'135****8236','cardIndex':'6091bc019a','totalAmount':30.0,'noBenefitAmount':0.00,'cashAmount':24.05,'beanAmount':5.95,'coinAmount':0,'payFeeType':'D','payFeeRate':20.65,'maxPayFee':20.00,'payFee':1.00,'commRate':20.65,'giftRate':20.50,'commAmount':4.93,'marcketFee':3.93,'giftAmount':6.15,'giftPoint':0,'merchantSettlAmount':18.92,'payStatus':'03','payDesc':'已部分退货','createBy':'payment system','modifyBy':'payment system'},'payDetails':[{},{}],'payModifyStatus':'03'},'messageId':'82887d09-aba7-4e15-9853-0ea3008ad984'}";

	Scanner sc = new Scanner(System.in);

	@Override
	protected void execute() {
		execute(normalMsg);
	}

	// 测试消费逻辑
	public void execute(String message) {
		PaymentMQ payment = newInstance(message);
		// 参数校验
		if (null == payment || null == payment.getPaymentBody() || StringUtils.isEmpty(payment.getTranType())) {
			return;
		}
		// 执行
		try {
			orderService.mqUpdateOrderDetailed(payment);
			factoryPaymentHandler.distribute(payment.getTranType()).build(payment).execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	// 测试推送逻辑
	public void push(String message) {
		try {
			PaymentMQ payment = newInstance(message);
			// 参数校验
			if (null == payment || null == payment.getPaymentBody() || StringUtils.isEmpty(payment.getTranType())) {
				return;
			}
			// 执行
			String type = payment.getTranType();
			factoryPaymentHandler.distribute(type).build(payment).push();
		} catch (Exception e) {
		}
	}

	private PaymentMQ newInstance(String message) {
		return new Gson().fromJson(message, PaymentMQ.class);
	}

}
