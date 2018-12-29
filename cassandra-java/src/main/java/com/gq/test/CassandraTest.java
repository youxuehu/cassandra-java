package com.gq.test;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraTest {
    public static void main(String[] args) {
        Cluster cluster = null;
        try {
            cluster = Cluster.builder().addContactPoint("127.0.0.1").withCredentials("cassandra", "cassandra").build();
            Session session = cluster.connect();
            ResultSet re = session.execute("select release_version from system.local");
            Row row = re.one();
            String releaseVersion = row.getString("release_version");
            System.out.printf(releaseVersion);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cluster != null) {
                cluster.close();
            }
        }
    }
}
