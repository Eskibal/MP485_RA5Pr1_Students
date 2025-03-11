/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author jeanp
 */
public class ManageFile {

    private String projectRoute;
    private String separator;
    private String dirRoute;
    private File dir;
    private File file;
    public static ArrayList<String> lines = new ArrayList<>();

    public ManageFile() {
        this.projectRoute = System.getProperty("user.dir"); // Project route
        this.separator = File.separator;
        this.dirRoute = projectRoute + separator + "registroFiles"; // Folder route
        this.file = new File(dirRoute + separator + "registro.txt"); // File route
        this.dir = new File(dirRoute);
    }
    
    /**
     * Makes a new folder and a new file if they don't exist
     * @return
     */
    public boolean mkDirFile() {
        try {
            if (!dir.exists() && !file.exists()) {
                dir.mkdir(); file.createNewFile();
                System.out.println("-- Dir & File created --");
            } else {
                System.out.println("-- Dir & File already created --");
                System.out.println("-- Opening --");
                return true;
            }
            
            
        } catch (IOException e) {
            System.err.println("Something happened. Unable to create");
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Renames the file
     * @param s
     */
    public void renameFile(String s){
        File newName = new File(dirRoute + separator + s);
        file.renameTo(newName);
        System.out.println("-- File renamed --");
    }
    
    /**
     * Renames the folder
     * @param s
     */
    public void renameFolder(String s) {
        File newName = new File(projectRoute + separator + s);
        dir.renameTo(newName);
        System.out.println("-- Dir renamed --");
    }
    
    /**
     * Deletes file if exists
     */
    public void deleteFile() {
        if (file.exists()) {
            file.delete();
        } else {
            System.err.println("-- Non-existent File --");
        }
    }
    
    /**
     * Deletes folder if exists
     */
    public void deleteFolder() {
        if (dir.exists()) {
            dir.delete();
        } else {
            System.err.println("-- Non-existent Folder --");
        }
    }
    
    /**
     * Writes file
     * @param s
     */
    public void writeFile(String s) {
        try { 
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.err.println("Something happened. Unable to write");
            e.printStackTrace();
        }
    }
    
    /**
     * Reads file
     */
    public void readFile() {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            
            while (s != null) {
                System.out.println(s);
                s = br.readLine();
            }
            
            br.close();
        } catch(IOException e) {
            System.err.println("Something happened. Unable to read");
            e.printStackTrace();
        }
    }
    
    /**
     * Removes a line containing a keyword
     * @param s
     */
    public void removeLineContaining(String s) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            String currentLine;
            boolean found = false;
            
            while ((currentLine = br.readLine()) != null) {
                if (!currentLine.contains(s)) {
                   lines.add(currentLine);
                } else {
                   found = true;
                }
            }
            br.close();
            
            if (found) {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
                bw.close();
            }
        } catch (IOException e) {
            System.err.println("Something happened. Unable to remove");
            e.printStackTrace();
        }
    }
    
    /**
     * Returns a line containing a keyword
     * @param s
     * @return
     */
    public String findLine(String s) {
        String line = "";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            int lineNum = 1;
            
            while ((line = br.readLine()) != null) {
                if (line.contains(s)) {
                    break;
                }
                lineNum++;
            }
        } catch (IOException e) {
            System.err.println("Something happened. Unable to find");
            e.printStackTrace();
        }
        return line;
    }
    
    /**
     * Reads file to the list
     * @return
     */
    public ArrayList<String> readFileToList() {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Something happened. Unable to List");
            e.printStackTrace();
        }
        return lines;
    }
    
    /**
     * Returns if file contains no elements
     * @return
     */
    public boolean isFileEmpty() {
        return !file.exists() || file.length() == 0;
    }
}
