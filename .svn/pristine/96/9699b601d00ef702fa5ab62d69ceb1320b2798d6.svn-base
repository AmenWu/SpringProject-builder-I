/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.risenb.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * 
 * @author runzhi
 */
public class StreamUtil {
	private static final int DEFAULT_BUFFER_SIZE = 8192;

	/**
	 * 将InputStream转换成String
	 * 
	 * @param in
	 *            InputStream
	 * @return String
	 * @throws Exception
	 * 
	 */
	public static String InputStreamTOString(InputStream in) {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[DEFAULT_BUFFER_SIZE];
		String string = null;
		int count = 0;
		try {
			while ((count = in.read(data, 0, DEFAULT_BUFFER_SIZE)) != -1)
				outStream.write(data, 0, count);
		} catch (IOException e) {
			e.printStackTrace();
		}

		data = null;
		try {
			string = new String(outStream.toByteArray(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	/**
	 * 将InputStream转换成某种字符编码的String
	 * 
	 * @param in
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String InputStreamTOString(InputStream in, String encoding) {
		String string = null;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[DEFAULT_BUFFER_SIZE];
		int count = -1;
		try {
			while ((count = in.read(data, 0, DEFAULT_BUFFER_SIZE)) != -1)
				outStream.write(data, 0, count);
		} catch (IOException e) {
			e.printStackTrace();
		}

		data = null;
		try {
			string = new String(outStream.toByteArray(), encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	/**
	 * 将String转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream StringTOInputStream(String in) throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes("UTF-8"));
		return is;
	}

	/**
	 * 将String转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static byte[] StringTObyte(String in) {
		byte[] bytes = null;
		try {
			bytes = InputStreamTOByte(StringTOInputStream(in));
		} catch (IOException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 将InputStream转换成byte数组
	 * 
	 * @param in
	 *            InputStream
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] InputStreamTOByte(InputStream in) throws IOException {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[DEFAULT_BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, DEFAULT_BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return outStream.toByteArray();
	}

	/**
	 * 将byte数组转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream byteTOInputStream(byte[] in) throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(in);
		return is;
	}

	/**
	 * 将byte数组转换成String
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static String byteToString(byte[] in) {

		String result = null;
		InputStream is = null;
		try {
			is = byteTOInputStream(in);
			result = InputStreamTOString(is, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将byte数组转换成String
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static String getString(String in) {

		String is = null;
		try {
			is = byteToString(StringTObyte(in));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	// InputStream 转换成byte[]
	public byte[] getBytes(InputStream is) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[DEFAULT_BUFFER_SIZE];
		int len = 0;

		while ((len = is.read(b, 0, DEFAULT_BUFFER_SIZE)) != -1) {
			baos.write(b, 0, len);
		}

		baos.flush();

		byte[] bytes = baos.toByteArray();

		System.out.println(new String(bytes));

		return bytes;
	}

	/**
	 * 根据文件路径创建文件输入流处理 以字节为单位（非 unicode ）
	 * 
	 * @param path
	 * @return
	 */
	public static FileInputStream getFileInputStream(String filepath) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileInputStream;
	}

	/**
	 * 根据文件对象创建文件输入流处理 以字节为单位（非 unicode ）
	 * 
	 * @param path
	 * @return
	 */
	public static FileInputStream getFileInputStream(File file) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileInputStream;
	}

	/**
	 * 根据文件对象创建文件输出流处理 以字节为单位（非 unicode ）
	 * 
	 * @param file
	 * @param append
	 *            true:文件以追加方式打开,false:则覆盖原文件的内容
	 * @return
	 */
	public static FileOutputStream getFileOutputStream(File file, boolean append) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file, append);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileOutputStream;
	}

	/**
	 * 根据文件路径创建文件输出流处理 以字节为单位（非 unicode ）
	 * 
	 * @param path
	 * @param append
	 *            true:文件以追加方式打开,false:则覆盖原文件的内容
	 * @return
	 */
	public static FileOutputStream getFileOutputStream(String filepath, boolean append) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(filepath, append);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileOutputStream;
	}

	public static File getFile(String filepath) {
		return new File(filepath);
	}

	public static ByteArrayOutputStream getByteArrayOutputStream() {
		return new ByteArrayOutputStream();
	}

	public static void io(InputStream in, OutputStream out) throws IOException {
		io(in, out, -1);
	}

	public static void io(InputStream in, OutputStream out, int bufferSize) throws IOException {
		if (bufferSize == -1) {
			bufferSize = DEFAULT_BUFFER_SIZE;
		}

		byte[] buffer = new byte[bufferSize];
		int amount;

		while ((amount = in.read(buffer)) >= 0) {
			out.write(buffer, 0, amount);
		}
	}

	public static void io(Reader in, Writer out) throws IOException {
		io(in, out, -1);
	}

	public static void io(Reader in, Writer out, int bufferSize) throws IOException {
		if (bufferSize == -1) {
			bufferSize = DEFAULT_BUFFER_SIZE >> 1;
		}

		char[] buffer = new char[bufferSize];
		int amount;

		while ((amount = in.read(buffer)) >= 0) {
			out.write(buffer, 0, amount);
		}
	}

	public static OutputStream synchronizedOutputStream(OutputStream out) {
		return new SynchronizedOutputStream(out);
	}

	public static OutputStream synchronizedOutputStream(OutputStream out, Object lock) {
		return new SynchronizedOutputStream(out, lock);
	}

	public static String readText(InputStream in) throws IOException {
		return readText(in, null, -1);
	}

	public static String readText(InputStream in, String encoding) throws IOException {
		return readText(in, encoding, -1);
	}

	public static String readText(InputStream in, String encoding, int bufferSize) throws IOException {
		Reader reader = (encoding == null) ? new InputStreamReader(in) : new InputStreamReader(in, encoding);

		return readText(reader, bufferSize);
	}

	public static String readText(Reader reader) throws IOException {
		return readText(reader, -1);
	}

	public static String readText(Reader reader, int bufferSize) throws IOException {
		StringWriter writer = new StringWriter();

		io(reader, writer, bufferSize);
		return writer.toString();
	}

	private static class SynchronizedOutputStream extends OutputStream {
		private OutputStream out;
		private Object lock;

		SynchronizedOutputStream(OutputStream out) {
			this(out, out);
		}

		SynchronizedOutputStream(OutputStream out, Object lock) {
			this.out = out;
			this.lock = lock;
		}

		public void write(int datum) throws IOException {
			synchronized (lock) {
				out.write(datum);
			}
		}

		public void write(byte[] data) throws IOException {
			synchronized (lock) {
				out.write(data);
			}
		}

		public void write(byte[] data, int offset, int length) throws IOException {
			synchronized (lock) {
				out.write(data, offset, length);
			}
		}

		public void flush() throws IOException {
			synchronized (lock) {
				out.flush();
			}
		}

		public void close() throws IOException {
			synchronized (lock) {
				out.close();
			}
		}
	}
}
