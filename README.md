improving-education-for-at-risk-students
========================================

Social SImproving Education for At-risk Students

To build:

This application was created using the Spring Tool Suite which is an Eclipse-based development environment that is customized for developing Spring applications. Therefore to build the code locally, it is recommended to download the Spring Tool Suite, clone this repository, and import the code using the .project file. The pom.xml file contains a reference to all dependencies so that when the project is built for the first time using Maven, the dependencies will be automatically downloaded and configured. 

Other dependencies that must be configured manually include Hadoop 2.5.0 HDFS, HBase 0.98.6.1, Hive 0.13.1 and a running Thrift Server. Tomcat 7.0.51 must also be installed and the Spring Tool Suite must have a reference to it to run the web application.