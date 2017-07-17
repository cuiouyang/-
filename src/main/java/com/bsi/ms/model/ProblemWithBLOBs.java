package com.bsi.ms.model;

import java.io.Serializable;

public class ProblemWithBLOBs extends Problem implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String problemTitle;

    private String solution;

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle == null ? null : problemTitle.trim();
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution == null ? null : solution.trim();
    }
}