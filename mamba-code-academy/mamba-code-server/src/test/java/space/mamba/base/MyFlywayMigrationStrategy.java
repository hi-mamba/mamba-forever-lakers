package space.mamba.base;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;

/**
 * @author pankui
 * @date 2019/9/12
 * <pre>
 *
 * </pre>
 */
@Component
public class MyFlywayMigrationStrategy implements FlywayMigrationStrategy {
    @Override
    public void migrate(Flyway flyway) {
        // Do nothing, just make sure the Flyway won't startup automatically.
       // flyway.setBaselineOnMigrate(true);
       // flyway.migrate();
    }
}
