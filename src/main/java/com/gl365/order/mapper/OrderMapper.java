package com.gl365.order.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gl365.order.dto.order.OrderListSum;
import com.gl365.order.model.Order;
import com.gl365.order.model.OrderSequence;

public interface OrderMapper {

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);
    
    Order selectBySn(@Param("orderSn") String orderSn);

    int updateByPrimaryKeySelective(Order record);

    /**
     * 
     * @Title: updateByOrderSnSelective
     * @Description: 按单号修改订单信息
     * @param record
     * @return
     * @return: int
     */
    int  updateByOrderSnSelective(Order record);
    

    
    /**
     * 
     * @Title: updateStatusByOrderSn
     * @Description: 按单号修改订单状态
     * @param orderSn
     * @param orderStatus
     * @return
     * @return: int
     */
    int  updateStatusByOrderSn(Order record);
    
    
	/**
	 * 往order_sequence表插入一条固定内容将自增的主键带出来
	 */
	void insertOrderSequence(OrderSequence sequence);
	
	/**
	 * 
	 * @Title: selectByOrderSn
	 * @Description: 按单号查询订单信息
	 * @param map
	 * @return
	 * @return: Order
	 */
	Order selectByOrderSn(Map map);
	
	
	/**
	 * 查询所有作废状态的订单
	 * @return
	 */
	List<Order> selectTimeOut();
	
    void batchUpdateStatus(@Param("orderStatus") List<Order> order);
    
    
    Order selectByOrderMemberId(@Param("memberId")String memberId);
    
    Order selectByOrderMemberIdList(@Param("memberIdList")List<String> memberId);
    /**
     * 查询群买单主单
     * @param memberId
     */
	List<Order> getByOrderMainMemberId(@Param("memberId")String memberId);
	List<Order> getByOrderMainMemberIdList(@Param("memberIdList")List<String> memberId);
    /**
     * 查询群买单子单
     * @param memberId
     */
	List<Order> getByOrderSonMemberId(@Param("memberId")String memberId);
	List<Order> getByOrderSonMemberIdList(@Param("memberIdList")List<String> memberId);
	/**
	 * 查询所有成功的单
	 * @param groupId
	 * @return
	 */
	List<Order> getByOrderGroupId(@Param("groupId")String groupId);
	
	/**
	 * 根据状态查询所有成功的单
	 * @param groupId
	 * @return
	 */
	List<Order> getByOrderGroupIdCancelOrder(Map<String, Object> map);
	/**
	 * 按群id查询订单
	 * @param groupId
	 * @return
	 */
	List<Order> getByOrderListGroupId(@Param("groupId")String groupId);
	
	
	Order getByMainOrderGroupId(@Param("groupId")String groupId);
	
	/**
	 * 强制修改订单状态为退款完成状态
	 * @param orderSn
	 * @return
	 */
	int updateRefundByOrderSn(@Param("orderSn")String orderSn);
	/**
	 *  修改查询次数
	 * @param record
	 * @return
	 */
	int updateQuerySumByOrderSn(Order record);
	
	/**
	 * 查询状态为付款中的订单
	 * @param record
	 * @return
	 */
	List<Order> getOrderByQuerySum(Order record);
	
	
	int updateStatusByOGroup(@Param("groupId")String groupId);
	
	/**
	 * 乐豆扣除失败查询
	 * @param beanQuerySum
	 * @return
	 */
	List<Order> getOrderByBeanQuerySum (@Param("beanQuerySum")Integer beanQuerySum);
	
	/**
	 * 修改订单为撤销状态
	 * @param record
	 * @return
	 */
	int updateRevokeByOrderSn(Order record);
	
	
	int updateOrderStatus(@Param("orderSn")String orderSn, @Param("orderStatus") int orderStatus, @Param("perOrderStatus") int perOrderStatus);
	
	
	
	//-------------------------------------fft----------------------
    /**
     *  付费通订单修改
     * @Title: updateStatusByOrderSn
     * @Description: 按单号修改订单状态
     * @param orderSn
     * @param orderStatus
     * @return
     * @return: int
     */
    int  updateStatusByFftOrderSn(Order record);
    
    /**
     * 付费通订单修改
     * @Title: updateByOrderSnSelective
     * @Description: 按单号修改订单信息
     * @param record
     * @return
     * @return: int
     */
    int  updateByFftOrderSnSelective(Order record);
    /**
     * 修改群买单撤销
     * @param record
     * @return
     */
    int  updateRevokeByGroupId(Order record);
    /**
     * 查找主单撤销 子单已经支付的状态
     * @return
     */
	List<Order> selectGroupRevok();

	List<Order> selectOrderList(@Param("condition")Order condition,
			@Param("fromDate")LocalDate fromDate,
			@Param("toDate")LocalDate toDate,
			@Param("totalAmountStart")BigDecimal totalAmountStart,
			@Param("totalAmountEnd")BigDecimal totalAmountEnd);

	OrderListSum selectOrderListSum(@Param("condition")Order condition,
			@Param("fromDate")LocalDate fromDate,
			@Param("toDate")LocalDate toDate,
			@Param("totalAmountStart")BigDecimal totalAmountStart,
			@Param("totalAmountEnd")BigDecimal totalAmountEnd);
	
	 /**
     * 查询支付成功的订单号
     * @param paymentNos
     * @return
     */
    List<String> queryPayCompleteOrder(@Param("paymentNos") List<String> paymentNos);
}