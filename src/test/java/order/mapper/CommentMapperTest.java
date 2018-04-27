package order.mapper;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.gl365.order.dto.comment.command.GetMerchantCommentsCommand;
import com.gl365.order.dto.comment.command.SaveComment4MemberCommand;
import com.gl365.order.service.CommentService;

import order.OrderTest;

public class CommentMapperTest extends OrderTest {

	@Autowired
	private CommentService service;

	@Override
	protected void execute() {
		SaveComment4MemberCommand command = new SaveComment4MemberCommand("511", "12312", "13123", new BigDecimal(100));
		service.saveDefaultComment(command);

		GetMerchantCommentsCommand command2 = new GetMerchantCommentsCommand();
		command2.setCurPage(1);
		command2.setPageSize(10);
		command2.setMerchantNo("12312");
		command2.setUserId("511");
		command2.setComment(true);
		service.getCommentList(command2);

	}

}
