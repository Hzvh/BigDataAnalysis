import com.hzvh.utils.ConnectionInstance;
import com.hzvh.utils.HbaseFilterUtil;
import com.hzvh.utils.HbaseUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;

public class HbaseFilterTest {

@Test
    public void getData() throws IOException {


    Connection connection = ConnectionInstance.getInstance();
    Table table = connection.getTable(TableName.valueOf("ct:calllog"));

    Scan scan = new Scan();

    Filter filter1 = HbaseFilterUtil.eqFilter("f1", "call1", Bytes.toBytes("16886343317"));
    Filter filter2 = HbaseFilterUtil.eqFilter("f1", "call2", Bytes.toBytes("16886343317"));
    Filter filter3 = HbaseFilterUtil.orFilter(filter1, filter2);

    Filter filter4 = HbaseFilterUtil.gteqFilter("f1", "buildtime", Bytes.toBytes("2019-02"));
    Filter filter5 = HbaseFilterUtil.lteqFilter("f1", "buildtime", Bytes.toBytes("2019-05"));
    Filter filter6 = HbaseFilterUtil.andFilter(filter4, filter5);

    Filter filter = HbaseFilterUtil.andFilter(filter3, filter6);
    scan.setFilter(filter);

    ResultScanner scanner = table.getScanner(scan);

    for (Result result : scanner) {
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println(Bytes.toString(CellUtil.cloneRow(cell))
                     +Bytes.toString(CellUtil.cloneFamily(cell))
                     +Bytes.toString(CellUtil.cloneQualifier(cell))
                     +Bytes.toString(CellUtil.cloneValue(cell)));
        }
    }

    }
}
