package com.adroitandroid.controller;

import com.adroitandroid.GsonExclusionStrategy;
import com.adroitandroid.HibernateProxyTypeAdapter;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pv on 30/11/16.
 */
@RestController
class AbstractController {

    public static final String INVALID_USER_MESSAGE = "bad user";

    JsonElement prepareResponseFrom(Object src, String... includeAnnotated) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        return builder.setExclusionStrategies(new GsonExclusionStrategy(includeAnnotated)).create().toJsonTree(src);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * For optional check of userId
     * @return
     */
    Long getUserIdFromRequest() {
        Long principal = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal > 0 ? principal : null;
    }

    /**
     * For mandatory requirement of userId
     * @return
     */
    Long needUserId() {
        Long userIdFromRequest = getUserIdFromRequest();
        if (userIdFromRequest == null || userIdFromRequest < 0) {
            throw new IllegalArgumentException(INVALID_USER_MESSAGE);
        }
        return userIdFromRequest;
    }
}
