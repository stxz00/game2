package com.games.game2.mapper;

import com.games.game2.domain.TestParam;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {
    TestParam selectOne();
}
