package simpledb.query;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import simpledb.metadata.MetadataMgr;
import simpledb.plan.ExtendPlan;
import simpledb.plan.Plan;
import simpledb.plan.TablePlan;
import simpledb.server.SimpleDB;
import simpledb.tx.Transaction;

/**
 *
 * @author Roman Yasinovskyy
 * @version 2025.11
 */
public class ExtendScanTest {
    private SimpleDB db = new SimpleDB("collegedb");
    private MetadataMgr mdm = db.mdMgr();

    @ParameterizedTest
    @CsvSource({
            "gradclass, 4, 4, 9", // INTEGER
            "initial, 12, 1, 9" // VARCHAR
    })
    public void testExtendPlan(String newfield, int type, int size, int expected) {
        System.out.println("EXTEND");
        Transaction tx = db.newTx();
        Plan tblPlan = new TablePlan(tx, "student", mdm);
        Plan plan = new ExtendPlan(tblPlan, newfield, type, size);
        Scan scan = plan.open();
        int records = 0;
        while (scan.next()) {
            records++;
        }
        assertEquals(expected, records);
        tx.commit();
    }
}