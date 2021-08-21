package space.mamba.utils.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 * http 单资源出参
 *
 * @author pankui
 */
@Getter
@Setter
@ToString
//@ApiModel(value = "公共返回包装类")
public class DataResponse<T> implements Serializable {

  //  @ApiModelProperty(value = "true 返回成功，false 失败")
    private Boolean success;

    //  @ApiModelProperty(value = "状态码")
    private Integer code;

    // @ApiModelProperty(value = "失败返回信息")
    private String message;

    // @ApiModelProperty(value = "返回数据")
    private T data;

    @Tolerate
    public DataResponse() {
        super();
    }

    public DataResponse(T data) {
        this(true, 0, "request successfully", data);
    }

    public static <T> DataResponse<T> fail(String message) {
        return new DataResponse<>(false, 10000, message, null);
    }

    public static <T> DataResponse<T> fail(Integer code, String message) {
        return new DataResponse<>(false, code, message, null);
    }

    public DataResponse(Boolean success, Integer code, String message) {
        this(success, code, message, null);
    }

    public DataResponse(Boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
