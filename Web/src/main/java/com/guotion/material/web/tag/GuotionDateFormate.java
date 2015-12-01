package com.guotion.material.web.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2014/12/16 0016.
 */
public class GuotionDateFormate extends TagSupport {
    private long value = 0L;
    private String pattern;

    public int doEndTag(){
        if(pattern != null){
            String patternDate = new SimpleDateFormat(pattern).format(new Date(value));
            JspWriter jspWriter = pageContext.getOut();
            try {
                jspWriter.print(patternDate);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SKIP_BODY;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
