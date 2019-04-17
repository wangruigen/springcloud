package com.study.springcloud;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * zuul回退类
 */
@Component
public class MyFallBackProvider implements FallbackProvider {
    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        if (cause instanceof HystrixTimeoutException){
            return response(HttpStatus.GATEWAY_TIMEOUT);
        }else {
            return this.fallbackResponse();
        }
    }

    /**
     * 指定哪些微服务提供回退，*表示所有服务
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return this.response(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ClientHttpResponse response(final HttpStatus status){
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                String resultBody = "服务不可用，请稍后重试。";
                return new ByteArrayInputStream(resultBody.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
                httpHeaders.setContentType(mediaType);
                return httpHeaders;
            }
        };
    }
}
