package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.SignIn;
import com.hudongwx.drawlottery.mobile.mappers.SignInMapper;
import com.hudongwx.drawlottery.mobile.service.user.ISignInService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/3 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/3 11:21　<br/>
 * <p>
 *          签到实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class SignInServiceImpl implements ISignInService {

    @Autowired
    SignInMapper mapper;

    @Override
    public boolean addUserSignIn(Long accountId) {
        if (null == accountId)
            return false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        SignIn signIn = new SignIn();
        List<SignIn> signInList = mapper.selectSignByAccountId(accountId);
        if (!signInList.isEmpty()) {
            Long date1 = signInList.get(0).getNewSignInDate();
            if (sdf.format(date).equals(sdf.format(date1)))
                return false;
            Date dayBack = new Date(date.getTime() - Settings.ONE_DAY_LONG_VALUE);
            if (sdf.format(date1).equals(sdf.format(dayBack))) {
                signIn.setSignInDay(signInList.get(0).getSignInDay() + 1);
            } else {
                signIn.setSignInDay(1);
            }
        } else {
            signIn.setSignInDay(1);
        }
        signIn.setUserAccountId(accountId);
        signIn.setNewSignInDate(date.getTime());
        return mapper.insert(signIn) > 0;
    }

    @Override
    public List<Map<String, Object>> selectUserSign(Long accountId) {
        if (null == accountId)
            return null;
        List<SignIn> signInList = mapper.selectSignByAccountId(accountId);
        if (signInList.isEmpty())
            return null;
        List<Map<String, Object>> infoList = new ArrayList<>();
        for (SignIn signIn : signInList) {
            Map<String, Object> map = new HashMap<>();
            map.put("continuousTimes", signIn.getSignInDay());
            map.put("date", signIn.getNewSignInDate());
            infoList.add(map);
        }
        return infoList;
    }
}
