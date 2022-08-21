package com.games.game2.common.interceptor;

import com.games.game2.common.utils.NetUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
public class LoggerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("===============================================");
        log.info("==================== BEGIN ====================");
        log.info("Request URI ===> " + request.getRequestURI());
        log.info("remoteAddr : " + NetUtils.getIp(request));
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("==================== END ======================");
        log.info("===============================================");
        long midTime = System.currentTimeMillis();
        //정적 리소스들은 modelAndView null
        if (modelAndView != null) {
            //view 전달할 자료 수정 가능
            //modelAndView.addObject("message", "Hello interceptor");
        }
        request.setAttribute("midTime", midTime);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("==================== AFTER ======================");
        log.info("===============================================");

        long endTime = System.currentTimeMillis();
        Object startTimeObj = request.getAttribute("startTime");
        Object midTimeObj = request.getAttribute("midTime");
        log.info("servlet: {}", request.getServletPath());
        if (midTimeObj != null) {
            log.info("Handler 동작시간: {}", endTime - (Long) midTimeObj);
        }
        log.info("전체 동작시간: {}", endTime - (Long) startTimeObj);

        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            Class clazz = method.getDeclaringClass();

            String className = clazz.getName();
            String classSimpleName = clazz.getSimpleName();
            String methodName = method.getName();

            log.info("[ACCESS CONTROLLER] " + className + "." + methodName);
            
            //AccessLog 테이블 설정 시 사용
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
}
