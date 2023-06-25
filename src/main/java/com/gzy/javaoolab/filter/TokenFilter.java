package com.gzy.javaoolab.filter;

import com.gzy.javaoolab.entity.User;
import com.gzy.javaoolab.service.UserService;
import com.gzy.javaoolab.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = {"/chat/*","/relation/*","/history/*","/group/*","/login/test/*"})
//注：加上@component的话，主类不比写scan，但是上面配的pattern不生效，拦截全部
public class TokenFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private UserService userService;

    @Value("${jwt.config.failureTime}")
    long failureTime;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        //logger.info("token过滤器---------start---------------");

        //设置允许跨域的配置
        //允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        //rep.setHeader("Access-Control-Allow-Origin", "*");
        //允许的访问方法
        //rep.setHeader("Access-Control-Allow-Methods","POST, GET");
        //Access-Control-Max-Age 用于 CORS 相关配置的缓存
        //rep.setHeader("Access-Control-Max-Age", "3600");
        //rep.setHeader("Access-Control-Allow-Headers","token");
        //rep.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept");


        rep.setCharacterEncoding("UTF-8");
        //rep.setContentType("application/json; charset=utf-8");
        String token = req.getHeader("token");//header方式
        String method = ((HttpServletRequest) request).getMethod();


        if (method.equals("OPTIONS")) {
            rep.setStatus(HttpServletResponse.SC_OK);
        } else {
            if (null == token || token.isEmpty()) {
                logger.info("用户授权认证没有通过!客户端请求参数中无token信息");
                rep.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                rep.setHeader("code", String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
                rep.setHeader("msg",  "token is none!");
                //chain.doFilter(req, rep);
            } else {
                try {
                    Claims claims = jwtUtils.parseJwt(token);
                    String userId = claims.getId();
                    Date expiration = claims.getExpiration();
                    long validTime = expiration.getTime() - System.currentTimeMillis();

                    if (userService.getLoginTime(Integer.valueOf(userId))==null||validTime < 0) {
                        logger.info("token已过期！");
                        rep.setHeader("code", String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
                        rep.setHeader("msg", "token is overtime！");
                        return;//不允许继续
                    }else if(expiration.getTime()-failureTime<userService.getLoginTime(Integer.valueOf(userId)).getTime()-5000) {
                        logger.info("该用户已在其他地点登录，此token无效！");
                        rep.setHeader("code", String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
                        rep.setHeader("msg", "has been login at other place,this token is expired");
                        return;
                    }else if (validTime < failureTime / 10) {
                        User user = userService.load(Integer.valueOf(userId));
                        logger.info("token的有效期小于过期时间的10%！");
                        Map<String, Object> data = new HashMap<>();
                        data.put("email", user.getEmail());
                        String newToken = jwtUtils.createJwt(user.getId()+"", user.getName(), data);
                        logger.info("已生成新的token：" + newToken);
                        rep.setHeader("code", String.valueOf(HttpServletResponse.SC_OK));
                        rep.setHeader("msg", "token has refreshed！");
                        rep.setHeader("newToken", newToken);
                    } else {
                        logger.debug("用户授权认证通过!");
                        rep.setHeader("code", String.valueOf(HttpServletResponse.SC_OK));
                        req.setAttribute("uid", userId);
                    }
                    chain.doFilter(req, rep);
                } catch (Exception exception) {
                    if (SignatureException.class.getName().equals(exception)) {
                        logger.warn("token sign解析失败");
                        rep.setHeader("code", String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
                        rep.setHeader("msg", "token is invalid! ");
                    } else if (MalformedJwtException.class.getName().equals(exception)) {
                        logger.warn("token sign解析失败");
                        rep.setHeader("code", String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
                        rep.setHeader("msg", "token is invalid! ");
                    }else{
                        logger.warn("token 未知原因解析失败:{}",exception.getMessage());
                        rep.setHeader("code", String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
                        rep.setHeader("msg", "token is invalid! ");
                        //exception.printStackTrace();
                    }
                    //chain.doFilter(req, rep);
                }
            }
        }
    }


    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        logger.info("初始化filter");
    }
}
