package com.ncusc.dms.mapper;

import com.ncusc.dms.pojo.UserAccount;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 * 登陆集中授权映射
 * 对应用户表：t_user_account
 * @author WANGHAO
 * @version 1.0.0
 */
@Mapper
public interface UserAccountMapper {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from t_user_account where id=#{id}")
    public UserAccount findUserAccountByUserId(@Param("id")String id);

    /**
     * 注册用户
     * @param user
     */
    @Insert("insert into t_user_account(id,name,password,authority) " +
            "values(#{id},#{name},#{password},#{authority})")
    public int add(UserAccount user);


}
