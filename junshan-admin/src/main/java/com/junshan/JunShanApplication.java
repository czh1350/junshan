package com.junshan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class JunShanApplication
{
    private static final Logger log = LoggerFactory.getLogger(JunShanApplication.class);

    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        ConfigurableApplicationContext application = SpringApplication.run(JunShanApplication.class, args);
        startEndMessage(application);
    }

    private static void startEndMessage(ConfigurableApplicationContext application) {
        try {
            Environment env = application.getEnvironment();
            String ip = InetAddress.getLocalHost().getHostAddress();
            String port = env.getProperty("server.port");
            String path = env.getProperty("server.servlet.context-path");
            path = path==null?"":path;
            log.info("\n----------------------------------------------------------\n\t" +
                    "Application is running! Access URLs:\n\t" +
                    "Local: \t\thttp://localhost:" + port + path + "\n\t" +
                    "PC: \t\thttp://localhost:80" + path + "\n\t" +
                    "External: \thttp://" + ip + ":" + port + path + "v1/index.html\n\t" +
                    "swagger-ui: \thttp://" + ip + ":" + port + path + "swagger-ui.html\n\t" +
                    "开发前阅读项目帮助文件  \n\t" +
                    "----------------------------------------------------------");
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
}
