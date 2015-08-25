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
                    "  `settingid` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `exptimes` INT(10) UNSIGNED DEFAULT NULL,\n" +
                    "  `cost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `deadline` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1resultNum` INT(10) DEFAULT NULL,\n" +
                    "  `cs1deadline` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1cost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1realtime` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs1realcost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2resultNum` INT(10) DEFAULT NULL,\n" +
                    "  `cs2deadline` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2cost` DOUBLE(20,2) DEFAULT NULL,\n" +
                    "  `cs2realtime` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs2realcost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3resultNum` INT(10) DEFAULT NULL,\n" +
                    "  `cs3deadline` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3cost` DOUBLE(20,2) DEFAULT NULL,\n" +
                    "  `cs3realtime` INT(20) UNSIGNED DEFAULT NULL,\n" +
                    "  `cs3realcost` DOUBLE(20,2) UNSIGNED DEFAULT NULL,\n" +
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
                    "  `algorithm` ENUM('cs','rp','rt','mx','th') DEFAULT NULL,\n" +
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
        statement.addBatch("DROP VIEW IF EXISTS `csresult`");
        statement.addBatch("CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `csresult` AS SELECT `cs1result`.`id` AS `expid`,`cs1result`.`expno` AS `expno`,((`cs1result`.`success` >= `cs1result`.`cs1resultNum`) AND (`cs2result`.`success` >= `cs1result`.`cs2resultNum`) AND (`cs3result`.`success` >= `cs1result`.`cs3resultNum`)) AS `success` FROM ((`expresult` `cs1result` JOIN `expresult` `cs2result` ON(((`cs1result`.`id` = `cs2result`.`id`) AND (`cs2result`.`cs` = 'cs2') AND (`cs1result`.`expno` = `cs2result`.`expno`)))) JOIN `expresult` `cs3result` ON(((`cs1result`.`id` = `cs3result`.`id`) AND (`cs3result`.`cs` = 'cs3') AND (`cs1result`.`expno` = `cs3result`.`expno`)))) WHERE (`cs1result`.`cs` = 'cs1')");
        statement.addBatch("DROP VIEW IF EXISTS `expresult`");
        statement.addBatch("CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `expresult` AS SELECT `expinput`.`id` AS `id`,`expinput`.`exptimes` AS `exptimes`,`expinput`.`cost` AS `cost`,`expinput`.`deadline` AS `deadline`,`expinput`.`cs1resultNum` AS `cs1resultNum`,`expinput`.`cs2resultNum` AS `cs2resultNum`,`expinput`.`cs3resultNum` AS `cs3resultNum`,`workersuccess`.`expno` AS `expno`,sum(`workersuccess`.`success`) AS `success`,`expstatus`.`cs` AS `cs` FROM (((`expinput` JOIN `expstatus` ON((`expstatus`.`expid` = `expinput`.`id`))) JOIN `worker` ON((`expstatus`.`workerid` = `worker`.`id`))) JOIN `workersuccess` ON((`workersuccess`.`workerid` = `worker`.`id`))) GROUP BY `workersuccess`.`expno`,`expstatus`.`cs`,`expinput`.`id`");
        statement.addBatch("DROP VIEW IF EXISTS `successrate`");
        statement.addBatch("CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `successrate` AS SELECT `expinput`.`settingid` AS `settingid`,`expinput`.`cost` AS `cost`,`expinput`.`deadline` AS `deadline`,`expinput`.`cs1resultNum` AS `cs1resultNum`,`expinput`.`cs2resultNum` AS `cs2resultNum`,`expinput`.`cs3resultNum` AS `cs3resultNum`,(sum(`csresult`.`success`) / count(`csresult`.`success`)) AS `successrate`,`expstatus`.`algorithm` AS `algorithm` FROM ((`csresult` JOIN `expinput` ON((`csresult`.`expid` = `expinput`.`id`))) JOIN `expstatus` ON((`expstatus`.`expid` = `expinput`.`id`))) GROUP BY `expinput`.`settingid`,`expstatus`.`algorithm`");
        return statement.executeBatch().length > 0;
    }
}
