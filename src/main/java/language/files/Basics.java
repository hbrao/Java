package language.files;

import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.nio.channels.*;

public class Basics {
    public static void main(String[] args) throws IOException {
        //Access file system
        FileSystem fs = FileSystems.getDefault();
        System.out.println(fs.getSeparator());
        System.out.println(fs.getRootDirectories());

        //Create base path.
        //fs.getPath API supports varargs to avoid dealing with file separator
        Path base = fs.getPath("src", "test", "resources");
        System.out.printf("Working at base path %s \n", base.toAbsolutePath());

        //Extend base path
        Path src = base.resolve("data");
        Path dest = base.resolve("results");

        //Copy source folder contents into destination.
        System.out.printf("Copy contents from %s to %s \n", src, dest);
        Files.walk(src, FileVisitOption.FOLLOW_LINKS)
             .forEach( srcPath -> {
                 try {
                     Path relativePath = src.relativize(srcPath);
                     Path destPath = dest.resolve(relativePath);
                     if ( ! Files.isDirectory(destPath) || ! Files.exists(destPath) ) {
                         Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                     }
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             });
        System.out.println();

        Path csvFile = dest.resolve("csv").resolve("sample.csv");

        //Small files
        List<String> allLines = Files.readAllLines(csvFile, StandardCharsets.UTF_8);
        allLines.forEach( line -> System.out.println(line));

        Files.writeString(csvFile, "\nJohn,Software,Mark,100000", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        System.out.println();

        //Large files
        try ( BufferedReader csvFileRdr = Files.newBufferedReader(csvFile, StandardCharsets.UTF_8) ) {
            String line;
            while ( ( line = csvFileRdr.readLine() ) != null ) {
                System.out.println(line);
            }
        }

        try ( BufferedWriter csvFileWriter = Files.newBufferedWriter(csvFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND) ) {
            allLines.stream()
                    .skip(1)
                    .forEach( line -> {
                        try {
                            csvFileWriter.newLine();
                            csvFileWriter.write( line );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
        System.out.println();

        //Huge files.
        final int CHUNK_SIZE = 524_2880; //5 Mb
        try( FileChannel csvFileChannel = FileChannel.open(csvFile, EnumSet.of(StandardOpenOption.READ)) ) {
            long length = csvFileChannel.size();
            int position = 0;
            while ( position < length ) {
                long remaining = length - position;
                int bytesToMap = (int) Math.min(CHUNK_SIZE, remaining);
                MappedByteBuffer inMemChunk = csvFileChannel.map(FileChannel.MapMode.READ_ONLY, position, bytesToMap);
                System.out.println(StandardCharsets.UTF_8.decode(inMemChunk));
                position += bytesToMap ;
            }
        }

    }
}
