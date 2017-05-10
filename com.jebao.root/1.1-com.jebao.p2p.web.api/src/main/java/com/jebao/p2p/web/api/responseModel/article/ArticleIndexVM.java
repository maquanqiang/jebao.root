package com.jebao.p2p.web.api.responseModel.article;

import com.jebao.jebaodb.entity.article.TbArticle;
import com.jebao.p2p.web.api.responseModel.ViewModel;

/**
 * Created by Administrator on 2017/1/6.
 */
public class ArticleIndexVM extends ViewModel {
    public ArticleIndexVM(TbArticle entity) {
        this.id = entity.getaId();
        String tempContent = entity.getaContent();
        int firstIndex = tempContent.indexOf("</p>");
        if (firstIndex > -1 && firstIndex < tempContent.length() - 4) {
            this.content = tempContent.substring(0, firstIndex + 4);
        } else {
            this.content = tempContent;
        }
    }

    private Long id;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
