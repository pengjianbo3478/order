package com.gl365.order.mq.producer;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.gl365.aliyun.ons.OnsProducer;
import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.req.merchant.UpdateMerchantCommentCountReq;

/**
 * < 商家评论动作生产者 >
 * 
 * @since hui.li 2017年5月10日 下午6:32:56
 */
@Component
public class MerchantCommentGradeProducer {

	private static final Logger LOG = LoggerFactory.getLogger(MerchantCommentGradeProducer.class);

	@Lazy
	@Resource(name = "order-merchant-discuss-producer")
	private OnsProducer merchantProducer;

	public void send(UpdateMerchantCommentCountReq command) {
		String message = JsonUtils.toJsonString(command);
		LOG.info("<mq-send>更新商家评论统计信息》》》入参：{}", message);
		try {
			// 更新商家评论统计信息
			merchantProducer.send(message);
		} catch (Exception e) {
			LOG.error("<mq-send>更新商家评论统计信息》》》异常：{}", e);
		}
	}

}
