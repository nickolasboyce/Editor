//package com.nickolasboyce.io;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

public class ReadinDirectory {

	public String thepath;

	public ArrayList<String> readToArrayList(Path path) throws IOException {
    	ArrayList<String> result= new ArrayList<String>();

		try (
			DirectoryStream<Path> dirstream = Files.newDirectoryStream(path, "*")) {
	        	for (Path entry: dirstream) {
	        		result.add(entry.toString());
	        	}
    	} catch (DirectoryIteratorException e) {
    		throw e.getCause();
    	}
    	return result;
	}

	public static void showEntries() throws IOException {
		ReadinDirectory rid= new ReadinDirectory();
		  Path p= FileSystems.getDefault().getPath("/home/nickolasboyce");
		  ArrayList<String> dirlist= rid.readToArrayList(p);
		  	Collections.sort(dirlist);

		for (String entry: dirlist) {
			System.out.println(entry);
		}
	}

	public static void main(String[] args) throws IOException {
		ReadinDirectory rid= new ReadinDirectory();
		  rid.showEntries();
	}

}
