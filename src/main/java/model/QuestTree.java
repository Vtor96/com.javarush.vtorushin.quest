package model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class QuestTree {
    private final Map<String, QuestStep> steps = new HashMap();
    public final String START_STEP_ID = "start";

    public QuestTree() {
        this.initializeSteps();
    }

    private void initializeSteps() {
        Map<String, String> startChoices = new LinkedHashMap();
        startChoices.put("path_left", "По левой тропинке");
        startChoices.put("path_right", "По правой тропинке");
        this.steps.put("start", new QuestStep("Вы в тёмном лесу. Видите две тропинки. По какой пойдёте?", startChoices));
        Map<String, String> leftChoices = new LinkedHashMap();
        leftChoices.put("cave_enter", "Войти в пещеру");
        leftChoices.put("cave_ignore", "Пройти мимо");
        this.steps.put("path_left", new QuestStep("На левой тропинке вы видите пещеру. Войти?", leftChoices));
        Map<String, String> caveEnterChoices = new LinkedHashMap();
        caveEnterChoices.put("treasure", "Открыть сундук");
        caveEnterChoices.put("no_treasure", "Не открывать");
        this.steps.put("cave_enter", new QuestStep("В пещере вы видите сундук. Открыть?", caveEnterChoices));
        this.steps.put("cave_ignore", new QuestStep("Вы решили не заходить в пещеру и заблудились в лесу!", false));
        Map<String, String> rightChoices = new LinkedHashMap();
        rightChoices.put("help", "Помочь путнику");
        rightChoices.put("ignore", "Пройти мимо");
        this.steps.put("path_right", new QuestStep("На правой тропинке вы встретили путника. Помочь ему?", rightChoices));
        this.steps.put("help", new QuestStep("Путник оказался добрым волшебником и подарил вам карту сокровищ!", true));
        this.steps.put("ignore", new QuestStep("Путник разозлился и наслал на вас заклятие!", false));
        this.steps.put("treasure", new QuestStep("Поздравляем! Вы нашли сундук с золотом и драгоценностями!", true));
        this.steps.put("no_treasure", new QuestStep("Сундук оказался мимиком и съел игрока!", false));
    }

    public QuestStep getStep(String stepId) {
        return (QuestStep) this.steps.get(stepId);
    }

    public String getStartStepId() {
        return "start";
    }

    public boolean hasStep(String stepId) {
        return this.steps.containsKey(stepId);
    }

    public int getStepCount() {
        return this.steps.size();
    }
}
