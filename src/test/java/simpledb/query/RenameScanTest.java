package simpledb.query;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import simpledb.metadata.MetadataMgr;
import simpledb.plan.Plan;
import simpledb.plan.RenamePlan;
import simpledb.plan.TablePlan;
import simpledb.server.SimpleDB;
import simpledb.tx.Transaction;

/**
 *
 * @author Roman Yasinovskyy
 * @version 2025.11
 */
public class RenameScanTest {

    private SimpleDB db = new SimpleDB("collegedb");
    private MetadataMgr mdm = db.mdMgr();

    public RenameScanTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @ParameterizedTest
    @CsvSource({
            "gradyear, graduated",
            "sname, name"
    })
    public void testRenameScan(String oldFieldName, String newFieldName) {
        System.out.println("RENAME");
        Transaction tx = db.newTx();
        Plan studentTblPlan = new TablePlan(tx, "student", mdm);
        Plan plan = new RenamePlan(studentTblPlan, oldFieldName, newFieldName);
        tx.commit();
        Scan scan = plan.open();
        int records = 0;
        while (scan.next()) {
            records++;
        }
        assertEquals(9, records);
    }

}