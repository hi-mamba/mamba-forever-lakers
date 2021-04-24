package kobe.mamba.web.drools.repository;

import kobe.mamba.feature.upvote.entity.UserLike;
import kobe.mamba.web.drools.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pankui
 * @date 2021/4/24
 * <pre>
 *   Integer 主键ID
 * </pre>
 */
public interface UserLikeRepository extends JpaRepository<UserLike, Integer> {
}
