package estg.mee.piscoreboard.utils;

import com.jcraft.jsch.SftpProgressMonitor;

/**
 * Created by Fernando Henriques on 27/05/2015.
 */
public class SystemOutProgressMonitor implements SftpProgressMonitor
{
    private boolean checker;

    public SystemOutProgressMonitor() {
        this.checker=false;
    }

    public boolean getChecker() {
        return checker;
    }

    public void init(int op, java.lang.String src, java.lang.String dest, long max)
    {

    }

    public boolean count(long bytes)
    {
        return false;
    }

    public void end()
    {
        this.checker=true;
    }
}
