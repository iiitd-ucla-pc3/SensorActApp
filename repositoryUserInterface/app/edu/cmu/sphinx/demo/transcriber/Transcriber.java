/*
 * Copyright 1999-2004 Carnegie Mellon University.
 * Portions Copyright 2004 Sun Microsystems, Inc.
 * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 *
 */

package edu.cmu.sphinx.demo.transcriber;

import java.net.URL;

import edu.cmu.sphinx.frontend.util.AudioFileDataSource;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

/**
 * A simple example that shows how to transcribe a continuous audio file that
 * has multiple utterances in it.
 */
public class Transcriber {

	public String callTranscriber(String filename) {
		// public static void main(String[] args) throws IOException,
		// UnsupportedAudioFileException {
		URL audioURL;
		System.out.println("filename : " + filename.toString());
		String[] temp_filename = null;
		temp_filename = filename.split("/");

		audioURL = Transcriber.class
				.getResource(temp_filename[temp_filename.length - 1]);

		System.out.println("audioURL : " + audioURL.toString());

		System.out.println("error in config file");
		URL configURL = Transcriber.class.getResource("config.xml");
		System.out.println("error cleared");

		System.out.println("configURL : " + configURL.toString());

		ConfigurationManager cm = new ConfigurationManager(configURL);

		System.out.println("error 1");
		System.out.println("cm : " + cm.lookup("recognizer").toString());
		Recognizer recognizer = (Recognizer) cm.lookup("recognizer");

		System.out.println("error 2");
		/* allocate the resource necessary for the recognizer */
		System.out.println("Recognizer contents" + recognizer.toString());

		recognizer.allocate();

		// configure the audio input for the recognizer
		AudioFileDataSource dataSource = (AudioFileDataSource) cm
				.lookup("audioFileDataSource");
		dataSource.setAudioFile(audioURL, null);
		System.out.println("Result Text is");

		// Loop until last utterance in the audio file has been decoded, in
		// which case the recognizer will return null.
		Result result;
		String resultText = "Error!!";
		while ((result = recognizer.recognize()) != null) {

			resultText = result.getBestResultNoFiller();
			System.out.println("Result Text is : " + resultText);
		}
		return resultText;
	}

}
