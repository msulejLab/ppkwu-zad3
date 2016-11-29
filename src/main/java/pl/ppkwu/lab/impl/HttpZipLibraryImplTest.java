package pl.ppkwu.lab.impl;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.*;

/**
 * Created by Shogun on 28.11.2016.
 */
public class HttpZipLibraryImplTest {
    HttpZipLibraryImpl httpZipLibrary;
    @Before
    public void setUp() throws Exception {

        httpZipLibrary = new HttpZipLibraryImpl();
    }

    @Test
    public void downloadFile() throws Exception {
        File file = new File("downloaded.txt");
        file.createNewFile();
        URL url = new URL("http://google.com");
        long i = file.length();
        httpZipLibrary.downloadFile(url,file);
        assertEquals("plik nie sciagniety",sizeofFileBiggerThanZero(file), true);
    }

    @Test
    public void zipFile() throws Exception {
        File normalFIle = new File("normalfile.txt");
        File zipFile = new File("zip.zip");
        httpZipLibrary.zipFile(normalFIle,zipFile);

        assertEquals("nie stworzono pliku",true,zipFile.exists());

    }


    @Test
    public void zipDirectory() throws Exception {
        httpZipLibrary.zipDirectory();
    }

    @Test
    public void unzipFile() throws Exception {

    }

    private boolean sizeofFileBiggerThanZero(File file){
        if(file.length()>0) return true;
        else return false;
    }
}