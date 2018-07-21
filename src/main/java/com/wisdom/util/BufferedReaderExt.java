package com.wisdom.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class BufferedReaderExt extends BufferedReader {
	private String from;
	private String to;

	public BufferedReaderExt(Reader in) {
		super(in);
		
		from = "";
		to = "";
	}

	@Override
	public String readLine() throws IOException {
		String ret = super.readLine();
		if (null != ret){
			ret = ret.replaceAll(from, to);
		}
		return ret;
	}
	
	public String getFrom(){
		return from;
	}
	
	public void setFrom(String from){
		this.from = from;
	}
	
	public String getTo(){
		return to;
	}
	
	public void setTo(String to){
		this.to = to;
	}
}
