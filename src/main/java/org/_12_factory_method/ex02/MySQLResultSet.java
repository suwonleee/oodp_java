package org._12_factory_method.ex02;

class MySQLResultSet implements ResultSet {
    public void getResults() {
        System.out.println("Getting results from MySQL database");
    }
}