package com.wk.system.examination.service.impl.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wk.system.examination.entity.dao.*;
import com.wk.system.examination.entity.po.*;
import com.wk.system.examination.service.bs.domain.AnswerInfoServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnswerInfoServiceImpl implements AnswerInfoServiceBs {
	private final AnswerInfoMapper answerInfoMapper;
	private final ChoiceQuestionMapper choiceQuestionMapper;
	private final ObjectiveQuestionMapper objectiveQuestionMapper;
	private final ExamInfoMapper examInfoMapper;
	private final ScoreMapper scoreMapper;

	@Autowired
	public AnswerInfoServiceImpl(AnswerInfoMapper answerInfoMapper, ChoiceQuestionMapper choiceQuestionMapper, ObjectiveQuestionMapper objectiveQuestionMapper, ExamInfoMapper examInfoMapper, ScoreMapper scoreMapper) {
		this.answerInfoMapper = answerInfoMapper;
		this.choiceQuestionMapper = choiceQuestionMapper;
		this.objectiveQuestionMapper = objectiveQuestionMapper;
		this.examInfoMapper = examInfoMapper;
		this.scoreMapper = scoreMapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void submitAnswer(Map<String, Object> answer) {
		int examId = Integer.parseInt(answer.get("exam_id").toString());
		int userId = Integer.parseInt(answer.get("user_id").toString());
		List res = answerInfoMapper.selectByExamIdAndUserId(examId, userId);
		Gson gson = new Gson();
		String ans = gson.toJson(answer);
		if(res.size()>0){
			answerInfoMapper.update(ans, null, userId, examId);
		}else {
			AnswerInfo answerInfo = new AnswerInfo();
			answerInfo.setAnswer(ans);
			answerInfo.setExam_id((long) examId);
			answerInfo.setUser_id((long) userId);
			answerInfoMapper.insert(answerInfo);
		}
	}

	@Override
	public List<Map<String, Object>> getAnswer(int examId, int userId) {
		return answerInfoMapper.selectByExamIdAndUserId(examId, userId);
	}

	@Override
	public List<Map<String, Object>> getStudentInfosByExamId(int examId) {
		return answerInfoMapper.selectStudentInfosByExamId(examId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void setScore(int examId, int studentId, int scoreOfObjective) {
		List<Map<String, Object>> answerList = getAnswer(examId, studentId);
		if(answerList.size() == 0){
			return;
		}
		Gson gson = new Gson();
		String answerString = answerList.get(0).get("answer").toString();
		@SuppressWarnings("unchecked")
		Map<String, Object> answerMap = gson.fromJson(answerString, Map.class);
		String answerOfChoice = answerMap.get("choiceAnswers").toString();
		ArrayList<AnswerElement> choiceAnswerList = gson.fromJson(answerOfChoice, new TypeToken<ArrayList<AnswerElement>>(){}.getType());
		int scoreOfChoice = 0;
		for (AnswerElement choice : choiceAnswerList){
			ChoiceQuestion choiceQuestion = choiceQuestionMapper.selectById(choice.getId());
			if(choiceQuestion.getAnswer() == choice.getAnswer().charAt(0)-'A' + 1L){
				scoreOfChoice += choiceQuestion.getScore();
			}
		}
		int maxScore = examInfoMapper.countChoiceScore(examId) + examInfoMapper.countObjectiveScore(examId);
		Score score = new Score();
		score.setScore_max((long) maxScore);
		score.setExam_id((long) examId);
		score.setUser_id((long) studentId);
		score.setScore_choice((long) scoreOfChoice);
		score.setScore_objective((long) scoreOfObjective);
		List historyScore = scoreMapper.selectByUserIdAndExamId(examId, studentId);
		if(historyScore.size() > 0){
			scoreMapper.update(score);
		}else {
			scoreMapper.insert(score);
		}
	}

	@Override
	public List<Map<String, Object>> getScoresByStudentId(int studentId) {
		return answerInfoMapper.selectByStudentId(studentId);
	}
}
