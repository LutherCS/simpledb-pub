package simpledb.query;

import org.apache.commons.lang3.NotImplementedException;

public class RenameScan implements Scan {

    private Scan s;
    private String oldName, newName;

    public RenameScan(Scan s, String oldfldname, String newfldname) {
        this.s = s;
        this.oldName = oldfldname;
        this.newName = newfldname;
    }

    public void beforeFirst() {
        s.beforeFirst();
    }

    public boolean next() {
        return s.next();
    }

    public void close() {
        s.close();
    }

    public Constant getVal(String fldname) {
        throw new NotImplementedException("Rename scan not implemented");
    }

    public int getInt(String fldname) {
        throw new NotImplementedException("Rename scan not implemented");
    }

    public String getString(String fldname) {
        throw new NotImplementedException("Rename scan not implemented");
    }

    public boolean hasField(String fldname) {
        throw new NotImplementedException("Rename scan not implemented");
    }

}
