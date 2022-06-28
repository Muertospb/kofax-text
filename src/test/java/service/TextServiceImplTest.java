package service;

import mvc.TestUtil;
import mvc.View;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TextServiceImplTest {

    @Test
    void computeFrameTest() {
        //given
        View view = Mockito.mock(View.class);
        FontMetrics fontMetrics = Mockito.mock(FontMetrics.class);
        StateHolder stateHolder = new StateHolder();

        when(view.getWidth()).thenReturn(200);
        when(view.getInsets()).thenReturn(new Insets(10, 10, 10, 10));
        when(view.getFontMetrics(any(Long.class))).thenReturn(fontMetrics);
        when(fontMetrics.stringWidth(any(String.class))).thenReturn(30);

        //when
        List<FrameLine> frameLines = new TextServiceImpl().computeFrame(TestUtil.getList(), view, stateHolder);

        //then
        assertEquals(100, frameLines.size());
        assertEquals(frameLines.get(0).getWordList(), frameLines.get(98).getWordList());
        assertEquals(frameLines.get(1).getWordList(), frameLines.get(99).getWordList());
        assertEquals(6, frameLines.get(0).getFontList().size());
        assertEquals(2, frameLines.get(1).getSpacesList().size());
    }
}