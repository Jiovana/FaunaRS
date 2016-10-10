package util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

import java.io.ByteArrayOutputStream;

public class ImagemUtil {
	
	
	/**
	 * Converte um Bitmap em um array de bytes (bytes[])
	 * @param bitmap
	 * @return byte[]
	 */
	public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    bitmap.compress(CompressFormat.PNG, 0, outputStream);
	    return outputStream.toByteArray();
	}
	
	  
	}
