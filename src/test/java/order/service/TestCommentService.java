package order.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.comment.command.GetCommentTempleteCommand;
import com.gl365.order.dto.comment.command.GetMerchantCommentsCommand;
import com.gl365.order.dto.comment.command.SaveComment4MemberCommand;
import com.gl365.order.dto.comment.command.UpdateComment4MemberCommand;
import com.gl365.order.service.CommentService;

import order.OrderTest;

public class TestCommentService extends OrderTest {

	@Autowired
	private CommentService commentService;

	private String userId = "511", merchantNo = "mer-no123123", paymentNo = "paym-no31231";

	@Override
	protected void execute() {
		try {
			System.out.println("Unit Test Comment Model Begin");
			getCommentTemplete();
			saveComment();
			getUnComment();
			updateComment();
			getComment();
			System.out.println("Unit Test Comment Model End");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getCommentTemplete() {
		GetCommentTempleteCommand command = new GetCommentTempleteCommand();
		command.setIndustry("0");
		System.out.println(JsonUtils.toJsonString(commentService.getTemplete(command)));
	}

	private void getUnComment() {
		GetMerchantCommentsCommand command = new GetMerchantCommentsCommand();
		command.setMerchantNo(merchantNo);
		command.setComment(false);
		command.setCurPage(1);
		command.setPageSize(10);
		System.out.println(JsonUtils.toJsonString(commentService.getCommentList(command)));
	}

	private void getComment() {
		GetMerchantCommentsCommand command = new GetMerchantCommentsCommand();
		command.setMerchantNo(merchantNo);
		command.setComment(true);
		command.setCurPage(1);
		command.setPageSize(10);
		System.out.println(JsonUtils.toJsonString(commentService.getCommentList(command)));
	}

	private void saveComment() {
		SaveComment4MemberCommand command = new SaveComment4MemberCommand();
		command.setUserId(userId);
		command.setMerchantNo(merchantNo);
		command.setPaymentNo(paymentNo);
		System.out.println(JsonUtils.toJsonString(commentService.saveDefaultComment(command)));
	}

	private void updateComment() {
		UpdateComment4MemberCommand command = new UpdateComment4MemberCommand(userId, merchantNo, paymentNo);
		command.setGrade(5);
		command.setLabels(new Integer[] { 101 });
		System.out.println(JsonUtils.toJsonString(commentService.updateComment(command)));
	}

}
