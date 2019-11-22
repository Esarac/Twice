package model;

import java.io.IOException;

/**
* <b>Description:</b> The interface FileLoader in the package model.<br>
* @author VoodLyc & Esarac.
*/

public interface FileLoader<T> {

	String read(String path) throws IOException;
	T load(String path);
	
}