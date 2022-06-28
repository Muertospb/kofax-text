package mvc;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewTest {
    private View view;

    @Test
    void initFontsTest() {
        //given
        Properties properties = TestUtil.getTestProperties();
        view = new View();

        //when
        view.initFonts(properties);

        //then
        assertEquals(3, view.getFontList().size());
        assertEquals(5, view.getOneSizedWordsInRow());
        assertEquals(15, view.getTopBorder());
        assertEquals(25, view.getVerticalSpacer());
    }

    @Test
    void initFontsTestWhenPropertiesIsNull() {
        //given
        view = new View();

        //when
        view.initFonts(null);

        //then
        assertEquals(2, view.getFontList().size());
        assertEquals(2, view.getOneSizedWordsInRow());
        assertEquals(12, view.getTopBorder());
        assertEquals(22, view.getVerticalSpacer());
    }

    @Test
    void getFontMetricsTest() {
        //given
        view = new View();
        view.initFonts(TestUtil.getTestProperties());
        view.setOneSizedWordsInRow(3);
        view.setSize(400, 400);
        view.setVisible(true);

        //when
        FontMetrics fontMetrics = view.getFontMetrics(70);
        Font font = fontMetrics.getFont();
        String expectedFontName = Font.decode(null).getName();

        //then
        assertEquals(expectedFontName, font.getName());
        assertEquals(15, font.getSize());

        //after
        view.setVisible(false);
    }

    @Test
    void getFontNumberTest() {
        //given
        view = new View();
        view.initFonts(TestUtil.getTestProperties());
        view.setOneSizedWordsInRow(3);

        //when
        int fontNumber = view.getFontNumber(70);

        //then
        assertEquals(2, fontNumber);
    }
}