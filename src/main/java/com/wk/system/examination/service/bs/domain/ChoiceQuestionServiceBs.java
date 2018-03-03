package com.wk.system.examination.service.bs.domain;

public interface ChoiceQuestionServiceBs {
	void addNewChoiceQuestion(String description, String optionA, String optionB, String optionC, String optionD, String answer, int score, int exam_id);
}
