/**
 * 
 */
package edu.iiitd.muc.sensoract.apis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import play.libs.WS.HttpResponse;
import controllers.SendHTTP;
import edu.cmu.sphinx.demo.transcriber.Transcriber;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.MicrocontrollerActuate;
import edu.iiitd.muc.sensoract.format.MicrocontrollerData;
import edu.iiitd.muc.sensoract.format.MicrocontrollerStatus;
import edu.iiitd.muc.sensoract.format.SpeechRecognitionOutputMicrocontroller;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

/**
 * @author ishaan
 * 
 */
public class SoundInput extends SensorActAPI {

	public final void doProcess(String userID) {
		System.out.println("USER ID : " + userID);
		String res = null;
		try {
			InputStream inputStream = request.body;
			String fileURL = Const.BASE_WAVEFILE_URL + userID + ".wav";
			OutputStream out = new FileOutputStream(new File(fileURL));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();
			Transcriber obj = new Transcriber();
			String alpha = obj.callTranscriber(fileURL);
			System.out.println("from controller " + alpha);
			// renderText(alpha);
			String[] temp;
			temp = alpha.split(" ");

			int flag = 0;// to keep track of whether the keyword is detected or
							// not
			SpeechRecognitionOutputMicrocontroller sro = new SpeechRecognitionOutputMicrocontroller();
			sro.inputcommand = alpha;
			for (int i = 0; i < temp.length; i++) {
				// System.out.println(temp[i]);

				if (temp[i].contains("turn")) {
					System.out.println("1");
					String onOff = null;
					String deviceName = null;
					flag = 1;
					try {
						onOff = temp[2].toUpperCase();
						deviceName = temp[3];
					} catch (Exception e) {
						sro.outputtext = "Speech not recognised";
					}
					System.out.println(onOff);
					System.out.println(deviceName);

					try {
						res = (new SendHTTP().sendGetRequest(
								"http://192.168.9.108/actuate/faculty_room/"
										+ deviceName + "/1/" + onOff + "/",
								"actuate")).getString();
					} catch (Exception e) {
						sro.outputtext = "Request to microcontroller could not be made";
					}

					try {
						// sro.inputcommand = alpha;
						// sro.errormessage = null;
						// function Microcontroller(11111111111111)
						MicrocontrollerActuate mca = gson.fromJson(res,
								MicrocontrollerActuate.class);
						System.out.println(mca.state.state);
						String output = "The " + deviceName
								+ " has been turned " + onOff;
						sro.outputtext = output;
						// sro.state = mca.state.state;

					} catch (Exception e) {
						sro.outputtext = "JSON parse error";

						// renderJSON(gson.toJson(new APIResponse("  ", 2,
						// "JSON parse error")));

					}
					// renderJSON(gson.toJson(sro));
					// MicrocontrollerActuate mca = gson.fromJson(res,
					// MicrocontrollerActuate.class);

				}
				if (temp[i].contains("status")) {
					System.out.println("2");
					flag = 1;
					String deviceName = null;

					try {
						deviceName = temp[6];
					} catch (Exception e) {
						sro.outputtext = "Speech not recognised";
					}

					try {
						res = (new SendHTTP().sendGetRequest(

						"http://192.168.9.108/state/faculty_room/" + deviceName
								+ "/1", "status")).getString();
					} catch (Exception e) {
						sro.outputtext = "Request to microcontroller could not be made";
					}

					// SpeechRecognitionOutputMicrocontroller sro = new
					// SpeechRecognitionOutputMicrocontroller();

					try {
						// sro.inputcommand = alpha;
						// function Microcontroller(11111111111111)
						MicrocontrollerStatus mca = gson.fromJson(res,
								MicrocontrollerStatus.class);
						System.out.println(mca.state.state);
						String output = "The status of the " + deviceName
								+ " is " + mca.state.state;
						sro.outputtext = output;
					} catch (Exception e) {
						// renderJSON(gson.toJson(new APIResponse("  ", 2,
						// "JSON parse error")));
						sro.outputtext = "JSON parse error";

					}
					// renderJSON(gson.toJson(sro));

				}
				if (temp[i].contains("current")) {
					System.out.println("3");
					String sensorName = null;
					try {
						sensorName = temp[4];
						System.out.println("sensor :" + sensorName);
					} catch (Exception e) {
						sro.outputtext = "Speech not recognised";
					}

					String actualName = null;

					if (sensorName.contains("motion"))
						actualName = "pir";
					if (sensorName.contains("door"))
						actualName = "reed";
					if (sensorName.contains("temp"))
						actualName = "temperature";

					try {
						res = (new SendHTTP().sendGetRequest(
								"http://192.168.9.108/data/faculty_room/"
										+ actualName + "/1", "data"))
								.getString();
					} catch (Exception e) {
						sro.outputtext = "Request to microcontroller could not be made";
					}

					// SpeechRecognitionOutputMicrocontroller sro = new
					// SpeechRecognitionOutputMicrocontroller();

					try {
						// sro.inputcommand = alpha;

						// function Microcontroller(11111111111111)
						MicrocontrollerData mca = gson.fromJson(res,
								MicrocontrollerData.class);
						System.out.println(mca.data.reading);
						System.out.println("sensor :" + sensorName);
						String output = "The current reading of " + sensorName
								+ " sensor is " + mca.data.reading;
						output.replace("\t", " ");
						sro.outputtext = output;
					} catch (Exception e) {
						// renderJSON(gson.toJson(new APIResponse("  ", 2,
						// "JSON parse error")));
						sro.outputtext = "JSON parse error";

					}
					flag = 1;
				}

			}
			if (flag == 0) {
				System.out.println("Error : no keyword detected");
				sro.outputtext = "Error : no keyword detected";

			}

			String text = sro.outputtext;
			String textToSend = text.replace(" ", "%20");
			HttpResponse r = new SendHTTPRequest()
					.sendGetRequest("http://speech.jtalkplugin.com/api/?speech="
							+ textToSend);

			try {
				InputStream inputStream1 = r.getStream();

				System.out.println(inputStream1);

				// FileOutputStream saveFile = new
				// FileOutputStream("saveFile.wav");
				/*
				 * ByteArrayOutputStream out1 = new ByteArrayOutputStream();
				 * 
				 * byte[] buffer = new byte[1024]; // you can configure the
				 * buffer // size while (inputStream1.read(buffer) != -1)
				 * out1.write(buffer); // copy streams
				 * 
				 * byte[] result = out1.toByteArray();
				 */
				String fileURL1 = Const.BASE_OUTPUTWAVEFILE_URL + userID
						+ ".wav";

				OutputStream out1 = new FileOutputStream(new File(fileURL1));
				int read1 = 0;
				byte[] bytes1 = new byte[1024];

				while ((read1 = inputStream1.read(bytes1)) != -1) {

					out1.write(bytes1, 0, read1);
				}
				// out1.save(); }

				/*
				 * Audio audio = new Audio(); audio.sound = result; //
				 * Application.uploadAudio(audio); audio.save();
				 * 
				 * // renderBinary();
				 */
				inputStream1.close();
				out1.flush();
				out1.close();
			} catch (Exception e) {

			}
			sro.fileID = userID;
			renderArgs.put("mp3ID", sro.fileID);
			renderJSON(gson.toJson(sro));

			System.out.println("New file created!");

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
