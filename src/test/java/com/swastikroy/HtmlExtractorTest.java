package com.swastikroy;

import com.swastikroy.researchPaper.HtmlToTextExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * Created by Swastik on 1/6/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContextConfig.class, loader = AnnotationConfigContextLoader.class)
public class HtmlExtractorTest {

    @Inject
    HtmlToTextExtractor htmlToTextExtractor;

    @Test
    public void test1() throws IOException {
        String url = "https://in.style.yahoo.com/veteran-actor-om-puri-dies-age-66-044734393.html";
        List<String> lines = htmlToTextExtractor.extractTextFromHtml(url , 0.5 , 0.5);
        System.out.println(lines);
    }
}
