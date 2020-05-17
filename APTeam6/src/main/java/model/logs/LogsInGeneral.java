package model.logs;
import controller.ProgramManager;

import java.util.ArrayList;
import java.util.Date;

public class LogsInGeneral {
    protected static int nextLogId = 0;
    protected int logId;
    protected Date date;
    protected int executedDiscount;
    protected int type;      // 1 : buyLog  -  2 : sellLog

    /**
     * Attention: this method automatically adds the log to the log list.
     */
    public LogsInGeneral(Date date, int executedDiscount,int logId,int type) {
        this.date = date;
        this.executedDiscount = executedDiscount;
        nextLogId++;
        this.logId = nextLogId;
        ProgramManager.getProgramManagerInstance().addLogToList(this);
        this.type = type;
    }

    public int getLogId() {
        return logId;
    }
}
