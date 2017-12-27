package com.algorithm.$12_logutils;

import com.algorithm.$5_json.JsonMapper;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/27.
 * @version:v1.0
 */

public class LogUtils {

    /** record param by using Jackson */
    public static void infoUseJson(Object targetClass, Object messageToJson,String... otherMessageToJson) {

        org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(targetClass.getClass());
        if (log.isInfoEnabled()) {
            String strInfo = messageToJson == null ? "message is null." : JsonMapper.toJson(messageToJson);
            StringBuilder sb = new StringBuilder(messageToJson.getClass().getSimpleName());
            sb.append(" ;request json: ").append(strInfo);
//            sb.append(" ;curUserNo: ").append(UserContext.getCurrentuserNo().orElse("no userNo "));
            if (otherMessageToJson != null && otherMessageToJson.length != 0){
                sb.append(" ;other message: ").append(JsonMapper.toJson(otherMessageToJson));
            }
            log.info(sb.toString());
        }
    }
}
