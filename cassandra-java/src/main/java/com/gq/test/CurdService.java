package com.gq.test;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.gq.cluster.SessionFactory;

import java.util.Iterator;

public class CurdService {
    static Session session = SessionFactory.getSession();
    public static void main(String[] args) {
        insert();
        Student student = getStudentByKeys(1, "jack", "beining");
        System.out.println(student);
        session.close();
        SessionFactory.close();
    }


    public void removeStudent(int id, String address, String name)
    {
        session.execute(QueryBuilder.delete()
                .from("mycas", "student")
                .where(QueryBuilder.eq("id", id))
                .and(QueryBuilder.eq("address", address))
                .and(QueryBuilder.eq("name", name)));
    }
    public void updateStudent(Student student)
    {
        session.execute(
                QueryBuilder.update("mycas", "student")
                        .with(QueryBuilder.set("age", student.getAge()))
                        .and(QueryBuilder.set("height", student.getHeight()))
                        .where(QueryBuilder.eq("id", student.getId()))
                        .and(QueryBuilder.eq("address", student.getAddress()))
                        .and(QueryBuilder.eq("name", student.getName())));
    }

    public void saveStudent(Student student)
    {
        session.execute(
                QueryBuilder.insertInto("mycas", "student")
                        .values(new String[]{"id", "address", "name", "age", "height"},
                                new Object[]{student.getId(), student.getAddress(),
                                        student.getName(), student.getAge(), student.getHeight()}));
    }

    public static Student getStudentByKeys(int id, String address, String name)
    {
        Student student = null;
        ResultSet rs = session.execute(
                QueryBuilder.select("id", "address", "name", "age", "height")
                        .from("mycas", "student")
                        .where(QueryBuilder.eq("id", id))
                        .and(QueryBuilder.eq("address", address))
                        .and(QueryBuilder.eq("name", name)));
        Iterator<Row> rsIterator = rs.iterator();
        if (rsIterator.hasNext())
        {
            Row row = rsIterator.next();
            student = new Student();
            student.setAddress(row.getString("address"));
            student.setAge(row.getInt("age"));
            student.setHeight(row.getInt("height"));
            student.setId(row.getInt("id"));
            student.setName(row.getString("name"));
        }

        return student;
    }
    private static void insert() {
        Session session = SessionFactory.getSession();
        Student student = new Student(1, "jack", "beining", 18, 178);
        // 字符串注意单引号'
        String cql = "insert into mycas.student(id,address,name,age,height) values("
                + student.getId() + ",'" + student.getAddress() + "','" + student.getName()
                + "'," + student.getAge() + "," + student.getHeight() + ");";
        System.out.println(cql);
        session.execute(cql);
        System.out.println("done!");

    }
}
