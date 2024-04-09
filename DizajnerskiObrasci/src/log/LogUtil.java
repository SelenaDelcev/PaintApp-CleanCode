package log;

import java.util.Scanner;

import geometry.Shape;

public interface LogUtil {
	public Shape makeShapeFromLog(String line, String stringShape, Boolean second);
	void executeLineLog(String line);
	void loadFileByLoadingType(int type, Scanner file);
}
