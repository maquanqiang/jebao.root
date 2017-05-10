package com.jebao.erp.web.conf;

import com.jebao.erp.web.utils.contract.PdfCustom;
import com.jebao.jebaodb.dao.dao.investment.TbInvestInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/12/8.
 */
@Configuration
public class StartupListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    @Autowired
    private PdfCustom pdfCustom;
    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        int serverPort = event.getEmbeddedServletContainer().getPort();
        EmbeddedServletInstance.setServerPort(serverPort);
        pdfCustom.doTaskForCreateContractPdf();
    }
}
