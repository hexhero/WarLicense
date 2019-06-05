package com.yangb.cer;

import javax.servlet.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * cer
 *
 * @author create by yangb in 2019/6/5
 */
public class WebLicense implements Filter {

    private static boolean license = false;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static String err="";

    public void init(FilterConfig filterConfig) throws ServletException {

        try {
            URL resource = WebLicense.class.getClassLoader().getResource("/cert.cer");
            if(resource != null){
                File file = new File(resource.getFile());
                if (file.exists()) {
                    FileInputStream inputStream = new FileInputStream(file);
                    String cert = CerUtil.decoder(inputStream);
                    String[] args = cert.split(":");
                    if(args.length >= 4){
                        String mac = CerUtil.getMacByIp(args[0]);
                        if(mac.toUpperCase().equals(args[1].toUpperCase())){
                            Date current = new Date();
                            Date begin_date = format.parse(args[2]);
                            Date end_date = format.parse(args[3]);
                            if(current.getTime() > begin_date.getTime() && current.getTime() < end_date.getTime()){
                                license = true;
                                System.out.println("-----------------------证书授权成功-----------------------");
                                System.out.println("---------------著作权归浙江按普利通信技术所有-----------------");
                                System.out.println("------------------------@yangb--------------------------");
                                return;
                            }
                            throw new Exception("证书不在有效期");
                        }
                        throw new Exception("硬件不匹配");
                    }
                    throw new Exception("证书错误");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            err = "证书不存在";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            err = "证书加载失败";
        } catch (IOException e) {
            e.printStackTrace();
            err = "证书读取错误";
        } catch (ParseException e) {
            e.printStackTrace();
            err = "证书解析错误";
        } catch (Exception e){
            e.printStackTrace();
            err = e.getMessage();
        }
        err = "系统授权错误:" + err;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(license)
            filterChain.doFilter(servletRequest,servletResponse);
        else{
            servletResponse.setContentType("text/html; charset=utf-8");
            servletResponse.getWriter().println(err);
        }
    }

    public void destroy() {

    }
}
