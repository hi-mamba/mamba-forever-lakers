package kobe.mamba.web.base;

import kobe.mamba.web.SpringBootDroolsApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author pankui
 * @date 2019/9/12
 * <pre>
 *   使用回滚是为了减少每执行 test 重新插入数据一次
 * </pre>
 */
@Slf4j
//@Rollback
//@Transactional()
@ActiveProfiles({"unittest"})
//使用powermock自己的Runner
@RunWith(SpringRunner.class)

@SpringBootTest(classes = SpringBootDroolsApplication.class)
public class AbstractBaseDbTest {

    private static boolean isInited = false;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        migrate();
    }

    public void migrate() {
     //   //初始化flyway类
     //   Flyway flyway = Flyway.configure()
     //           //设置加载数据库的相关配置信息
     //           .dataSource(dataSource)
     //           //设置flyway扫描sql升级脚本、java升级脚本的目录路径或包路径，默认"db/migration"，可不写
     //           .locations("db/migration")
     //           //设置sql脚本文件的编码，默认"UTF-8"，可不写
     //           .encoding(Charset.forName(StandardCharsets.UTF_8.name()))
     //           // 针对数据库在同一段时间有修改，但不会造成冲突的情况，通常实际项目中主要存在这样的情况
     //           // 那可以设置flyway.out-of-order=true，这样允许当v1和v3已经被应用后，v2出现时同样也可以被应用
     //           .outOfOrder(false)
     //           .baselineOnMigrate(true)
     //           .load();
     //   //设置存放flyway metadata数据的表名，默认"schema_version"，可不写
     //   //flyway.setTable("schema_version");
     //   try {
     //     //  log.info("###开始执行");
     //       // 删除当前 schema 下所有表
     //       flyway.clean();
     //       //flyway.setValidateOnMigrate(true);
     //       //flyway.setCleanDisabled(true);
     //       // 开始执行
     //       flyway.migrate();
     //   } catch (Exception e) {
     //       flyway.repair();
     //       e.printStackTrace();
     //   }
    }
}
