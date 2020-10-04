package kero.javautil.commons.util.fd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import kero.javautil.commons.exception.AppFileIOException;
import kero.javautil.commons.exception.AppFileIOException.KIND;
import kero.javautil.commons.exception.AppFileIOException.TARGET;

public class DirUtil {

	// 指定したフォルダを削除する

	// 指定したパスに該当するフォルダを複数削除する
	public static void cleanDirectory(String directory) throws AppFileIOException {
		try {
			FileUtils.cleanDirectory(new File(directory));
		} catch (IOException e) {
			throw new AppFileIOException(TARGET.DIR, KIND.DELETE_ERROR, Paths.get(directory), e);
		}
	}

}