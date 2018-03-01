package com.wk.system.examination.entity.codes;

public enum LevelCode {
	LEVEL_INVALID(0),
	LEVEL_TEACHER(2),
	LEVEL_STUDENT(1);

	private int value;

	private LevelCode(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getValueString(){
		return value + "";
	}
}
