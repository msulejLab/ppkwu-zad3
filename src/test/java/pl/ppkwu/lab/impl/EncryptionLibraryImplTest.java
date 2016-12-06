package pl.ppkwu.lab.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.ppkwu.lab.api.ChecksumAlgorithm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Shogun on 29.11.2016.
 */
public class EncryptionLibraryImplTest {


    @Test
    public void encryptFile() throws Exception {

    EncryptionLibraryImpl impl = new EncryptionLibraryImpl();
        File inputFile = new File("src/test/resources/inputFile.txt");
        File outputFie = new File("output/outputFile.txt");
       impl.encryptFile(inputFile , outputFie);

    }

    @Test
    public void decryptFile() throws Exception {
        EncryptionLibraryImpl impl = new EncryptionLibraryImpl();
        File inputFile = new File("src/test/resources/encryptedFile.txt");
        File outputFile = new File("output/decryptedFile.txt");
        impl.decryptFile(inputFile, outputFile);
    }

    @Test
    public void fileChecksum() throws Exception {
        EncryptionLibraryImpl impl = new EncryptionLibraryImpl();
        File inputFile = new File("src/test/resources/inputFile.txt");
        assertEquals("noot correct checksum", "C331AA37FF9B71448C81AD85D1FD6447" , impl.fileChecksum(inputFile,ChecksumAlgorithm.MD5));
        assertEquals("noot correct checksum", "D505DF8F193139DF14D225EAC023527DA204228F" ,  impl.fileChecksum(inputFile,ChecksumAlgorithm.SHA1));

    }


    boolean sameContent(Path file1, Path file2) throws IOException {
        final long size = Files.size(file1);
        if (size != Files.size(file2))
            return false;

        if (size < 4096)
            return Arrays.equals(Files.readAllBytes(file1), Files.readAllBytes(file2));

        try (InputStream is1 = Files.newInputStream(file1);
             InputStream is2 = Files.newInputStream(file2)) {

            int data;
            while ((data = is1.read()) != -1)
                if (data != is2.read())
                    return false;
        }

        return true;
    }

    @AfterClass
    public static void clean() {
        File directory = new File("output");

        for(File file: directory.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }

}