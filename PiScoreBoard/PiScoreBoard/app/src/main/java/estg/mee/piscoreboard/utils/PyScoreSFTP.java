/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.mee.piscoreboard.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Fernando Henriques
 */
public class PyScoreSFTP {

    private final String SFTPHOST = "10.5.5.1";
    private final int SFTPPORT = 22;
    private final String SFTPUSER = "pi";
    private final String SFTPPASS = "raspberry";
    private final String SFTPWORKINGDIR = "/home/pi";
    private final String SFTP_PUB_IM_DIR = "/home/pi/Desktop/piScoreServer22/src/piscoreserver/Pubs";
    private final String SFTP_LOGOS_IM_DIR = "/home/pi/Desktop/piScoreServer22/src/piscoreserver/Logos";
    private final String SFTP_VIDEOS_IM_DIR = "/home/pi/Desktop/piScoreServer22/src/piscoreserver/Videos";

    Session session = null;
    Channel channel = null;
    ChannelSftp channelSftp = null;
    Vector<ChannelSftp.LsEntry> listFiles;
    ArrayList<String> stringListOfFiles;


    public PyScoreSFTP() {
        this.stringListOfFiles = new ArrayList<>();
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            try {
                channelSftp.cd(SFTPWORKINGDIR);
            } catch (SftpException ex) {
                Logger.getLogger(PyScoreSFTP.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JSchException ex) {
            Logger.getLogger(PyScoreSFTP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public ArrayList<String> listFolder(String path) {

        try {
            listFiles = channelSftp.ls(path);
            for (LsEntry entry : listFiles) {
                if ((entry.getFilename().toLowerCase() != "." || entry.getFilename().toLowerCase() != "..") && entry.getAttrs().isDir() != true) {
                    stringListOfFiles.add(entry.getFilename());
                }
            }
        } catch (SftpException ex) {
            Logger.getLogger(PyScoreSFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stringListOfFiles;
    }


    public boolean removeElement(String path, String logoName) {

        try {
            channelSftp.cd(path);
            channelSftp.rm(logoName);

        } catch (SftpException ex) {
            Logger.getLogger(PyScoreSFTP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean removeAllFromFolder(String path) {
        boolean output = true;
        try {
            if (session.isConnected()) {
                channelSftp.cd(path);
                listFiles = channelSftp.ls(path);
                if (!listFiles.isEmpty()) {
                    for (LsEntry entry : listFiles) {
                        if ((entry.getFilename().toLowerCase() != "." || entry.getFilename().toLowerCase() != "..") && entry.getAttrs().isDir() != true) {
                            channelSftp.rm(entry.getFilename());

                        }
                    }

                }else{
                    output = false;
                }

            }else {
               output = false;
            }

            }catch(SftpException ex){
                Logger.getLogger(PyScoreSFTP.class.getName()).log(Level.SEVERE, null, ex);
                output = false;
            }
        return output;
    }

    public boolean upLoadFiles(String remotePath, ArrayList<String> listOfFiles) {


        boolean output = true;
        ArrayList<String> remoteFilesList = new ArrayList<String>();

        try {
            if (session.isConnected()) {
                channelSftp.cd(remotePath);
                remoteFilesList = listFolder(remotePath);

                for (String localFileName : listOfFiles) {

                    for (String remoteFileName : remoteFilesList) {
                        if (remoteFileName.equalsIgnoreCase(localFileName)) {

                            break;
                        }
                    }
                    channelSftp.put(localFileName, remotePath);
                }

            }else{
                output = false;
            }

        } catch (SftpException ex) {
            Logger.getLogger(PyScoreSFTP.class.getName()).log(Level.SEVERE, null, ex);
            output = false;
        }
        return output;
    }


    public boolean downLoadAllFromFolder(String remoteFolderPath, String localFolderPath) {
        boolean output = true;
        try {
            if(session.isConnected()){
                channelSftp.cd(remoteFolderPath);
                listFiles = channelSftp.ls(remoteFolderPath);

                for (LsEntry entry : listFiles) {
                    if ((entry.getFilename().toLowerCase() != "." || entry.getFilename().toLowerCase() != "..") && entry.getAttrs().isDir() != true) {
                        if (!localFolderContains(localFolderPath, entry.getFilename()))
                            while (!localFolderContains(localFolderPath, entry.getFilename()))
                                channelSftp.get(entry.getFilename(), localFolderPath);
                    }
                }
            }else{
                output = false;
            }

        } catch (SftpException ex) {
            Logger.getLogger(PyScoreSFTP.class.getName()).log(Level.SEVERE, null, ex);
            output = false;
        }
        return output;
    }


    public boolean downLoadSingleFile(String remoteFolderPath, String localFolderPath, String fileName) {
        ArrayList<String> listOfFiles = new ArrayList<>();
        boolean output = true;
        try {
            if (session.isConnected()) {
                channelSftp.cd(remoteFolderPath);
                listFiles = channelSftp.ls(remoteFolderPath);

                for (LsEntry entry : listFiles) {
                    if (entry.getFilename().equalsIgnoreCase(fileName)) {
                        if (!localFolderContains(localFolderPath, fileName))
                            while (!localFolderContains(localFolderPath, fileName))
                                channelSftp.get(entry.getFilename(), localFolderPath);
                    }
                }
            }else{
                output = false;
            }

        } catch (SftpException ex) {
            Logger.getLogger(PyScoreSFTP.class.getName()).log(Level.SEVERE, null, ex);
             output = false;
        }
        return output;
    }

    public boolean CheckIfFileExists(String path, String name) {
        boolean output = true;
        ArrayList<String> listOfFiles = new ArrayList<>();
        try {
            if(session.isConnected()){
                channelSftp.cd(path);
                listFiles = channelSftp.ls(path);

                for (LsEntry entry : listFiles) {
                    if ((entry.getFilename().toLowerCase() != "." || entry.getFilename().toLowerCase() != "..") && entry.getAttrs().isDir() != true) {
                        if (entry.getFilename().equalsIgnoreCase(name)) {

                        }
                    }
                }
            }
        } catch (SftpException ex) {
            Logger.getLogger(PyScoreSFTP.class.getName()).log(Level.SEVERE, null, ex);
            output = false;
        }
        return output;
    }


    private ArrayList<String> getLocalFilesList(String localFolderPath) {
        ArrayList<String> pathsOfSelectedFiles = new ArrayList<String>();

        File f = new File(localFolderPath);
        File filesList[] = f.listFiles();

        for (File entry : filesList) {
            pathsOfSelectedFiles.add(entry.getName());
        }
        return pathsOfSelectedFiles;
    }

    private boolean localFolderContains(String localFolderPath, String fileName) {
        boolean exists = false;
        ArrayList<String> filesList = new ArrayList<String>();

        filesList = getLocalFilesList(localFolderPath);

        for (String entry : filesList) {
            if (entry.equalsIgnoreCase(fileName)) {
                exists = true;
            }

        }
        return exists;
    }

}
