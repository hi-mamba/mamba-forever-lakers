package space.mamba.secondskill.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author mini kobe
 * @date 6/7 10:36
 */
@Getter
@Setter
@ToString
public class Stock implements Serializable {

    private Integer id;

    private String name;

    private Integer count;

    private Integer sale;

    private Integer version;
}
