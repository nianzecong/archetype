package com.yesway.wechat.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yesway.wechat.cfg.AppConfig;

public class InitListener implements ServletContextListener {
	private static final Logger log = LoggerFactory.getLogger(InitListener.class);
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("【系统关闭，当前系统时间：" + df.format(new Date()) + "】");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		AppConfig.configure(arg0.getServletContext().getRealPath("/") + "/WEB-INF/appconfig.xml");
		log.info("【系统启动，当前系统时间：" + df.format(new Date()) + "】");
	}

}
