package simpledb.plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
public class RenamePlanTest {

    private SimpleDB db = new SimpleDB("collegedb");
    private MetadataMgr mdm = db.mdMgr();

    public RenamePlanTest() {
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
    public void testRenamePlan(String oldFieldName, String newFieldName) {
        System.out.println("RENAME");
        Transaction tx = db.newTx();
        Plan studentTblPlan = new TablePlan(tx, "student", mdm);
        Plan plan = new RenamePlan(studentTblPlan, oldFieldName, newFieldName);
        tx.commit();
        // assertEquals(Arrays.asList("sid", "sname", "majorid", "graduated"), plan.schema().fields());
        assertFalse(plan.schema().fields().contains(oldFieldName));
        assertTrue(plan.schema().fields().contains(newFieldName));
        assertEquals(9, plan.recordsOutput());
    }

}