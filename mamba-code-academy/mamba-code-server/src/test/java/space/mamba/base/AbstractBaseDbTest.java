package space.mamba.base;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pankui
 * @date 2019/9/12
 * <pre>
 *   使用回滚是为了减少每执行 test 重新插入数据一次
 * </pre>
 */
@Rollback
@Transactional()
public class AbstractBaseDbTest extends MockTest {
}
