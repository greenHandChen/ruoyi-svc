package com.ruoyi.gateway.config;

import com.ruoyi.gateway.handler.SentinelFallbackHandler;
import com.ruoyi.gateway.support.GatewayFilterHelperChain;
import com.ruoyi.gateway.support.GatewayHelperFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * 网关限流配置
 *
 * @author ruoyi
 */
@Configuration
public class GatewayConfig {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelGatewayExceptionHandler() {
        return new SentinelFallbackHandler();
    }

    /**
     * @Author: enHui.Chen
     * @Description: 内置拦截链
     * @Data 2020/10/20
     */
    @Bean
    public GatewayFilterHelperChain gatewayFilterHelperChain(List<GatewayHelperFilter> gatewayHelperFilters) {
        return new GatewayFilterHelperChain(gatewayHelperFilters);
    }
}