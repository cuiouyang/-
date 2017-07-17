package com.bsi.ms.model;

public class TestSet {
    private String courseName;

    private Integer radio;

    private Integer choice;

    private Integer judge;

    private Integer shortAnswer;

    private Integer radioScore;

    private Integer choiceScore;

    private Integer judgeScore;

    private Integer shortAnswerScore;

    private Integer radioDifficultyScore;

    private Integer choiceDifficultyScore;

    private Integer judgeDifficultyScore;

    private Integer answerDifficultyScore;

    private Integer totalTime;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getRadio() {
        return radio;
    }

    public void setRadio(Integer radio) {
        this.radio = radio;
    }

    public Integer getChoice() {
        return choice;
    }

    public void setChoice(Integer choice) {
        this.choice = choice;
    }

    public Integer getJudge() {
        return judge;
    }

    public void setJudge(Integer judge) {
        this.judge = judge;
    }

    public Integer getShortAnswer() {
        return shortAnswer;
    }

    public void setShortAnswer(Integer shortAnswer) {
        this.shortAnswer = shortAnswer;
    }

    public Integer getRadioScore() {
        return radioScore;
    }

    public void setRadioScore(Integer radioScore) {
        this.radioScore = radioScore;
    }

    public Integer getChoiceScore() {
        return choiceScore;
    }

    public void setChoiceScore(Integer choiceScore) {
        this.choiceScore = choiceScore;
    }

    public Integer getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(Integer judgeScore) {
        this.judgeScore = judgeScore;
    }

    public Integer getShortAnswerScore() {
        return shortAnswerScore;
    }

    public void setShortAnswerScore(Integer shortAnswerScore) {
        this.shortAnswerScore = shortAnswerScore;
    }

    public Integer getRadioDifficultyScore() {
        return radioDifficultyScore;
    }

    public void setRadioDifficultyScore(Integer radioDifficultyScore) {
        this.radioDifficultyScore = radioDifficultyScore;
    }

    public Integer getChoiceDifficultyScore() {
        return choiceDifficultyScore;
    }

    public void setChoiceDifficultyScore(Integer choiceDifficultyScore) {
        this.choiceDifficultyScore = choiceDifficultyScore;
    }

    public Integer getJudgeDifficultyScore() {
        return judgeDifficultyScore;
    }

    public void setJudgeDifficultyScore(Integer judgeDifficultyScore) {
        this.judgeDifficultyScore = judgeDifficultyScore;
    }

    public Integer getAnswerDifficultyScore() {
        return answerDifficultyScore;
    }

    public void setAnswerDifficultyScore(Integer answerDifficultyScore) {
        this.answerDifficultyScore = answerDifficultyScore;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }
}