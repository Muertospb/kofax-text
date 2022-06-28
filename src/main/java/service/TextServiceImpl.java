package service;

import mvc.View;

import java.util.ArrayList;
import java.util.List;

public class TextServiceImpl implements TextService {

    @Override
    public List<FrameLine> computeFrame(List<List<String>> stringList, View view, StateHolder stateHolder) {
        List<FrameLine> frame = new ArrayList<>();
        while (true) {
            try {
                frame.add(computeFrameLine(stringList, view, stateHolder));
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        stateHolder.reset();
        return frame;
    }

    private FrameLine computeFrameLine(List<List<String>> stringList, View view, StateHolder stateHolder) {
        FrameLine frameLine = new FrameLine();
        double freeWidthSpace = view.getWidth() - view.getInsets().left - view.getInsets().right;
        String firstWordInLine = stringList.get(stateHolder.getCurrentStringPosition()).get(stateHolder.getCurrentWordPosition());
        if (stateHolder.isDivided()) {
            firstWordInLine = firstWordInLine.substring(stateHolder.getSubStringPosition());
        }
        int stringWidth = getStringWidth(view, stateHolder, firstWordInLine);
        if (freeWidthSpace - stringWidth >= view.getLEFT_BORDER()) {
            frameLine.getWordList().add(firstWordInLine);
            frameLine.getFontList().add(view.getFontNumber(stateHolder.getGlobalPosition()));
            frameLine.getSpacesList().add(stringWidth);
            freeWidthSpace = freeWidthSpace - stringWidth - view.getSPACER();
            stateHolder.setDivided(false);
            stateHolder.increaseGlobalPosition();
            stateHolder.setSubStringPosition(0);
            stateHolder.increaseWordPosition();
            if (stateHolder.getCurrentWordPosition() == stringList.get(stateHolder.getCurrentStringPosition()).size()) {
                stateHolder.increaseStringPosition();

                stateHolder.setCurrentWordPosition(0);
                return frameLine;
            }
        } else {
            int substringPosition = calculateSubstring(firstWordInLine, stateHolder, view, freeWidthSpace);
            stateHolder.setDivided(true);
            stateHolder.setSubStringPosition(stateHolder.getSubStringPosition() + substringPosition);
            frameLine.getWordList().add(firstWordInLine.substring(0, substringPosition));
            frameLine.getFontList().add(view.getFontNumber(stateHolder.getGlobalPosition()));
            frameLine.getSpacesList().add(view.getSPACER());
            return frameLine;
        }
        while (true) {
            String word = stringList.get(stateHolder.getCurrentStringPosition()).get(stateHolder.getCurrentWordPosition());
            stringWidth = getStringWidth(view, stateHolder, word);
            if (freeWidthSpace - stringWidth >= view.getLEFT_BORDER()) {
                frameLine.getWordList().add(word);
                frameLine.getFontList().add(view.getFontNumber(stateHolder.getGlobalPosition()));
                frameLine.getSpacesList().add(stringWidth);
                freeWidthSpace = freeWidthSpace - stringWidth - view.getSPACER();
                stateHolder.increaseGlobalPosition();
                stateHolder.increaseWordPosition();
                if (stateHolder.getCurrentWordPosition() == stringList.get(stateHolder.getCurrentStringPosition()).size()) {
                    stateHolder.increaseStringPosition();
                    stateHolder.setCurrentWordPosition(0);
                    return frameLine;
                }
            } else {
                break;
            }
        }
        return frameLine;
    }

    private int calculateSubstring(String firstWordInLine, StateHolder stateHolder, View view, double freeWidthSpace) {
        int pointer = firstWordInLine.length();
        while (!checkWordIsFit(view, stateHolder, freeWidthSpace, firstWordInLine.substring(0, pointer))) {
            pointer--;
        }
        return pointer;
    }

    private boolean checkWordIsFit(View view, StateHolder stateHolder, double freeWidthSpace, String word) {
        int stringWidth = getStringWidth(view, stateHolder, word);
        return freeWidthSpace - stringWidth >= view.getLEFT_BORDER();
    }

    private int getStringWidth(View view, StateHolder stateHolder, String word) {
        int stringWidth = view.getFontMetrics(stateHolder.getGlobalPosition()).stringWidth(word);
        return stringWidth;
    }
}
