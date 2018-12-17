package com.ncusc.dms.service.user;

import com.ncusc.dms.mapper.AdminMapper;
import com.ncusc.dms.mapper.StudentMapper;
import com.ncusc.dms.mapper.TeacherMapper;
import com.ncusc.dms.mapper.UserAccountMapper;
import com.ncusc.dms.pojo.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 登陆集中授权处理
 * @author WANGHAO && WangYiqing
 * @version 1.0.0
 */
@Service
public class UserAccountService implements UserDetailsService {
    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    AdminMapper adminmapper;
    @Autowired
    StudentMapper studentmapper;
    @Autowired
    TeacherMapper teachermapper;
    /**
     * 返回用户验证信息供Spring Security使用
     * @param str 注意：参数为用户id
     * @return UserDetails对象
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String str) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountMapper.findUserAccountByUserId(str);
        if (userAccount == null) {
            throw new UsernameNotFoundException("用户名："+str+"不存在");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+userAccount.getAuthority()));//默认是ROLE_开头
        return new User(userAccount.getName(), userAccount.getPassword(), grantedAuthorities);

    }

    /**
     * 注册
     * @param id
     * @param name
     * @param password
     * @return
     */
    public boolean addUser(String id,String name,String password)
    {
        if(adminmapper.get(id)==null && studentmapper.getById(id)==null && teachermapper.getById(id)==null)
        {
            UserAccount user = new UserAccount();
            user.setId(id);
            user.setName(name);
            user.setAuthority("USER");
            user.setPassword(password);
            userAccountMapper.add(user);
            return true;
        }
        else
            return false;//用户已存在,注册失败
    }

    /**
     * 登录
     * @param id
     * @param password
     * @return
     */
    /*public UserAccount loginUser(String id,String password)
    {
        UserAccount user = userAccountMapper.findUserAccountByUserId(id);
        if(password.equals(user.getPassword()))
            return user;
        else
            return null; //id不存在或密码不匹配

    }*/

}