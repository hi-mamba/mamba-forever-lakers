package space.lakers.family.entity;
//mport com.baomidou.mybatisplus.annotation.IdType;
//mport com.baomidou.mybatisplus.annotation.TableField;
//mport com.baomidou.mybatisplus.annotation.TableId;
//mport com.baomidou.mybatisplus.annotation.TableLogic;
//mport com.youlai.common.base.BaseEntity;
//mport io.swagger.annotations.ApiModelProperty;
/**
 * @author mini kobe
 * @date 2021/8/21
 * <pre>
 *
 * </pre>
 */

import lombok.Data;

import java.util.List;


@Data
public class SysUser {

  //  @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String nickname;

    private String mobile;

    private Integer gender;

    private String avatar;

    private String password;

    private String email;

    private Integer status;

    private Long deptId;

   // @ApiModelProperty("逻辑删除标识 0-未删除 1-已删除")
    // @TableLogic(value = "0", delval = "1")
    private Integer deleted;

  //  @TableField(exist = false)
    private String deptName;

 //   @TableField(exist = false)
    private List<Long> roleIds;

    // @TableField(exist = false)
    private String roleNames;

    //  @TableField(exist = false)
    private List<String> roles;

  // @TableField(fill = FieldFill.INSERT)
  // @JsonInclude(value = JsonInclude.Include.NON_NULL)
  // @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  // private Date gmtCreate;

  // @TableField(fill = FieldFill.INSERT_UPDATE)
  // @JsonInclude(value = JsonInclude.Include.NON_NULL)
  // @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  //  private Date gmtModified;
}
