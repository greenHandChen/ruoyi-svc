package com.ruoyi.gateway.support.filter;


import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.gateway.security.dto.CuxUserDetails;
import com.ruoyi.gateway.support.GatewayContext;
import com.ruoyi.gateway.support.GatewayFilterHelperChain;
import com.ruoyi.gateway.support.GatewayHelperFilter;
import com.ruoyi.gateway.support.feign.OauthFeignClient;
import com.ruoyi.gateway.support.service.GetUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Map;

/**
 * Created by enHui.Chen on 2020/10/19.
 */
@Slf4j
@Component
public class AuthenticationGatewayFilter implements GatewayHelperFilter {
    public AuthenticationGatewayFilter() {
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Autowired
    private GetUserDetailsService getUserDetailsService;
    @Lazy
    @Autowired
    private OauthFeignClient oauthFeignClient;

    @Override
    public void doFilter(GatewayContext gatewayContext, GatewayFilterHelperChain.InnerGatewayFilterHelperChain chain) {
        String token = gatewayContext.getAccessToken();
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("令牌不能为空");
        }

        // 获取当前用户信息
        Map<String, Object> user = oauthFeignClient.user(new Principal() {
            @Override
            public String getName() {
                return null;
            }
        }, "Bearer" + token);

        // 解析用户信息
        CuxUserDetails cuxUserDetails = getUserDetailsService.buildUserDetails(user);
        gatewayContext.setCuxUserDetails(cuxUserDetails);


//        String userkey = JwtUtils.getUserKey(claims);
//        boolean islogin = redisService.hasKey(getTokenKey(userkey));
//        if (!islogin) {
//            throw new RuntimeException("登录状态已过期");
//        }

//        gatewayContext.addHeader(SecurityConstants.USER_KEY, userkey);
//        gatewayContext.addHeader(SecurityConstants.DETAILS_USER_ID, userid);
//        gatewayContext.addHeader(SecurityConstants.DETAILS_USERNAME, username);
        gatewayContext.removeHeader(SecurityConstants.FROM_SOURCE);
        chain.doFilter(gatewayContext, chain);
    }

}
