package com.training.bringiton.test;

import com.training.bringiton.page.HomePage;
import com.training.bringiton.page.ResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class PastebinTest {
    private WebDriver driver;
    private static final String PASTE_CODE = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String HEAD_LINE = "how to gain dominance among developers";
    private static final String SYNTAX_HIGHLIGHTING = "Bash";
    private ResultPage resultPage;

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        resultPage = new HomePage(driver)
                .openPage()
                .addPasteCode(PASTE_CODE)
                .selectSyntaxHighlighting(SYNTAX_HIGHLIGHTING)
                .selectPasteExpiration("10 Minutes")
                .addPasteName(HEAD_LINE)
                .createPaste();
    }

    @Test
    public void isResultPageHeadLineIsCorrect() {
        String resultPageHeadLineText = resultPage
                .getResultPageHeadLineText();
        Assert.assertEquals(HEAD_LINE, resultPageHeadLineText);
    }

    @Test
    public void isSyntaxHighlightingBash() {
        String resultPageSyntaxHighlighting = resultPage
                .getSyntaxHighlightingFromResultPage();
        Assert.assertEquals(SYNTAX_HIGHLIGHTING, resultPageSyntaxHighlighting);
    }

    @Test
    public void isResultPageCodeBoxContainsCorrectCode() {
        String resultPageCode = resultPage
                .getPastedCodeFromResultPage();
        Assert.assertEquals(PASTE_CODE, resultPageCode);
    }

    @AfterClass(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
