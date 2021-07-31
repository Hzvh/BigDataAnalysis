package com.hzvh.mr;



import com.hzvh.util.JDBCInstance;
import com.hzvh.util.JDBCUtil;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqllOutputFormat extends OutputFormat<Text, Text> {
    private FileOutputCommitter committer = null;

    @Override
    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
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

    static class MysqlRecordWriter extends RecordWriter<Text, Text> {
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


        public void write(Text key, Text value) throws IOException, InterruptedException {


            String key1 = key.toString();
            String[] splits = key1.split("_");
            String call1 = splits[0];
            String call2 = splits[1];

            String id1 = null;
            String id2 = null;
            String sql1 = "select id from tb_contacts where telephone = ? ;";
            try {
                preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setString(1,call1);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                if (resultSet1.next()) {
                    id1 = resultSet1.getString(1);
                }
                preparedStatement.setString(1,call2);
                ResultSet resultSet2 = preparedStatement.executeQuery();
                if (resultSet2.next()) {
                    id2 = resultSet2.getString(1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            String values = value.toString();
            String[] split1 = values.split("_");

            String countSum = split1[0];
            String durationSum = split1[1];

            int intimacy = 1;
            if (Integer.valueOf(durationSum)>=5000) {
                intimacy = 5;
            }else if (Integer.valueOf(durationSum)>=2000)
            {
                intimacy = 4;
            }else if (Integer.valueOf(durationSum)>=1000)
            {
                intimacy = 3;
            }else if (Integer.valueOf(durationSum)>=500)
            {
                intimacy = 2;
            }else   {
                intimacy = 1;
            }

            String sql = "INSERT INTO tb_intimacy VALUES(NULL,?,?,?,?,?);";




            try {
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, intimacy);
                preparedStatement.setInt(2, Integer.valueOf(id1));
                preparedStatement.setInt(3, Integer.valueOf(id2));
                preparedStatement.setInt(4, Integer.valueOf(countSum));
                preparedStatement.setInt(5, Integer.valueOf(durationSum));

//                preparedStatement.setInt(++i, Integer.valueOf(countSum));
//                preparedStatement.setInt(++i, Integer.valueOf(durationSum));

                preparedStatement.executeUpdate();
//                preparedStatement.addBatch();
//                batchSize++;
//                if (batchSize >= batchBound) {
//
//                    preparedStatement.executeBatch();
//                    connection.commit();
//                }

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
                    //preparedStatement.close();
                }
                JDBCUtil.close(connection, preparedStatement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

