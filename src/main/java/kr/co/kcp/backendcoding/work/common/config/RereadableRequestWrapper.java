package kr.co.kcp.backendcoding.work.common.config;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/*
* POST로 전달된 application/json 타입의 데이터를 Interceptor에서 처리 하기 위해 작성된 Wrapper
*
* */
public class RereadableRequestWrapper extends HttpServletRequestWrapper {

    private final Charset encoding;
    private byte[] rawData;

    /*
    *  LogInterceptor에서 InputStream으로 읽은후 이미 읽었던 데이터로 다시 InputStream을 controller에 돌려줄수 있도록 작성
    * */
    public RereadableRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        String characterEncoding = request.getCharacterEncoding();
        if (StringUtils.isBlank(characterEncoding)) {
            characterEncoding = StandardCharsets.UTF_8.name();
        }
        this.encoding = Charset.forName(characterEncoding);

        try {
            InputStream inputStream = request.getInputStream();
            this.rawData = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
    }

    @Override
    public ServletRequest getRequest() {
        return super.getRequest();
    }

}
