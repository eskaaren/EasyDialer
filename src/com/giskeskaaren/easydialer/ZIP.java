package com.giskeskaaren.easydialer;

/**
 * Creator: Eivin Giske Skaaren
 * Email: eskaaren@yahoo.no
 * Date: 6/15/12
 * Time: 10:47 PM
 */

import android.util.Log;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZIP
{
    public static boolean compress(String file, String zipFile)
    {
        String[] tmp = {file};
        return compress(tmp, zipFile);
    }

    public static boolean compress(String[] files, String zipFile)
    {
        boolean ok = true;

        Log.d("ZIP", "Start compressing to " + zipFile);
        try
        {
            File zipFileParent = new File(zipFile).getParentFile();
            if (zipFileParent != null)
                zipFileParent.mkdirs();

            ZipOutputStream out = new ZipOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(zipFile)));

            for (int i = 0; i < files.length; i++)
            {
                if (!compress(new File(files[i]), out)) {
                    ok = false;
                }
            }
            out.close();
        }
        catch (IOException e)
        {
            Log.e("ZIP", "Error compressing to " + zipFile + "\n" + e.toString());
            return false;
        }

        Log.d("ZIP", "Done compressing");
        return ok;
    }

    private static boolean compress(File file, ZipOutputStream out)
    {
        if (file.isDirectory())
        {
            File[] files = file.listFiles();

            for (int i = 0; i < files.length; i++)
            {
                compress(files[i], out);
            }
        }
        else
        {
            byte[] buffer = new byte[4096];
            int bytesRead;

            Log.d("ZIP", "Adding: " + file.getPath() + "... ");
            try
            {
                FileInputStream in = new FileInputStream(file);

                ZipEntry entry = new ZipEntry(file.getPath());
                out.putNextEntry(entry);

                while ((bytesRead = in.read(buffer)) != -1)
                {
                    out.write(buffer, 0, bytesRead);
                }

                out.closeEntry();
                in.close();
            }
            catch (IOException e)
            {
                Log.e("ZIP", "Error\n" + e.toString());
                return false;
            }
        }
        return true;
    }


    public static boolean decompress(String source, String destination)
    {
        Log.d("ZIP", "Start decompressing " + source + " to " + destination);
        try
        {
            ZipFile zipFile = new ZipFile(source);

            Enumeration<?extends ZipEntry> e = zipFile.entries();

            while (e.hasMoreElements())
            {
                ZipEntry entry = e.nextElement();

                File destFile =	new File(destination, entry.getName());
                Log.d("ZIP", "Extracting: " + destFile + "... ");

                File destinationParent = destFile.getParentFile();
                if (destinationParent != null)
                    destinationParent.mkdirs();

                if (!entry.isDirectory())
                {
                    BufferedInputStream in = new BufferedInputStream(zipFile.getInputStream(entry));

                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));

                    byte[] buffer = new byte[4096];
                    int bytesWrite;

                    while ((bytesWrite = in.read(buffer)) != -1)
                    {
                        out.write(buffer, 0, bytesWrite);
                    }

                    out.flush();
                    out.close();
                    in.close();
                }
            }

            zipFile.close();
        }
        catch (IOException ioe)
        {
            Log.e("ZIP", "Error decompressing " + source + "\n" + ioe.toString());
            return false;
        }
        Log.d("ZIP", "Done decompressing.");
        return true;
    }
}