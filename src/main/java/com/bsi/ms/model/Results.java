package com.bsi.ms.model;

public class Results extends ResultsKey {
    private Float score;

    private Float radioGrade;

    private Float selectGrade;

    private Float judgeGrade;

    private Float shortAnswerGrade;

    private Integer whetherGrade;

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Float getRadioGrade() {
        return radioGrade;
    }

    public void setRadioGrade(Float radioGrade) {
        this.radioGrade = radioGrade;
    }

    public Float getSelectGrade() {
        return selectGrade;
    }

    public void setSelectGrade(Float selectGrade) {
        this.selectGrade = selectGrade;
    }

    public Float getJudgeGrade() {
        return judgeGrade;
    }

    public void setJudgeGrade(Float judgeGrade) {
        this.judgeGrade = judgeGrade;
    }

    public Float getShortAnswerGrade() {
        return shortAnswerGrade;
    }

    public void setShortAnswerGrade(Float shortAnswerGrade) {
        this.shortAnswerGrade = shortAnswerGrade;
    }

    public Integer getWhetherGrade() {
        return whetherGrade;
    }

    public void setWhetherGrade(Integer whetherGrade) {
        this.whetherGrade = whetherGrade;
    }
}