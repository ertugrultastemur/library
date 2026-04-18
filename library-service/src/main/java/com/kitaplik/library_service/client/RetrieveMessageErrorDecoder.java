package com.kitaplik.library_service.client;

import com.kitaplik.library_service.exception.BookNotFoundException;
import com.kitaplik.library_service.exception.ExceptionMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage exceptionMessage = null;
        try(InputStream body = response.body().asInputStream()){
            exceptionMessage = new ExceptionMessage((String)response.headers().get("date").toArray()[0],
                    response.status(),
                    HttpStatus.resolve(response.status()).getReasonPhrase(),
                    IOUtils.toString(body, StandardCharsets.UTF_8),
                    response.request().url());
        }catch (IOException e){
            return new IOException(e.getMessage());
        }
        switch (response.status()) {
            case 404:
                throw new BookNotFoundException(exceptionMessage);
            default:
                return defaultErrorDecoder.decode(methodKey, response);
        }
    }
}
