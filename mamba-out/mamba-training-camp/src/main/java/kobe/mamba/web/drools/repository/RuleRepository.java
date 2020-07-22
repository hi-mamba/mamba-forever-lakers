package kobe.mamba.web.drools.repository;

/**
 * @author mamba
 * @ 2020/7/22
 */

import kobe.mamba.web.drools.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    Rule findByRuleKey(String ruleKey);
}
