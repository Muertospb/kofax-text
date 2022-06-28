package mvc;

import lombok.Getter;
import lombok.Setter;
import service.FrameLine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Getter
@Setter
public class View extends Frame {
    private List<Font> fontList;
    private int oneSizedWordsInRow;
    private final int LEFT_BORDER = 10;
    private int topBorder;
    private final int LINE_SPACER = 10;
    private final int SPACER = 10;
    private int verticalSpacer;

    public void initFonts(Properties properties) {
        this.fontList = new ArrayList<>();
        String systemFontName = Font.decode(null).getName();
        if (properties != null) {
            oneSizedWordsInRow = Integer.parseInt(properties.getProperty("oneSizedWordsInRow"));
            String fontSizes = properties.getProperty("fontSizes");
            String[] fontSizesList = fontSizes.split(",");
            for (String size : fontSizesList) {
                Font font = new Font(systemFontName, Font.PLAIN, Integer.parseInt(size));
                fontList.add(font);
            }
        } else {
            System.out.println("Cannot load \"application.properties\", will use default properties");
            oneSizedWordsInRow = 2;
            fontList.add(new Font(systemFontName, Font.PLAIN, 9));
            fontList.add(new Font(systemFontName, Font.PLAIN, 12));
        }
        verticalSpacer = fontList.stream().mapToInt(Font::getSize).max().getAsInt() + LINE_SPACER;
        topBorder = fontList.stream().mapToInt(Font::getSize).max().getAsInt();
    }

    public void paint(List<FrameLine> frame) {
        Graphics g = getGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());
        int y = topBorder + getInsets().top;
        for (FrameLine frameLine : frame) {
            int x = LEFT_BORDER;
            for (int i = 0; i < frameLine.getWordList().size(); i++) {
                g.setFont(fontList.get(frameLine.getFontList().get(i)));
                g.drawString(frameLine.getWordList().get(i), x, y);
                x += frameLine.getSpacesList().get(i) + SPACER;
            }
            y += verticalSpacer;
        }
    }

    public FontMetrics getFontMetrics(long globalPosition) {
        int fontNumber = getFontNumber(globalPosition);
        return getGraphics().getFontMetrics(fontList.get(fontNumber));
    }

    public int getFontNumber(long globalPosition) {
        int fontNumber = (int) (globalPosition / oneSizedWordsInRow) % fontList.size();
        return fontNumber;
    }
}
