package com.ruoyi.gateway.support.feign;

import com.ruoyi.gateway.support.feign.fallback.OauthFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * Created by enHui.Chen on 2020/10/26.
 */
@FeignClient(
        value = "ruoyi-oauth",
        fallbackFactory = OauthFeignClientFallbackFactory.class,
        path = "/oauth"
)
public interface OauthFeignClient {

    @GetMapping("/api/oauth/user")
    Map<String,Object> user(@RequestParam Principal principal, @RequestHeader(name = "Authorization") String Token);
}
