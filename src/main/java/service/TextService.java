package service;

import mvc.View;

import java.util.List;

public interface TextService {
    List<FrameLine> computeFrame(List<List<String>> stringList, View view, StateHolder stateHolder);
}
