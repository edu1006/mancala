package com.mancala.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.util.Arrays;

public class PlayerControllerTest{

	public static void main(String[] args) {
		
		
		int [] teste = { 1, -1, 1 -2,   4, 5}; 
		
		List<Object> list = Arrays.asList(teste); 

		
		int count =0;
		//big notation O(N*+n) 
		for (Object object : list) {
			if (checkNumber(list,object)) {
				if (!set.contains((Integer) object)) {
					set.add((Integer)object); 
					count+= 1; 
				}
			}	
		}
		System.out.println(count/2);
	}

	private static boolean checkNumber(List<Object> list, Object object) {
		if (list.contains((Integer)object* -1)) {
			return true; 
		}
		return false;
	}
	
}
