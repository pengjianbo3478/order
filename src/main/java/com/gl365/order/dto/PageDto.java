package com.gl365.order.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * < 页码DTO >
 * 
 * @author hui.li 2017年4月20日 - 下午7:36:25
 * @Since 1.0
 */
/**
 * @author DELL
 *
 * @param <T>
 */
public class PageDto<T> implements Serializable {

	private static final long serialVersionUID = -7498073051970322499L;

	private Integer totalCount; // 总条数

	private Integer totalPage; // 总页数

	private Integer curPage; // 页码

	private Integer pageSize; // 页数

	private Map<String, Object> dataMap; // 自定义列表头部

	private List<T> list; // 数据集

	public PageDto() {

	}

	/**
	 * 
	 * @param totalCount
	 *            总数
	 * @param curPage
	 *            页码
	 * @param pageSize
	 *            页数
	 * @param list
	 *            数据
	 */
	public PageDto(Integer totalCount, Integer curPage, Integer pageSize, List<T> list) {
		this.totalCount = totalCount; // 总条数
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1; // 总页数
		this.curPage = curPage; // 页码
		this.pageSize = pageSize; // 页数
		this.list = list;
	}

	public PageDto(Integer totalCount, Integer curPage, Integer pageSize, Map<String, Object> dateMap, List<T> list) {
		this.totalCount = totalCount; // 总条数
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1; // 总页数
		this.curPage = curPage; // 页码
		this.dataMap = dateMap; // 自定义分页头部Map
		this.pageSize = pageSize; // 页数
		this.list = list;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
