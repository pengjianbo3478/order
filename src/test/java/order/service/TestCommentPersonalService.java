package order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.comment.command.SaveCommentPersonal4MemberCommand;
import com.gl365.order.service.CommentService;

import order.OrderTest;

public class TestCommentPersonalService extends OrderTest {

	@Autowired
	private CommentService commentService;

	private String userId = "511", merchantNo = "mer1312312test", paymentNo = "pay-1312test";

	@Override
	protected void execute() {
		System.out.println("Unit Test Comment Personal Model Begin");
		saveCommentPersonal();
		updateCommentPersonal();
		getCommentPersonal();
		System.out.println("Unit Test Comment Personal Model End");
	}

	private void getCommentPersonal() {
		List<String> command = new ArrayList<>();
		command.add(paymentNo);
		System.out.println(JsonUtils.toJsonString(commentService.getCommentPersonalStatus(command)));

	}

	private void updateCommentPersonal() {
		SaveCommentPersonal4MemberCommand command = new SaveCommentPersonal4MemberCommand(userId, merchantNo, paymentNo);
		command.setPaymentNo(paymentNo);
		command.setOperatorId("operatorId");
		System.out.println(JsonUtils.toJsonString(commentService.saveUpdateCommentPersonal(command)));
	}

	private void saveCommentPersonal() {
		SaveCommentPersonal4MemberCommand command = new SaveCommentPersonal4MemberCommand(userId, merchantNo, paymentNo);
		command.setPaymentNo(paymentNo);
		System.out.println(JsonUtils.toJsonString(commentService.saveUpdateCommentPersonal(command)));
	}

}
