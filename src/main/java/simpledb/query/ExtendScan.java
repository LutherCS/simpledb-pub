package simpledb.query;

import static java.sql.Types.INTEGER;

import org.apache.commons.lang3.NotImplementedException;

public class ExtendScan implements Scan {

    private Scan s;
    private String newFldName;
    private int newFldType;
    private int newFldLen;

    public ExtendScan(Scan s, String newfldname, int fldtype, int fldlen) {
        this.s = s;
        this.newFldName = newfldname;
        this.newFldType = fldtype;
        this.newFldLen = fldlen;
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
        throw new NotImplementedException("Extend scan not implemented");
    }

    public int getInt(String fldname) {
        throw new NotImplementedException("Extend scan not implemented");
    }

    public String getString(String fldname) {
        throw new NotImplementedException("Extend scan not implemented");
    }

    public boolean hasField(String fldname) {
        throw new NotImplementedException("Extend scan not implemented");
    }

}
