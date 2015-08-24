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
                    "  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `exptimes` int(10) unsigned DEFAULT NULL,\n" +
                    "  `cost` double(20,2) unsigned DEFAULT NULL,\n" +
                    "  `deadline` int(20) unsigned DEFAULT NULL,\n" +
                    "  `cs1resultNum` int(10) DEFAULT NULL,\n" +
                    "  `cs1deadline` int(20) unsigned DEFAULT NULL,\n" +
                    "  `cs1cost` double(20,2) unsigned DEFAULT NULL,\n" +
                    "  `cs1realtime` int(20) unsigned DEFAULT NULL,\n" +
                    "  `cs1realcost` double(20,2) unsigned DEFAULT NULL,\n" +
                    "  `cs2resultNum` int(10) DEFAULT NULL,\n" +
                    "  `cs2deadline` int(20) unsigned DEFAULT NULL,\n" +
                    "  `cs2cost` double(20,2) DEFAULT NULL,\n" +
                    "  `cs2realtime` int(20) unsigned DEFAULT NULL,\n" +
                    "  `cs2realcost` double(20,2) unsigned DEFAULT NULL,\n" +
                    "  `cs3resultNum` int(10) DEFAULT NULL,\n" +
                    "  `cs3deadline` int(20) unsigned DEFAULT NULL,\n" +
                    "  `cs3cost` double(20,2) DEFAULT NULL,\n" +
                    "  `cs3realtime` int(20) unsigned DEFAULT NULL,\n" +
                    "  `cs3realcost` double(20,2) unsigned DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
        }
        if (!ignoreExpstatus) {
            statement.addBatch("DROP TABLE IF EXISTS `expstatus`");
            statement.addBatch("CREATE TABLE `expstatus` (\n" +
                    "  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `expid` int(10) unsigned DEFAULT NULL,\n" +
                    "  `workerid` int(10) unsigned DEFAULT NULL,\n" +
                    "  `cs` enum('cs3','cs2','cs1') DEFAULT NULL,\n" +
                    "  `algorithm` enum('cs','rp','rt','th') DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  KEY `FK_expid` (`expid`),\n" +
                    "  KEY `FK_workerid` (`workerid`),\n" +
                    "  CONSTRAINT `expstatus_ibfk_1` FOREIGN KEY (`expid`) REFERENCES `expinput` (`id`),\n" +
                    "  CONSTRAINT `expstatus_ibfk_2` FOREIGN KEY (`workerid`) REFERENCES `worker` (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
        }
        if (!ignoreWorker) {
            statement.addBatch("DROP TABLE IF EXISTS `worker`");
            statement.addBatch("CREATE TABLE `worker` (\n" +
                    "  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `cost` double(20,2) unsigned DEFAULT NULL,\n" +
                    "  `reliability` double(20,18) unsigned DEFAULT NULL,\n" +
                    "  `responseTime` int(10) unsigned DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
            statement.addBatch("DROP TABLE IF EXISTS `workersuccess`");
            statement.addBatch("CREATE TABLE `workersuccess` (\n" +
                    "  `workerid` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `expno` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `success` BIT(1) DEFAULT NULL,\n" +
                    "  KEY `FK_worker` (`workerid`),\n" +
                    "  CONSTRAINT `FK_worker` FOREIGN KEY (`workerid`) REFERENCES `worker` (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
        }
        return statement.executeBatch().length > 0;
    }
}
