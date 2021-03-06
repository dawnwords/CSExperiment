package cn.edu.fudan.se.crowdservice.dao;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class ResetDBDAO extends DAO<Boolean> {
    private boolean ignoreExpinput, ignoreExpstatus, ignoreWorker;

    public static void main(String[] args) {
        new ResetDBDAO().getResult();
    }

    public ResetDBDAO ignoreExpinput() {
        this.ignoreExpinput = true;
        return this;
    }

    public ResetDBDAO ignoreExpstatus() {
        this.ignoreExpstatus = true;
        return this;
    }

    public ResetDBDAO ignoreWorker() {
        this.ignoreWorker = true;
        return this;
    }

    @Override
    protected Boolean processData(Connection connection) throws Exception {
        if (ignoreExpinput && ignoreExpstatus && ignoreWorker) {
            return true;
        }
        Statement statement = connection.createStatement();
        statement.addBatch("SET FOREIGN_KEY_CHECKS=0");
        if (!ignoreExpinput) {
            statement.addBatch("DROP TABLE IF EXISTS `expinput`");
            statement.addBatch("CREATE TABLE `expinput` (\n" +
                    "  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                    "  `cost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `deadline` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1resultNum` INT(10) DEFAULT NULL,\n" +
                    "  `cs1deadline` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1cost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1realtime` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1realcost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1realResultNum` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1success` BIT(1) DEFAULT NULL,\n" +
                    "  `cs2resultNum` INT(10) DEFAULT NULL,\n" +
                    "  `cs2deadline` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2cost` DOUBLE(20,2) DEFAULT NULL,\n" +
                    "  `cs2realtime` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2realcost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2realResultNum` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2success` BIT(1) DEFAULT NULL,\n" +
                    "  `cs3resultNum` INT(10) DEFAULT NULL,\n" +
                    "  `cs3deadline` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3cost` DOUBLE(20,2) DEFAULT NULL,\n" +
                    "  `cs3realtime` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3realcost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3realResultNum` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3success` BIT(1) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=0 CHARSET=utf8");
        }
        if (!ignoreExpstatus) {
            statement.addBatch("DROP TABLE IF EXISTS `expstatus`");
            statement.addBatch("CREATE TABLE `expstatus` (\n" +
                    "  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                    "  `expid` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `workerid` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs` ENUM('CS3','CS2','CS1') DEFAULT NULL,\n" +
                    "  `selected` BIT(1) DEFAULT NULL,\n" +
                    "  `success` BIT(1) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  KEY `FK_expid` (`expid`),\n" +
                    "  KEY `FK_workerid` (`workerid`),\n" +
                    "  CONSTRAINT `FK_expid` FOREIGN KEY (`expid`) REFERENCES `expinput` (`id`),\n" +
                    "  CONSTRAINT `FK_workerid` FOREIGN KEY (`workerid`) REFERENCES `worker` (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=0 CHARSET=utf8");
        }
        if (!ignoreWorker) {
            statement.addBatch("DROP TABLE IF EXISTS `worker`");
            statement.addBatch("CREATE TABLE `worker` (\n" +
                    "  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                    "  `cost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `reliability` DOUBLE(20,18) UNSIGNED DEFAULT NULL,\n" +
                    "  `responseTime` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8");
        }
        return statement.executeBatch().length > 0;
    }
}
