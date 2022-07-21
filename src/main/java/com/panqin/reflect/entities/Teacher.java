package com.panqin.reflect.entities;

import com.panqin.reflect.annotation.Column;
import com.panqin.reflect.annotation.Id;
import com.panqin.reflect.annotation.Table;


    @Table(tableName = "teacher", label = "教师表")
    public class Teacher {
        @Id(idName = "sid")
        @Column(columnName = "sid", label = "教师工号")
        private String id;
        @Column(columnName = "sname", label = "教师姓名")
        private String name;
        @Column(columnName = "sage", label = "教师年龄")
        private Integer age;

        public Teacher() {
        }

        public Teacher(String id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
