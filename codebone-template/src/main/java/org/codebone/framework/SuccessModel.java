package org.codebone.framework;

public class SuccessModel extends BaseModel {
	public SuccessModel(){
		this.code = "200";
		this.message = "성공";
	}
	public SuccessModel(Object obj){
		this.code = "200";
		this.message = "성공";
		this.data = obj;
	}
	public SuccessModel(Object obj, boolean hasNext){
		this.code = "200";
		this.message = "성공";
		this.data = obj;
		this.hasNext = hasNext;
	}
	public SuccessModel(Object obj, boolean hasNext, int allCount){
		this.code = "200";
		this.message = "성공";
		this.data = obj;
		this.hasNext = hasNext;
		this.allCount = allCount;
	}
}
