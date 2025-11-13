package simpledb.plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import simpledb.metadata.MetadataMgr;
import simpledb.server.SimpleDB;
import simpledb.tx.Transaction;

/**
 *
 * @author Roman Yasinovskyy
 * @version 2025.11
 */
public class ExtendPlanTest {
    private SimpleDB db = new SimpleDB("collegedb");
    private MetadataMgr mdm = db.mdMgr();

    @ParameterizedTest
    @CsvSource({
            "gradclass, 4, 4", // INTEGER
            "initial, 12, 1" // VARCHAR
    })
    public void testExtendPlan(String newField, int type, int size) {
        System.out.println("EXTEND");
        Transaction tx = db.newTx();
        Plan tblPlan = new TablePlan(tx, "student", mdm);
        Plan plan = new ExtendPlan(tblPlan, newField, type, size);
        tx.commit();
        assertTrue(plan.schema().fields().contains(newField));
        assertEquals(9, plan.recordsOutput());
    }
}