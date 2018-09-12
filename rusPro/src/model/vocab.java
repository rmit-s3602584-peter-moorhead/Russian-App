package model;

import java.io.Serializable;
import java.util.*;

public class vocab implements Serializable{
		private String vID;
		private String rusWo;
		private String engWo;
		ArrayList<vocab> vocab;
		
		public vocab(){};
		
		public vocab(String id, String r, String e)
		{
			vID = id;
			rusWo = r;
			engWo = e;
			vocab = new ArrayList<vocab>();
		}
	
	public void setID(String id){
		this.vID = id;
	}
	public void setRusWo(String r){
		this.rusWo = r;
	}
	public void setEngWo(String e){
		this.engWo = e;
	}
	public String getvID(){
		return vID;
	}
	public String getRusWo(){
		return rusWo;
	}
	public String getEngWo(){
		return engWo;
	}
	
	public String toString(){
		return "ID: " + vID + ", Russian Phrase: " + rusWo + ", English Phrase: " + engWo;
	}
		
}