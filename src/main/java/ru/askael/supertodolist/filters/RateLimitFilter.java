package ru.askael.supertodolist.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.askael.supertodolist.services.RateLimitService;
import ru.askael.supertodolist.services.UserContext;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alikin E.A. on 2020-10-01.
 */
@Service
public class RateLimitFilter extends OncePerRequestFilter {

    @Autowired
    UserContext userContext;

    @Autowired
    RateLimitService rateLimitService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (userContext.getUserLogin() != null && rateLimitService.tryConsumeAndReturnRemaining(userContext.getUserLogin()) == 0) {
            httpServletResponse.sendError(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
