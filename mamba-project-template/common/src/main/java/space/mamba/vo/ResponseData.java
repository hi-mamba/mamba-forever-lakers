package space.mamba.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description
 * <p>
 * @author mamba
 */
@Getter
@Setter
@ToString
public class ResponseData<T> implements Serializable {

    private Boolean success;

    private String code;

    private String message;

    private T data;

    public ResponseData() {
        super();
    }

    public ResponseData(T data) {
        this(true, "0", "request successfully", data);
    }

    public ResponseData(Boolean success, String code, String message) {
        this(success, code, message, null);
    }

    public ResponseData(Boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}