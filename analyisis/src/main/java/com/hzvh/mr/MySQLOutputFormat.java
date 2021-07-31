package com.hzvh.mr;


import com.hzvh.convertor.DimensionConverterImpl;
import com.hzvh.kv.base.BaseDimension;

import com.hzvh.kv.key.CommDimension;
import com.hzvh.kv.value.CountDurationValue;
import com.hzvh.util.JDBCInstance;
import com.hzvh.util.JDBCUtil;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLOutputFormat extends OutputFormat<BaseDimension, CountDurationValue> {
    private FileOutputCommitter committer = null;

    @Override
    public RecordWriter<BaseDimension, CountDurationValue> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
        //创建jdbc连接
        Connection connection = null;
        try {
            connection = JDBCInstance.getInstance();
            //关闭自动提交，以便于批量提交
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new MysqlRecordWriter(connection);
    }

    @Override
    public void checkOutputSpecs(JobContext job) throws IOException, InterruptedException {
        // 校检输出
    }

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if (committer == null) {
            Path output = getOutputPath(context);
            committer = new FileOutputCommitter(output, context);
        }
        return committer;

       /* String name = context.getConfiguration().get(FileOutputFormat.OUTDIR);
        Path output = name == null ? null : new Path(name);
        return new FileOutputCommitter(output, context);*/
    }

    private static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name == null ? null : new Path(name);
    }

    static class MysqlRecordWriter extends RecordWriter<BaseDimension, CountDurationValue> {
        private Connection connection = null;
        // private DimensionConverter dimensionConverter = null;
        private PreparedStatement preparedStatement = null;
        //private int batchNumber = 0;
        private int batchBound = 500;
        private int batchSize = 0;

        public MysqlRecordWriter(Connection connection) {
            this.connection = connection;
            //this.batchNumber = Constants.JDBC_DEFAULT_BATCH_NUMBER;
            //this.dimensionConverter = new DimensionConverter();
        }

        @Override
        public void write(BaseDimension key, CountDurationValue value) throws IOException, InterruptedException {

                CommDimension commDimension = (CommDimension) key;

                String sql = "INSERT INTO ct.tb_call VALUES(?,?,?,?,?) " +
                        "ON DUPLICATE KEY UPDATE `call_sum` = ?, `call_duration_sum` = ? ;";
                DimensionConverterImpl converter = new DimensionConverterImpl();

                int contactId = converter.getDimensionID(commDimension.getContactDimension());
                int dateId = converter.getDimensionID(commDimension.getDateDimension());
                String date_contact = dateId + "_" + contactId;

                int countSum = Integer.valueOf(value.getCountSum());
                int durationSum = Integer.valueOf(value.getDurationSum());

                try {
                    preparedStatement = connection.prepareStatement(sql);
                    int i = 0;
                    preparedStatement.setString(++i, date_contact);
                    preparedStatement.setInt(++i, dateId);
                    preparedStatement.setInt(++i, contactId);
                    preparedStatement.setInt(++i, countSum);
                    preparedStatement.setInt(++i, durationSum);

                    preparedStatement.setInt(++i, countSum);
                    preparedStatement.setInt(++i, durationSum);
                    preparedStatement.addBatch();
                    batchSize++;
                    if (batchSize >= batchBound) {

                        preparedStatement.executeBatch();
                        connection.commit();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }


        }


        @Override
        public void close(TaskAttemptContext context) throws IOException, InterruptedException {
            try {
                if (preparedStatement != null) {
                    preparedStatement.executeBatch();
                    connection.commit();
                    preparedStatement.close();
                }
                JDBCUtil.close(connection, preparedStatement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

