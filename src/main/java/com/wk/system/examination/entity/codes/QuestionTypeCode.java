package com.wk.system.examination.entity.codes;

public enum  QuestionTypeCode {
	TYPE_CHOICE(1),
	TYPE_OBJECTIVE(2);

	private int value;

	QuestionTypeCode(int val) {
		this.value = val;
	}

	public int getValue() {
		return value;
	}

	public String getValueString(){
		return value + "";
	}
}
