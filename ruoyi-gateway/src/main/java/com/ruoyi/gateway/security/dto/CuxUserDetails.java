package com.ruoyi.gateway.security.dto;


//import com.ruoyi.system.api.domain.SysUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


/**
 * Created by enHui.Chen on 2019/9/5.
 */
@Getter
@Setter
public class CuxUserDetails {
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 用户名id
     */
    private Long userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 角色列表
     */
    private Set<String> roles;
    /**
     * 用户信息
     */
//    private SysUser sysUser;

    private Boolean clientOnly;
    private String clientId;
    private String grantType;
    private List<String> scope;
    private List<String> resourceIds;
    private Boolean refresh;
    private Boolean approved;
    private String redirectUri;


}
