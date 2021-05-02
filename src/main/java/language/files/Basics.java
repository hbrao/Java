package language.files;

import java.io.*;
import java.nio.file.*;

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

    }
}
