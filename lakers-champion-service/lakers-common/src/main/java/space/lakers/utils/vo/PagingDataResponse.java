package space.lakers.utils.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 * http 分页出参
 * @author mini kobe
 */
@Getter
@Setter
@ToString
public class PagingDataResponse<T> extends DataResponse<T> implements Serializable {
    /**
     * 总数据量
     */
    //@ApiModelProperty(value = "总数")
    private Long total;

    @Tolerate
    public PagingDataResponse() {
    }

    public PagingDataResponse(T data, Long total) {
        super(data);
        this.total = total;
    }

    public static <T> PagingDataResponse<T> success(T data, Long total) {
        return new PagingDataResponse<>(data, total);
    }

    public PagingDataResponse(Integer code, String message) {
        super(false, code, message, null);

    }

    public static <T> PagingDataResponse<T> fail(String message) {
        return new PagingDataResponse<>(999, message);
    }

    public static <T> PagingDataResponse<T> fail(Integer code, String message) {
        return new PagingDataResponse<>(code, message);
    }
}
