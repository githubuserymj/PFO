package com.offer.config.interceptor;

import com.offer.pojo.PfoUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * Created by YMJ on 2019-09-26.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{
    //访问前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("拦截开始");
        HttpSession session = request.getSession();
        PfoUser user = (PfoUser) session.getAttribute("user");
        if(null != user){
//            System.out.println("seesion user"+user.toString());
            return true;//放行
        }
        response.sendRedirect("/pfo/html/user/login.html");
        return false;//拦截
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
