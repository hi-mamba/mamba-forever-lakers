package space.lakers.utils.vo;


import java.io.Serializable;

/**
 * 分页信息
 *
 * @author mamba
 */
public class Paging implements Serializable {

    private Integer pageSize;

    private Integer pageNum;

    /**
     * 总数据量
     */
    private Integer total;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
