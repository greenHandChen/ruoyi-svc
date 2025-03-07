package com.ruoyi.gateway.support.feign.fallback;

import com.ruoyi.gateway.support.feign.OauthFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.security.Principal;
import java.util.Map;

/**
 * Created by enHui.Chen on 2020/10/26.
 */
@Slf4j
public class OauthFeignClientFallbackFactory implements FallbackFactory<OauthFeignClient> {
    @Override
    public OauthFeignClient create(Throwable throwable) {
        return new OauthFeignClient() {

            @Override
            public Map<String, Object> user(Principal principal, String Token) {
                return null;
            }
        };
    }
}
