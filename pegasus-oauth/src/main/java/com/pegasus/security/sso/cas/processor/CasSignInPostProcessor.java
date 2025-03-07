package com.pegasus.security.sso.cas.processor;

import com.pegasus.security.config.PeSecurityProperties;
import com.pegasus.security.sso.cas.constants.CasConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author enhui.chen
 * @desc cas登录processor
 * @date 2021-03-24 16:39:32
 */
@Component
@ConditionalOnProperty(value = PeSecurityProperties.PREFIX + ".sso.enabled", havingValue = "true")
public class CasSignInPostProcessor implements SecurityProcessor {

    /**
     * @param request
     * @param response
     * @return
     * @date 2021-03-24 16:40:16
     * @desc 保存记录ticket
     **/
    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) {
        String casTicket = request.getParameter("ticket");
        if (!StringUtils.isEmpty(casTicket)) {
            request.getSession(false).setAttribute(CasConstant.SSO_CAS_TICKET, casTicket);
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
