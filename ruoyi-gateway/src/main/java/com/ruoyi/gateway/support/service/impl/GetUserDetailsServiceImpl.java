package com.ruoyi.gateway.support.service.impl;

import com.ruoyi.gateway.security.dto.CuxUserDetails;
import com.ruoyi.gateway.support.service.GetUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by enHui.Chen on 2020/10/26.
 */
@Slf4j
@Service
public class GetUserDetailsServiceImpl implements GetUserDetailsService {
    @Override
    public CuxUserDetails buildUserDetails(Map<String, Object> userMap) {
        Map<String, Object> userAuthentication = (LinkedHashMap<String, Object>) userMap.get("principal");
        if (userAuthentication != null) {
            userMap = userAuthentication;
        }


        return null;
    }
}
