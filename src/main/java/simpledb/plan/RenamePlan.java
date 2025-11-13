package simpledb.plan;

import org.apache.commons.lang3.NotImplementedException;

import simpledb.query.RenameScan;
import simpledb.query.Scan;
import simpledb.record.Schema;

/**
 * The Plan class corresponding to <i>rename</i>
 * relational algebra operator.
 * 
 * @author Roman Yasinovskyy
 * @version 2025.11
 */
public class RenamePlan implements Plan {

    private Plan plan;
    private String oldFieldName;
    private String newFieldName;
    private Schema schema = new Schema();

    /**
     * Creates a new rename node in the query tree
     * 
     * @param plan         the underlying query
     * @param oldFieldName the old field name
     * @param newFieldName the new field name
     * 
     */
    public RenamePlan(Plan plan, String oldFieldName, String newFieldName) {
        throw new NotImplementedException("Rename plan not implemented");
    }

    /**
     * Creates a rename scan for this query.
     * 
     * @see simpledb.plan.Plan#open()
     */
    public Scan open() {
        Scan s = plan.open();
        return new RenameScan(s, oldFieldName, newFieldName);
    }

    /**
     * Estimates the number of block accesses in the rename.
     * The formula is:
     * 
     * <pre>
     * B(rename(p)) = B(p)
     * </pre>
     * 
     * @see simpledb.plan.Plan#blocksAccessed()
     */
    public int blocksAccessed() {
        return plan.blocksAccessed();
    }

    /**
     * Estimates the number of output records in the rename.
     * The formula is:
     * 
     * <pre>
     * R(rename(p)) = R(p)
     * </pre>
     * 
     * @see simpledb.plan.Plan#recordsOutput()
     */
    public int recordsOutput() {
        return plan.recordsOutput();
    }

    /**
     * Estimates the distinct number of field values in the rename.
     * Since the rename does not increase or decrease field values,
     * the estimate is the same as in the appropriate underlying query.
     * 
     * @see simpledb.plan.Plan#distinctValues(java.lang.String)
     */
    public int distinctValues(String fldname) {
        if (fldname.equals(newFieldName)) {
            return plan.distinctValues(oldFieldName);
        } else {
            return plan.distinctValues(fldname);
        }
    }

    /**
     * Returns the schema of the rename,
     * which is the same as the schemas of the underlying query.
     * 
     * @see simpledb.plan.Plan#schema()
     */
    public Schema schema() {
        return schema;
    }
}
