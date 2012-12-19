package org.codebone.framework;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class FailModel extends BaseModel {
	public FailModel(){
		this.code = "500";
		this.message = "실패";
	}
	public FailModel(Object obj){
		this.code = "500";
		this.message = "실패";
		this.data = obj;
	}
	public FailModel(Object obj, boolean hasNext){
		this.code = "500";
		this.message = "실패";
		this.data = obj;
		this.hasNext = hasNext;
	}
	public FailModel(Object obj, boolean hasNext, int allCount){
		this.code = "500";
		this.message = "실패";
		this.data = obj;
		this.hasNext = hasNext;
		this.allCount = allCount;
	}
}
