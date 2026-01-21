package com.example.wrap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;

public class ByteResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private ServletOutputStream outputStream;
    private PrintWriter writer;

    public ByteResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() {
        if (outputStream == null) {
            outputStream = new ServletOutputStream() {
                @Override
                public void write(int b) throws IOException {
                    buffer.write(b);
                }
                @Override
                public boolean isReady() { return true; }
                @Override
                public void setWriteListener(javax.servlet.WriteListener listener) {}
            };
        }
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer == null) {
            writer = new PrintWriter(buffer, true);
        }
        return writer;
    }

    /** 获取 SDK 写出的完整字节内容 */
    public byte[] getCapturedBytes() {
        return buffer.toByteArray();
    }

    /** 获取为字符串（一般是 JSON） */
    public String getCapturedString() {
        try {
            return buffer.toString(getCharacterEncoding() != null 
                ? getCharacterEncoding() 
                : "UTF-8");
        } catch (Exception e) {
            return buffer.toString();
        }
    }
}
