package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.CreditsUpdateRecordsMapper;
import com.zgds.xfyj.util.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreditsUpdateRecordsService {
    private final Logger logger = LoggerFactory.getLogger(CreditsUpdateRecordsService.class);

    @Autowired
    private CreditsUpdateRecordsMapper mapper;

    private String Mapper = "CreditsMapper";

    /**
     * 查看用户积分记录列表
     *
     * @return
     */
    public ServerResponse credits_records(String user_id, Integer currPage, Integer pageSize) {
        ServerResponse serverResponse = null;

        try {
            int totalPage = 0;
            int totalCount = mapper.getCount(user_id);
            if (totalCount > 0) {
                if ((totalCount % pageSize) == 0) {
                    totalPage = totalCount / pageSize;
                } else {
                    totalPage = (totalCount / pageSize) + 1;
                }
                if (currPage > totalPage) {
                    log.info("用户查询积分记录页码错误");
                    return ServerResponse.createByErrorMessage("页码错误");
                }
                int startIndex = (currPage - 1) * pageSize;
                log.info("查询用户积分明细成功：" + Mapper + ".getAll()");
                serverResponse = ServerResponse.createBySuccess("查询用户积分明细成功！", mapper.getAll(user_id, startIndex, pageSize));
            } else {
                serverResponse = ServerResponse.createBySuccess("查询用户积分明细成功,暂无数据！", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("查询用户积分明细异常！");
        } finally {
            return serverResponse;
        }

    }

    /**
     * 查看用户当前积分
     *
     * @param user_id
     * @return
     */
    public ServerResponse getCredits(String user_id) {
        log.info("查看用户当前积分成功:" + Mapper + ".getCredits()");
        return ServerResponse.createBySuccess("查询成功！", mapper.getCredits(user_id));
    }

}