package cn.jumper.bone.singlenode.graceful;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * 实现友好关闭。当关闭事件出发，先拒绝所有链接，等待处理中的请求处理完成后，再执行关闭操作。<br/>
 * 1》原本没有友好关闭是怎样的？ 被直接terminal<br/>
 * 2》当关闭时，再发起链接请求你，客户端会有怎样反应？等待，直到terminal ThradPool.Connection reset by peer<br/>
 * https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-programmatic-embedded-container-customization<br/>
 * https://dzone.com/articles/graceful-shutdown-spring-boot-applications <br/>
 * https://juejin.im/post/5ae17c95518825670d72ce23<br/>
 * https://www.programcreek.com/java-api-examples/index.php?api=org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer<br/>
 */
//@Component
public class GracefulShutdownHandler implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>, ApplicationListener<ContextClosedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GracefulShutdownHandler.class);
    private Connector connector;

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers((connector) -> {
            this.connector = connector;
        });
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        /**
         * 此出如果用stop方法，客户端请求时，会出现拒绝连接；但调用此方法后所有的请求线程已被terminal，没有graceful shutdown
         */
        // connector.stop();
        /**
         * 会接受客户端的连接，但是不进行请求处理。直到connector 的 threadPool 调用 terminal 方法。客户端会一直等待，然后connection reset by peer
         */
        connector.pause();

        LOGGER.info("Connector has been paused...");
        Executor executor = connector.getProtocolHandler().getExecutor();
        LOGGER.info(executor.getClass().getName());
        if (!(executor instanceof ThreadPoolExecutor)) {
            LOGGER.error("!!!!!!!!!!!!!!!!!!!!!");
            return;
        }
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
        threadPoolExecutor.shutdown();
        try {
            LOGGER.info("Wait connector threadpool terminal...");
            threadPoolExecutor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Graceful shutdown done...");

    }
}
