package com.ruoyi.gateway.support;

import com.ruoyi.gateway.security.dto.CuxUserDetails;
import com.ruoyi.gateway.support.utils.ServerRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by enHui.Chen on 2020/10/16.
 */
public class GatewayContext {
    private ServerHttpRequest serverHttpRequest;

    private ServerHttpResponse serverHttpResponse;

    private CuxUserDetails cuxUserDetails;

    private Map<String, String> additionalHeaders = new HashMap<>();

    private Set<String> removeHeaders = new HashSet<>();

    private static final String ACCESS_TOKEN_HEADER = "Authorization";
    private static final String ACCESS_TOKEN_TYPE = "Bearer ";

    public ServerHttpRequest getServerHttpRequest() {
        return serverHttpRequest;
    }

    public void setServerHttpRequest(ServerHttpRequest serverHttpRequest) {
        this.serverHttpRequest = serverHttpRequest;
    }

    public ServerHttpResponse getServerHttpResponse() {
        return serverHttpResponse;
    }

    public void setServerHttpResponse(ServerHttpResponse serverHttpResponse) {
        this.serverHttpResponse = serverHttpResponse;
    }

    public CuxUserDetails getCuxUserDetails() {
        return cuxUserDetails;
    }

    public void setCuxUserDetails(CuxUserDetails cuxUserDetails) {
        this.cuxUserDetails = cuxUserDetails;
    }

    public Map<String, String> getAdditionalHeaders() {
        return additionalHeaders;
    }

    public void setAdditionalHeaders(Map<String, String> additionalHeaders) {
        this.additionalHeaders = additionalHeaders;
    }

    public String getRequestPath() {
        return serverHttpRequest.getURI().getPath();
    }

    public String getAccessToken() {
        String accessToken = ServerRequestUtil.getHeaderByName(serverHttpRequest, ACCESS_TOKEN_HEADER);
        if (StringUtils.startsWithIgnoreCase(accessToken, ACCESS_TOKEN_TYPE)) {
            return accessToken.substring(ACCESS_TOKEN_TYPE.length());
        }
        if (StringUtils.isEmpty(accessToken)) {
            MultiValueMap<String, String> queryParams = serverHttpRequest.getQueryParams();
            return queryParams.getFirst("access_token");
        }
        return accessToken;
    }


    public void addHeader(String headerName, String headerValue) {
        this.additionalHeaders.put(headerName, headerValue);
    }

    public void removeHeader(String headerName) {
        this.removeHeaders.add(headerName);
    }


}
