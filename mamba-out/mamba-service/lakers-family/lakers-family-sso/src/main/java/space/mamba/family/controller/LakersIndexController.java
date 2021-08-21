package space.mamba.family.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/api/lakers")
public class LakersIndexController {

    @GetMapping("/hi")
    public String hi() {
        log.info("### request hi");
        return "湖人总冠军";
    }


}
