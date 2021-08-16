package space.lakers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pankui
 * @date 2021/8/14
 * <pre>
 *
 * </pre>
 */

@RestController
@RequestMapping("/api/lakers")
public class LakersIndexController {

    @GetMapping("/hi")
    public String hi() {
        return "湖人总冠军";
    }
}
