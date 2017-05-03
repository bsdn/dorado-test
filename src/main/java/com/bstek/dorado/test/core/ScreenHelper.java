package com.bstek.dorado.test.core;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

public class ScreenHelper {
	public static void main(String[] args) {
		ScreenHelper.recordStart();
		ScreenHelper.recordStop();
	}
	private static ScreenRecorder screenRecorder;
	private static void init(){
		try {
			GraphicsConfiguration gconfig = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.getDefaultConfiguration();
			screenRecorder = new ScreenRecorder(gconfig, new Format(MediaTypeKey,
					MediaType.FILE, MimeTypeKey, MIME_AVI), new Format(
							MediaTypeKey, MediaType.VIDEO, EncodingKey,
							ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey,
							ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, (int) 24,
							FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f,
							KeyFrameIntervalKey, (int) (15 * 60)), new Format(MediaTypeKey,
									MediaType.VIDEO, EncodingKey, "black", FrameRateKey,
									Rational.valueOf(30)), null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Start Capturing the Video
	 */
	public static void recordStart() {
		init();
		try {
			screenRecorder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Stop the ScreenRecorder
	 */
	public static void recordStop() {
	    try {
	    	File videoFile = screenRecorder.getCreatedMovieFiles().get(0);
			System.out.println(videoFile.getAbsolutePath());
			screenRecorder.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
