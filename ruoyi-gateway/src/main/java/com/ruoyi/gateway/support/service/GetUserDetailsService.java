package com.ruoyi.gateway.support.service;


import com.ruoyi.gateway.security.dto.CuxUserDetails;

import java.util.Map;

/**
 * Created by enHui.Chen on 2020/10/26.
 */
public interface GetUserDetailsService {
    CuxUserDetails buildUserDetails(Map<String, Object> userMap);
}
