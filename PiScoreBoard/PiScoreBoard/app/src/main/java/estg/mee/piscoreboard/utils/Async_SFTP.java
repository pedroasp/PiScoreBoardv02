package estg.mee.piscoreboard.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.controller.MainActivity;

/**
 * Created by Fernando Henriques on 26/05/2015.
 */
public class Async_SFTP {

    private Context context;
    private final String REMOTE_PUBS_DIR = "/home/pi/Desktop/piScoreServer22/src/piscoreserver/Pubs";
    private final String REMOTE_LOGOS_DIR = "/home/pi/Desktop/piScoreServer22/src/piscoreserver/Logos";
    private final String REMOTE_VIDEOS_DIR = "/home/pi/Desktop/piScoreServer22/src/piscoreserver/Videos";
//    private final String LOCAL_VIDEOS_DIR = "/storage/sdcard0/piScoreBoard/videos";
//    private final String LOCAL_PUBS_DIR = "/storage/sdcard0/piScoreBoard/pubs";
//    private final String LOCAL_LOGOS_DIR = "/storage/sdcard0/piScoreBoard/logos";
//    private final String LOCAL_LOGOS_DIR = "/sdcard/piScoreBoard/logos";
//    private final String LOCAL_PUBS_DIR = "/sdcard/piScoreBoard/pubs";
//    private final String LOCAL_VIDEOS_DIR = "/sdcard/piScoreBoard/videos";
    private final String LOCAL_VIDEOS_DIR = Environment.getExternalStorageDirectory().toString() + "/piScoreBoard/videos";
    private final String LOCAL_PUBS_DIR = Environment.getExternalStorageDirectory().toString() + "/piScoreBoard/pubs";
    private final String LOCAL_LOGOS_DIR = Environment.getExternalStorageDirectory().toString() + "/piScoreBoard/logos";

    public Async_SFTP() {

    }

    public void listLogos(Context context) {
        this.context = context;
        (new List(this.context)).execute(REMOTE_LOGOS_DIR);
    }

    public void listPubs(Context context) {
        this.context = context;
        (new List(this.context)).execute(REMOTE_PUBS_DIR);
    }

    public void listVideos(Context context) {
        this.context = context;
        (new List(this.context)).execute(REMOTE_VIDEOS_DIR);
    }


    private class List extends AsyncTask<String, Void, ArrayList<String>> {

        private Context context;

        public List(Context context) {
            this.context = context;
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            PyScoreSFTP sftpProcess = new PyScoreSFTP();
            return sftpProcess.listFolder(params[0]);

        }

        @Override
        protected void onPostExecute(ArrayList<String> list) {
            MainActivity activity = (MainActivity) context;
            Toast.makeText(context, list.get(0), Toast.LENGTH_SHORT).show();
           // TextView response = (TextView) activity.findViewById(R.id.response);
          //  response.setText(list.get(3));
        }
    }


    public void existLogo(Context context, String path) {
        ArrayList<String> manageResults;
        this.context = context;
        (new Exist(this.context)).execute(REMOTE_LOGOS_DIR, path);
    }

    public void existPub(Context context, String path) {
        this.context = context;
        (new Exist(this.context)).execute(REMOTE_PUBS_DIR, path);
    }

    public void existVideo(Context context, String path) {
        this.context = context;
        (new Exist(this.context)).execute(REMOTE_VIDEOS_DIR, path);
    }


    private class Exist extends AsyncTask<String, Void, Boolean> {

        private Context context;

        public Exist(Context context) {
            this.context = context;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            return new PyScoreSFTP().CheckIfFileExists(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(Boolean exist) {
            MainActivity activity = (MainActivity) context;

//            TextView response = (TextView) activity.findViewById(R.id.response);
//            if (exist)
//                response.setText("File Exists");
//            else
//                response.setText("File Don't Exists");
        }
    }


    public void removeAllLogos(Context context) {
        this.context = context;
        (new Remove(this.context)).execute(REMOTE_LOGOS_DIR, "ALL");
    }

    public void removeAllPubs(Context context) {
        this.context = context;
        (new Remove(this.context)).execute(REMOTE_PUBS_DIR, "ALL");
    }

    public void removeAllVideos(Context context) {
        this.context = context;
        (new Remove(this.context)).execute(REMOTE_VIDEOS_DIR, "ALL");
    }

    public void removeLogo(Context context, String logoName) {
        this.context = context;
        (new Remove(this.context)).execute(REMOTE_LOGOS_DIR, logoName);
    }

    public void removeVideo(Context context, String videoName) {
        this.context = context;
        (new Remove(this.context)).execute(REMOTE_VIDEOS_DIR, videoName);
    }

    public void removePub(Context context, String pubName) {
        this.context = context;
        (new Remove(this.context)).execute(REMOTE_PUBS_DIR, pubName);
    }


    private class Remove extends AsyncTask<String, Void, Boolean> {

        private Context context;

        public Remove(Context context) {
            this.context = context;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean success;

            if (params[1].equalsIgnoreCase("ALL"))
                success = new PyScoreSFTP().removeAllFromFolder(params[0]);
            else
                success = new PyScoreSFTP().removeElement(params[0], params[1]);

            return success;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            MainActivity activity = (MainActivity) context;
//            TextView response = (TextView) activity.findViewById(R.id.response);
//            if (success)
//                response.setText("  Removed ");
//            else
//                response.setText("Not Erased");
        }
    }



    public void uploadPubs(Context context, ArrayList<String> pubsNames) {
        this.context = context;
        (new Upload(this.context, REMOTE_PUBS_DIR)).execute(pubsNames);
    }

    public void uploadLogos(Context context, ArrayList<String> logosNames) {
        this.context = context;
        (new Upload(this.context, REMOTE_LOGOS_DIR)).execute(logosNames);
    }

    public void uploadVideos(Context context, ArrayList<String> videosNames) {
        this.context = context;
        (new Upload(this.context, REMOTE_VIDEOS_DIR)).execute(videosNames);
    }


    private class Upload extends AsyncTask<ArrayList<String>, Void,Boolean > {

        private Context context;
        private String remoteFolderPath;

        public Upload(Context context,String remoteFolderPath) {
            this.context = context;
            this.remoteFolderPath = remoteFolderPath;
        }

        @Override
        protected Boolean doInBackground(ArrayList<String>... params) {
            PyScoreSFTP sftpProcess = new PyScoreSFTP();
            return sftpProcess. upLoadFiles(remoteFolderPath, params[0]);

        }

        @Override
        protected void onPostExecute(Boolean sucess) {
            MainActivity activity = (MainActivity) context;
//            TextView response = (TextView) activity.findViewById(R.id.response);
//            if(sucess)
//            response.setText("Files Uploaded");
//            else
//                response.setText("Files Not Uploaded");
        }
    }



    public void downloadPub(Context context, String pubName) {
        this.context = context;
        (new Download(this.context)).execute(REMOTE_PUBS_DIR,LOCAL_PUBS_DIR,pubName);
    }

    public void downloadLogo(Context context, String logoName) {
        this.context = context;
        (new Download(this.context)).execute(REMOTE_LOGOS_DIR,LOCAL_LOGOS_DIR,logoName);
    }

    public void downloadVideo(Context context, String videoName) {
        this.context = context;
        (new Download(this.context)).execute(REMOTE_VIDEOS_DIR,LOCAL_VIDEOS_DIR,videoName);
    }

    public void downloadAllVideos(Context context) {
        this.context = context;
        (new Download(this.context)).execute(REMOTE_VIDEOS_DIR,LOCAL_VIDEOS_DIR,"DOWNLOAD ALL");
    }

    public void downloadAllPubs(Context context) {
        this.context = context;
        (new Download(this.context)).execute(REMOTE_PUBS_DIR,LOCAL_PUBS_DIR,"DOWNLOAD ALL");
    }

    public void downloadAllLogos(Context context) {
        this.context = context;
        (new Download(this.context)).execute(REMOTE_LOGOS_DIR,LOCAL_LOGOS_DIR,"DOWNLOAD ALL");
    }


    private class Download extends AsyncTask<String, Void, Boolean> {

        private Context context;

        public Download(Context context) {
            this.context = context;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            PyScoreSFTP sftpProcess = new PyScoreSFTP();

            if(params[2].equalsIgnoreCase("DOWNLOAD ALL"))
                return sftpProcess.downLoadAllFromFolder(params[0],params[1]);
            else
                return sftpProcess.downLoadSingleFile(params[0],params[1],params[2]);

        }

        @Override
        protected void onPostExecute(Boolean sucess) {
            MainActivity activity = (MainActivity) context;
//            TextView response = (TextView) activity.findViewById(R.id.response);
//            if(sucess)
//                response.setText("File(s) Downloaded");
//            else
//                response.setText("File(s) not Downloaded");
        }
    }

}
