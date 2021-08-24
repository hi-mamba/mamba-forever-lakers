package space.lakers.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description
 * <p>
 * @author mamba
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PageResponseData<T> extends ResponseData<T> implements Serializable {

    private Paging paging;

    public PageResponseData(T data, Paging paging) {
        super(data);
        this.paging = paging;
    }
}