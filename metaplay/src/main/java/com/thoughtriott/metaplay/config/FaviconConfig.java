package com.thoughtriott.metaplay.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class FaviconConfig extends WebMvcConfigurationSupport {

	@Controller
    static class FaviconController {
        @RequestMapping("favicon.ico")
        String favicon(HttpServletResponse response) {
        	response.setContentType("image/x-icon");
            return "forward:/resources/img/favicon.ico";
        }
    }

}
