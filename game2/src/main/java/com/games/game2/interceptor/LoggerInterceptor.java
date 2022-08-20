package com.games.game2.interceptor;

import com.games.game2.utils.NetUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestUri = request.getRequestURI();
        String remoteAddr = NetUtils.getIp(request);

        log.info("requestURL : " + requestUri);
        log.info("remoteAddr : " + remoteAddr);

        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            Class clazz = method.getDeclaringClass();

            String className = clazz.getName();
            String classSimpleName = clazz.getSimpleName();
            String methodName = method.getName();

            log.info("[ACCESS CONTROLLER] " + className + "." + methodName);

//            AccessLog accessLog = new AccessLog();
//
//            accessLog.setRequestUri(requestUri);
//            accessLog.setRemoteAddr(remoteAddr);
//            accessLog.setClassName(className);
//            accessLog.setClassSimpleName(classSimpleName);
//            accessLog.setMethodName(methodName);

//            mapper.create(accessLog);
        }
        else {
            log.info("handler : " + handler);
        }

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("===============================================");
        log.debug("==================== BEGIN ====================");
        log.debug("Request URI ===> " + request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("==================== END ======================");
        log.debug("===============================================");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
