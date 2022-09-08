package com.luxoft.vowels.average;

import java.io.IOException;

public class VowelsAverageApplication {

	private static FileReaderWriter fileReaderWriter;

	public VowelsAverageApplication(FileReaderWriter fileReaderWriter) {
		this.fileReaderWriter = fileReaderWriter;
	}

	public static void main(String[] args) throws IOException {
		new VowelsAverageApplication(fileReaderWriter).calculate();
	}

	public void calculate() throws IOException {
		fileReaderWriter.report(new VowelsCounter().countVowels(new FileReaderWriter().getInputFile()));
	}


}
