package kobe.mamba.feature.upvote.enums;

import lombok.Getter;

/**
 * @author mamba
 * @date 2020/8/13 23:29
 */
@Getter
public enum  LikedStatusEnum {
    /***/
    LIKE(1, "点赞"),
    UNLIKE(0, "取消点赞/未点赞"),
            ;

    private final Integer code;

    private final String msg;

    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
