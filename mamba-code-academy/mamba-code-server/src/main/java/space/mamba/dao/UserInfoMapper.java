package space.mamba.dao;

import java.time.LocalDateTime;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import space.mamba.model.UserInfo;

/**
 * The interface User info mapper.
 *
 * @param <selectByAll> the type parameter
 * @author pankui
 */
@Mapper
public interface UserInfoMapper<selectByAll> {

    /**
     * Delete by primary key int.
     *
     * @param id the id
     * @return int int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    int insert(UserInfo record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(UserInfo record);

    /**
     * Select by primary key user info.
     *
     * @param id the id
     * @return the user info
     */
    UserInfo selectByPrimaryKey(Long id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return int int
     */
    int updateByPrimaryKeySelective(UserInfo record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(UserInfo record);

    /**
     * Update batch int.
     *
     * @param list the list
     * @return the int
     */
    int updateBatch(List<UserInfo> list);

    /**
     * Batch insert int.
     *
     * @param list the list
     * @return the int
     */
    int batchInsert(@Param("list") List<UserInfo> list);


    /**
     * Select by all list.
     *
     * @param userInfo the user info
     * @return the list
     */
    List<UserInfo> selectByAll(UserInfo userInfo);


    /**
     * Find all by id user info.
     *
     * @param id the id
     * @return the user info
     */
    UserInfo findAllById(@Param("id") Long id);


}