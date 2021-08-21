package space.mamba.family.resp;

import java.io.Serializable;

/**
 * @author pankui
 * @date 2021/8/21
 * <pre>
 *
 * </pre>
 */

public class UserDTO implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
