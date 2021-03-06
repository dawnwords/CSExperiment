package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;
import cn.edu.fudan.se.crowdservice.bean.CrowdWorkerGroups;
import cn.edu.fudan.se.crowdservice.datagen.Random;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class GenerateWorkerGroupDAO extends DAO<CrowdWorkerGroups> {

    private int cs1GroupNum, cs2GroupNum, cs3GroupNum;
    private Random random;

    public GenerateWorkerGroupDAO cs1GroupNum(int cs1GroupNum) {
        this.cs1GroupNum = cs1GroupNum;
        return this;
    }

    public GenerateWorkerGroupDAO cs2GroupNum(int cs2GroupNum) {
        this.cs2GroupNum = cs2GroupNum;
        return this;
    }

    public GenerateWorkerGroupDAO cs3GroupNum(int cs3GroupNum) {
        this.cs3GroupNum = cs3GroupNum;
        return this;
    }

    public GenerateWorkerGroupDAO random(Random random) {
        this.random = random;
        return this;
    }

    @Override
    protected CrowdWorkerGroups processData(Connection connection) throws Exception {
        return randomSelect(new SelectWorkerDAO().processData(connection));
    }

    private CrowdWorkerGroups randomSelect(List<CrowdWorker> all) {
        int selectedNumber = cs1GroupNum + cs2GroupNum + cs3GroupNum;
        int r, i;
        for (i = selectedNumber; i < all.size(); i++) {
            r = (int) (random.nextDouble() * (i + 1));
            if (r < selectedNumber) {
                Collections.swap(all, i, r);
            }
        }

        CrowdWorkerGroups result = new CrowdWorkerGroups();
        for (i = 0; i < cs1GroupNum; i++) {
            result.addCS1Worker(all.get(i));
        }
        for (; i < cs2GroupNum + cs1GroupNum; i++) {
            result.addCS2Worker(all.get(i));
        }
        for (; i < selectedNumber; i++) {
            result.addCS3Worker(all.get(i));
        }
        return result;
    }
}
