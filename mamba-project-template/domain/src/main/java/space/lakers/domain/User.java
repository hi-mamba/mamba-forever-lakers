package space.lakers.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author pankui
 * @date 2021/7/7
 * <pre>
 *
 * </pre>
 */
@ToString
@Getter
@Setter
public class User implements Serializable {

    private String name;

    private Long id;
}
