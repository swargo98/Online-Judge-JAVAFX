/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author MAHDI
 */
public class Folder implements Serializable {

    private File dir;
    private String FolderName;
    private ArrayList< Folder> childs;
    private ArrayList< Pair< String, byte[]>> files;
    private static final String FileSeparator = System.getProperty("file.separator");

    public Folder(File dir) {

        this.dir = dir;
        this.FolderName = dir.getName();
        this.childs = new ArrayList<Folder>();
        this.files = new ArrayList<Pair< String, byte[]>>();

        File f = dir;
        //System.out.println(f.getAbsolutePath());
        if (f.list() != null) {
            for (String s : f.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return new File(dir, name).isDirectory();
                }
            })) {
                if (s == null) {
                    continue;
                }
                File NextDir = new File(dir.getAbsolutePath() + FileSeparator + s);
                this.childs.add(new Folder(NextDir));
                //System.out.println(s);
            }

            for (String s : f.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return new File(dir, name).isFile();
                }
            })) {
                try {
                    if (s == null) {
                        continue;
                    }
                    File fl = new File(dir.getAbsolutePath() + FileSeparator + s);
                    this.files.add(new Pair<String, byte[]>(s, Files.readAllBytes(fl.toPath())));
                    //System.out.println(s);
                } catch (IOException ex) {
                    Logger.getLogger(Folder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public void write(File Dir) {
        Dir.mkdirs();
        if (!Dir.isDirectory()) {
            System.out.println("must be folder ");
            return;
        }
        this.dir = new File(Dir.getAbsolutePath() + FileSeparator + this.FolderName);
        if (!this.dir.exists()) {
            this.dir.mkdirs();
        }
        //System.out.println(Dir.getAbsolutePath());
        for (Pair< String, byte[]> sb : files) {
            try {
                File fl = new File(dir.getAbsolutePath() + FileSeparator + sb.getKey());

                Files.write(fl.toPath(), sb.getValue());

            } catch (IOException ex) {
                Logger.getLogger(Folder.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        for (Folder fl : this.childs) {

            fl.write(this.dir);

        }
    }

    public File getDir() {
        return dir;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String FolderName) {
        this.FolderName = FolderName;
    }

    public ArrayList<Folder> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Folder> childs) {
        this.childs = childs;
    }

    public ArrayList<Pair<String, byte[]>> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<Pair<String, byte[]>> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "Folder{" + "dir=" + dir + ", FolderName=" + FolderName + ", childs=" + childs + ", files=" + files + '}';
    }

}
