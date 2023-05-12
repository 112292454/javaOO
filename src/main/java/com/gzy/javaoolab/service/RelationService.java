package com.gzy.javaoolab.service;


public interface RelationService {

	boolean addRequest(String from, String to);

	boolean accessRequest(String from,String to);

	boolean denyRequest(String from,String to);


}
