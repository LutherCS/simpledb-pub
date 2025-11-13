package simpledb.plan;

import static java.sql.Types.INTEGER;
import static java.sql.Types.VARCHAR;

import org.apache.commons.lang3.NotImplementedException;

import simpledb.query.ExtendScan;
import simpledb.query.Scan;
import simpledb.record.Schema;

/**
 * The Plan class corresponding to <i>extend</i>
 * relational algebra operator.
 *
 * @author Roman Yasinovskyy
 * @version 2025.11
 */
public class ExtendPlan implements Plan {

    private Plan plan;
    private String newFldName;
    private int newFldType;
    private int newFldLen;
    private Schema currentSchema = new Schema();

    public ExtendPlan(Plan plan, String newfldname, int fldtype, int fldlen) {
        throw new NotImplementedException("Extend plan not implemented");
    }

    public Scan open() {
        Scan s = plan.open();
        return new ExtendScan(s, newFldName, newFldType, newFldLen);
    }

    public int blocksAccessed() {
        return plan.blocksAccessed();
    }

    public int recordsOutput() {
        return plan.recordsOutput();
    }

    public int distinctValues(String fldname) {
        return plan.distinctValues(fldname);
    }

    public Schema schema() {
        return currentSchema;
    }
}
