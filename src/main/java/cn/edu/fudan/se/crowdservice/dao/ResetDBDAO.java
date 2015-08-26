package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.Parameter;

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
                    "  `algorithm` ENUM('mx','rt','rp','cs','th') DEFAULT NULL,\n" +
                    "  `settingid` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `exptimes` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `cost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `deadline` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1successrate` DOUBLE(20,18) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1resultNum` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1deadline` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1cost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1realtime` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1realcost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2successrate` DOUBLE(20,18) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2resultNum` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2deadline` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2cost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2realtime` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2realcost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3successrate` DOUBLE(20,18) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3resultNum` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3deadline` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3cost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3realtime` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3realcost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `successrate` DOUBLE(20,18) UNSIGNED DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
        }
        if (!ignoreExpstatus) {
            statement.addBatch("DROP TABLE IF EXISTS `expstatus`");
            statement.addBatch("CREATE TABLE `expstatus` (\n" +
                    "  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                    "  `expid` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `workerid` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs` ENUM('cs3','cs2','cs1') DEFAULT NULL,\n" +
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
                    "  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                    "  `cost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `reliability` DOUBLE(20,18) UNSIGNED DEFAULT NULL,\n" +
                    "  `responseTime` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `success` VARCHAR(" + Parameter.instance().executeTimes() + ") DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
        }
        statement.addBatch("DROP VIEW IF EXISTS `successrate`");
        statement.addBatch("CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `successrate` AS SELECT `expinput`.`algorithm` AS `algorithm`,`expinput`.`settingid` AS `settingid`,`expinput`.`exptimes` AS `exptimes`,`expinput`.`cost` AS `cost`,`expinput`.`deadline` AS `deadline`,`expinput`.`cs1resultNum` AS `cs1resultNum`,`expinput`.`cs2resultNum` AS `cs2resultNum`,`expinput`.`cs3resultNum` AS `cs3resultNum`,count(`expinput`.`algorithm`) AS `total`,(sum(`expinput`.`successrate`) / count(`expinput`.`algorithm`)) AS `successrate`,(sum(`expinput`.`cs1successrate`) / count(`expinput`.`algorithm`)) AS `cs1successrate`,(sum(`expinput`.`cs2successrate`) / count(`expinput`.`algorithm`)) AS `cs2successrate`,(sum(`expinput`.`cs3successrate`) / count(`expinput`.`algorithm`)) AS `cs3successrate` FROM `expinput` GROUP BY `expinput`.`algorithm`,`expinput`.`settingid`");
        return statement.executeBatch().length > 0;
    }
}
