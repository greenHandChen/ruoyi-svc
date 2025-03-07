package com.ruoyi.gateway.support.filter;


import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.TokenConstants;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.gateway.config.properties.IgnoreWhiteProperties;
import com.ruoyi.gateway.support.GatewayContext;
import com.ruoyi.gateway.support.GatewayFilterHelperChain;
import com.ruoyi.gateway.support.GatewayHelperFilter;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created by enHui.Chen on 2020/10/20.
 */
@Slf4j
@Component
public class PermissionCheckGatewayFilter implements GatewayHelperFilter {
    private final AntPathMatcher matcher = new AntPathMatcher();
    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;
    @Override
    public int filterOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public void doFilter(GatewayContext gatewayContext, GatewayFilterHelperChain.InnerGatewayFilterHelperChain chain) {
        ServerHttpRequest request = gatewayContext.getServerHttpRequest();
        String url = request.getURI().getPath();
        // 跳过不需要验证的路径
        if (StringUtils.matches(url, ignoreWhite.getWhites())) {
            return;
        }

        chain.doFilter(gatewayContext, chain);
    }




}
