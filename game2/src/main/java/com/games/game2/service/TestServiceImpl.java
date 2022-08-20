package com.games.game2.service;

import com.games.game2.domain.TestParam;
import com.games.game2.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{
    @Autowired
    TestMapper testMapper;

    @Override
    public TestParam selectOne() {
        return testMapper.selectOne();
    }
}
