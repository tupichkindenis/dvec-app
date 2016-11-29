package com.whitesoft.messaging.util;


import java.io.IOException;
import java.io.Writer;

public class GhettoAsciiWriter extends Writer {
    private final Writer out;

    public GhettoAsciiWriter(Writer out) {
        this.out = out;
    }

    @Override public void write(char[] buffer, int offset, int count) throws IOException {
        for (int i = 0; i < count; i++) {
            char c = buffer[i + offset];
            if (c <= 0x7f) {
                out.write(c);
            } else {
                out.write(String.format("\\u%04x", (int) c));
            }
        }
    }

    @Override public void flush() throws IOException {
        out.flush();
    }

    @Override public void close() throws IOException {
        out.close();
    }
}