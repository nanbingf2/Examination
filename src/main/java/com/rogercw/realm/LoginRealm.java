package com.rogercw.realm;

import com.rogercw.po.Role;
import com.rogercw.po.User;
import com.rogercw.service.RoleService;
import com.rogercw.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1 on 2018/6/3.
 */
@Component
public class LoginRealm extends AuthorizingRealm{
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;


    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username= (String) getAvailablePrincipal(principalCollection);
        User user=userService.findByUserName(username);
        Role role=roleService.findById(user.getRole());

        //利用登录的用户信息来获取用户的角色
        Set<String> roles=new HashSet<>();
        if (role != null) {
            roles.add(role.getRolename());
        }

        //创建AuthorizationInfo对象
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        //返回权限信息
        return authorizationInfo;
    }

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        //获取用户名和密码
        String username= (String) token.getPrincipal();
        String password=new String((char[]) token.getCredentials());
        User user=userService.findByUserName(username);
        if(user==null){
            throw new UnknownAccountException("账号不存在");
        }else if(!password.equals(user.getPassword())){
            throw new IncorrectCredentialsException("密码错误");
        }

        //创建AuthenticationInfo对象
        AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(username,password,getName());
        return authenticationInfo;
    }
}
