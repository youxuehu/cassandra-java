package com.gq.cluster;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SessionFactory {
    private static Session instance = null;
    private static Cluster cluster = null;
    private static Lock lock = new ReentrantLock();

    private SessionFactory(){}

    public static Session getSession()
    {
        if (null == instance)
        {
            try
            {
                lock.lock();

                if (null == instance)
                {
                    cluster = Cluster.builder()
                            .addContactPoint("127.0.0.1")
                            .withCredentials("cassandra", "cassandra")
                            .build();
                    instance = cluster.connect();
                    // 也可以针对一个特定的keyspace获取一个session
                    // instance = cluster.connect("mycas");
                }
            }
            finally
            {
                lock.unlock();
            }
        }
        return instance;
    }

    public static void close()
    {
        if (null == cluster)
        {
            try
            {
                lock.lock();

                if (null == cluster)
                {
                    cluster.close();
                }
            }
            finally
            {
                lock.unlock();
            }
        }
    }
}
