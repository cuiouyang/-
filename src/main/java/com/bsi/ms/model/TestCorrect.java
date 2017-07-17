package com.bsi.ms.model;

public class TestCorrect {
	
	private String problemTitle;

    private String solution;
    
    private Integer difficulty;
    
    private String userSolution;
    
    private Integer problemId;
    
    private String courseName;
    
    private String userId;
    

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}

	public String getProblemTitle() {
		return problemTitle;
	}

	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public String getUserSolution() {
		return userSolution;
	}

	public void setUserSolution(String userSolution) {
		this.userSolution = userSolution;
	}

	@Override
	public String toString() {
		return "TestCorrect [problemTitle=" + problemTitle + ", solution=" + solution + ", difficulty=" + difficulty
				+ ", userSolution=" + userSolution + "]";
	}
    
}
