package simpledb.query;

import simpledb.metadata.MetadataMgr;
import simpledb.plan.JoinPlan;
import simpledb.plan.Plan;
import simpledb.plan.TablePlan;
import simpledb.server.SimpleDB;
import simpledb.tx.Transaction;

/**
 * Demo of the scan class corresponding to the <i>join</i> relational
 * algebra operator.
 * 
 * @author roman
 */
public class JoinScanDemo {
    public static void main(String[] args) {
        System.out.println("JOIN");
        SimpleDB db = new SimpleDB("collegedb");
        MetadataMgr mdm = db.mdMgr();
        Transaction tx = db.newTx();
        Plan studentTblPlan = new TablePlan(tx, "student", mdm);
        Plan deptTblPlan = new TablePlan(tx, "dept", mdm);
        tx.commit();
        Plan plan = new JoinPlan(studentTblPlan, deptTblPlan,
                new Predicate(
                        new Term(
                                new Expression("majorid"),
                                new Expression("did"))));
        for (String field : plan.schema().fields()) {
            System.out.printf("%10s", field);
        }
        System.out.println();
        Scan scan = plan.open();
        while (scan.next()) {
            for (String field : plan.schema().fields()) {
                System.out.printf("%10s", scan.getVal(field).toString());
            }
            System.out.println();
        }
    }
}
