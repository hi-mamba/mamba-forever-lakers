package space.lakers.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author pankui
 * @date 2021/7/7
 * <pre>
 *
 * </pre>
 */
@Getter
@Setter
public class Paging implements Serializable {

    /**
     * 单页数据量
     */
    private Integer limit;

    /**
     * 总数据量
     */
    private Long total;

}
